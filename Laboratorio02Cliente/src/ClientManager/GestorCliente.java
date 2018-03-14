package ClientManager;

import LogicaNegocio.Ficha;
import LogicaNegocio.Juego;
import LogicaNegocio.Jugador;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;

/**
 *
 * @author 
 * Andres Cascante Salas
 * Jose Andres Slon Conejo
 * Giancarlo Navarro Valverde
 */

public class GestorCliente extends Thread {

    // SOCKETS
    protected Socket socket;
    protected Socket socket2;
    protected ServerSocket serSock;

    //OBJETOS
    protected ObjectOutputStream oos;
    protected ObjectInputStream ois;

    private Jugador jug;
    //public ArrayList<Jugador> jugadores;
    private Juego juego;

    public GestorCliente() {
        Thread hilo = new Thread(this);
        //jugadores = new ArrayList();
        jug = new Jugador();
        juego = new Juego();
        //juego = new Juego();
        hilo.start();
    }

    public void setJugador(Jugador jug) {
        this.jug = jug;
    }

    public Jugador getJugador() {
        return jug;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Juego getJuego() {
        return juego;
    }

    /* ----------------------------- METODOS DE JUGADOR ----------------------------- */
    public void enviarJugador(Jugador juga, String accion) {
        try {
            Jugador jugador = juga;
            jugador.setAccion(accion);
            System.out.println("Nombre: " + jugador.getNickName());
            socket = new Socket("127.0.0.1", 10578);

            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(jugador);
            oos.close();

            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* ----------------------------- METODOS DE JUEGO ----------------------------- */
    public void enviarJuego(Juego juga, String accion) {
        try {
            Juego juego = juga;
            juego.setAccion(accion);
            System.out.println("Nombre: " + juego.getCodigo());
            socket = new Socket("127.0.0.1", 10578);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(juego);
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /* ----------------------------- METODOS DE FICHA ----------------------------- */
    public void enviarFicha(Ficha fich) {
        try {
            Ficha ficha = fich;
            System.out.println("Nombre: " + ficha.getTotal());
            socket = new Socket("127.0.0.1", 10578);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(ficha);
            oos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* ----------------------------- RUN RECIBE DEL SERVIDOR ----------------------------- */
    @Override
    public void run() {

        try {
            serSock = new ServerSocket(10579);
            while (true) {
                socket2 = serSock.accept();
                Object object;
                ois = new ObjectInputStream(socket2.getInputStream());

                object = ois.readObject();

                String className = object.getClass().getSimpleName();
                switch (className) {
                    case "Jugador":
                        Jugador jugador = (Jugador) object;
                        switch (jugador.getAccion()) {
                            case "buscarJugador":
                                setJugador(jugador);
                                break;
                            case "guardar":
                                System.out.println(jugador.toString());
                                break;
                            case "actualizar": // actualiza los puntos
                                System.out.println(jugador.toString());
                                break;
                        }
                        break;

                    case "Juego":
                        Juego juego = (Juego) object;

                        switch (juego.getAccion()) {
                            case "nuevoJuego":
                                setJuego(juego);
                                System.out.println(juego.toString());
                                System.out.println(juego.getJugadores().toString());
                                System.out.println(juego.getJugadores().get(0).fichasJugador.toString());
                                break;
                            case "retornarJuego": // actualiza los puntos
                                setJuego(juego);
                                break;
                        }
                        break;
                    case "Ficha":
                        break;
                }
            }
        } catch (IOException ex) {
            System.out.println("U P S ! ! ! E R R O R");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
    }

    public void desconectar() {
        try {
            socket.close();
            socket2.close();
            ois.close();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
