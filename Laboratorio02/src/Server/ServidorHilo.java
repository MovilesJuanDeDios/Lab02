
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
 * @author casca
 */

public class ServidorHilo extends Thread {
    
    private Socket socket;
    private Socket socket2;
  
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
     
    public ServidorHilo(Socket socket,Socket socket2) {
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
            switch(className) {
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
                            //System.out.println(jugador.toString());
                             
                                jugador.setAccion("buscarJugador");
                                oos.writeObject(sj.buscarJugador(jugador.getNickName()));
                            
                                
                            
                            break;
                    }
   
                    break;
                
                case "Ficha":
                    Ficha ficha = (Ficha) object;
                break;
                
                case "Juego":
                    System.out.println("Es juego");
                    Juego juego = (Juego) object;
                    ServicioJuego sjuego = new ServicioJuego();
                    switch (juego.getAccion()) {
                        case "insertar":
                            sjuego.insertarJuego(juego);
                            oos.writeObject(juego);
                            break;
                        case "buscar":
                            sjuego.buscarJuego(juego.getCodigo());
                            oos.writeObject(juego);
                            break;
                    }
                    break;
            }
          
            ois.close();
            oos.close();
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
