/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author casca
 */
import LogicaNegocio.Jugador;
import java.io.*;
import java.net.*;
import java.util.logging.*;
public class ServidorHilo extends Thread {
	
	private Socket socket;
        
	private DataOutputStream dos;
	private DataInputStream dis;
        
        private ObjectOutputStream oos;
        private ObjectInputStream ois;
        
	private int idSessio;
	
	public ServidorHilo(Socket socket, int id) {
		this.socket = socket;
		this.idSessio = id;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
                        
                        oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
                        
		} catch (IOException ex) {
			Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void desconectar() {
		try {
			socket.close();
		} catch (IOException ex) {
			Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	@Override
		public void run() {
		Jugador jugador;
		try {
			jugador = (Jugador) ois.readObject();
			if(jugador != null){
                                jugador.setNickName("banano en leche");
				oos.writeObject(jugador);
			}
                        ois.close();
                        oos.close();
		} catch (IOException ex) {
			Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
		desconectar();
	}
}
