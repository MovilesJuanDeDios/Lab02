
package ClientManager;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServicioJugador;
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

    protected Socket socket2et;
    protected Socket socket2;
    protected ServerSocket serSock;


    protected DataOutputStream dos;
    protected DataInputStream dis;

    protected ObjectOutputStream oos;
    protected ObjectInputStream ois;

    private Jugador jugador;
    private int estado = 0;
    private boolean flag = true;

    public GestorCliente() {
        Thread hilo=new Thread(this);
        hilo.start();
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void ingresarJugador() {
        try {
            Jugador jugador = this.jugador;
            System.out.println("Nombre: " + jugador.getNickName());
            socket2et = new Socket("127.0.0.1", 10578);
            oos = new ObjectOutputStream(socket2et.getOutputStream());
            oos.writeObject(jugador);
            oos.close();
            socket2et.close();
        } catch (IOException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        }

    }
}
