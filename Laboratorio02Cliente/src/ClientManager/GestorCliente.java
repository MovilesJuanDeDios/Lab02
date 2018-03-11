
package ClientManager;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServicioJugador;
import LogicaNegocio.Ficha;
import LogicaNegocio.Juego;
import LogicaNegocio.Jugador;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.*;

/**
 *
 * @author casca
 */
public class GestorCliente extends Thread {

    // SOCKETS
    protected Socket socket;
    protected Socket socket2;
    protected ServerSocket serSock;

    // MENSAJES
    protected DataOutputStream dos;
    protected DataInputStream dis;

    //OBJETOS
    protected ObjectOutputStream oos;
    protected ObjectInputStream ois;

    
    public GestorCliente() {
        Thread hilo=new Thread(this);
        hilo.start();
    }

    /* ----------------------------- METODOS DE JUGADOR ----------------------------- */
    public void enviarJugador(Jugador jug, String accion) {
        try {
            Jugador jugador = jug;
            jugador.setAccion(accion);
            System.out.println("Nombre: " + jugador.getNickName());
            socket = new Socket("127.0.0.1", 10578);
            dos = new DataOutputStream((socket.getOutputStream()));
            oos = new ObjectOutputStream(socket.getOutputStream());
            //dos.writeUTF(accion);
            oos.writeObject(jugador);
            dos.close();
            oos.close();
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* ----------------------------- METODOS DE JUEGO ----------------------------- */
    public void enviarJuego(Juego jug,String accion) {
        try {
            Juego juego = jug;
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
            while(true){
                socket2 = serSock.accept();
                dis = new DataInputStream(socket2.getInputStream());
                String respuesta="";
                respuesta = dis.readUTF();
                System.out.println(" Servidor devuelve: " + respuesta);
                dis.close();
                socket2.close();
            
        }
        } catch (IOException ex) {
            System.out.println("U P S ! ! ! E R R O R");
        }

    }
}
