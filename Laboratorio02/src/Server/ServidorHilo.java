
package Server;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
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
    
    private DataOutputStream dos;
    private DataInputStream dis;
    
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    private int idSessio;
    
    public ServidorHilo(Socket socket,Socket socket2) {
        this.socket = socket;
        this.socket2 = socket2;
        try {
            dos = new DataOutputStream(socket2.getOutputStream());
//            dis = new DataInputStream(socket.getInputStream());
//            
//            oos = new ObjectOutputStream(socket2.getOutputStream());
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
                break;
                
                case "Ficha":
                    System.out.println("Es ficha");
                    Ficha ficha = (Ficha) object;
                break;
                
                case "Juego":
                    System.out.println("Es juego");
                    Juego juego = (Juego) object;
                break;
            }
          
           
            /*if (jugador != null) {
                //jugador.setNickName("banano en leche");
                ServicioJugador sj = new ServicioJugador();
                sj.insertarJugador(jugador);
                dos.writeUTF("Jugador recibido");
                //oos.writeObject(jugador);
            }*/
            ois.close();
            dos.close();
            //oos.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }/* catch (GlobalException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        desconectar();
    }
}
