
package ClientManager;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServicioJugador;
import LogicaNegocio.Jugador;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.*;

/**
 *
 * @author casca
 */
public class GestorCliente extends Thread {

    protected Socket sk;

    protected DataOutputStream dos;
    protected DataInputStream dis;

    protected ObjectOutputStream oos;
    protected ObjectInputStream ois;

    private Jugador jugador;
    private int estado = 0;
    private boolean flag = true;

    public GestorCliente() {

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
            sk = new Socket("127.0.0.1", 10578);

            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());

            oos = new ObjectOutputStream(sk.getOutputStream());
            ois = new ObjectInputStream(sk.getInputStream());

            oos.writeObject(jugador);

            jugador = (Jugador) ois.readObject();

            System.out.println("Servidor devuelve: " + jugador.toString());

            dis.close();
            dos.close();

            oos.close();
            ois.close();

            sk.close();

        } catch (IOException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        System.out.println("Ingresando al hilo");

        switch (estado) {
            case 1:
                ingresarJugador();
                setEstado(0);
                break;
            case 0:
                System.out.println("D E F A U L T ! ! !");
                break;
        }

    }

}
