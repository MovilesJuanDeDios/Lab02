
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

    private String nombre;
    private int puntaje;
    private String juego;

    public GestorCliente(String nombre, int puntaje, String juego) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.juego = juego;
           
    }

    @Override
    public void run() {
        try {
            ServicioJugador sj = new ServicioJugador();
            Jugador jugador = new Jugador(nombre, puntaje, juego);
            sj.insertarJugador(jugador);
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
        } catch (GlobalException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
