package Server;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServicioJuego;
import AccesoDatos.ServicioJugador;
import LogicaNegocio.Ficha;
import LogicaNegocio.Juego;
import LogicaNegocio.Jugador;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.logging.*;

/**
 *
 * @author 
 * Andres Cascante Salas
 * Jose Andres Slon Conejo
 * Giancarlo Navarro Valverde
 */

public class ServidorHilo extends Thread {

    private Socket socket;
    private Socket socket2;

    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private Juego juego;

    public ServidorHilo(Socket socket, Socket socket2) {
        this.socket = socket;
        this.socket2 = socket2;
        try {

            oos = new ObjectOutputStream(socket2.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconectar() {
        try {
            socket.close();
            socket2.close();
            ois.close();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Object object = new Object();
        try {
            object = ois.readObject();
            String className = object.getClass().getSimpleName();
            switch (className) {
                case "Jugador":
                    Jugador jugador = (Jugador) object;
                    ServicioJugador sj = new ServicioJugador();
                    switch (jugador.getAccion()) {
                        case "guardar":
                            sj.insertarJugador(jugador);
                            oos.writeObject(jugador);
                            break;

                        case "actualizar":
                            sj.actualizarJugador(jugador);
                            oos.writeObject(jugador);
                            break;

                        case "buscarJugador":
                            Jugador jug2 = sj.buscarJugador(jugador.getNickName());
                            jug2.setAccion("buscarJugador");
                            oos.writeObject(jug2);

                            break;
                    }

                    break;

                case "Ficha":
                    Ficha ficha = (Ficha) object;
                    break;

                case "Juego":
                    Juego juego = (Juego) object;
                    //ServicioJuego sjuego = new ServicioJuego();
                    switch (juego.getAccion()) {
                        case "nuevoJuego":
                            //sjuego.insertarJuego(juego);
                            this.juego = juego;
                            this.juego.repartirFichas();
                            oos.writeObject(this.juego);
                            break;
                        case "retornarJuego":
                            this.juego = juego;
                            oos.writeObject(this.juego);
                            break;

                    }
                    break;
            }

        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GlobalException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
    }
}
