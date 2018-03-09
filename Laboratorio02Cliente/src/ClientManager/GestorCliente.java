/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientManager;

import LogicaNegocio.Jugador;
import java.io.*;
import java.net.Socket;
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
        
	public GestorCliente() {
		}
		@Override
		public void run() {
		try {
                        Jugador jugador = new Jugador("XXX", 2);
                        System.out.println("Nombre: " + jugador.getNickName());
			sk = new Socket("127.0.0.1", 10578);
                        
			dos = new DataOutputStream(sk.getOutputStream());
			dis = new DataInputStream(sk.getInputStream());
                        
                        oos = new ObjectOutputStream(sk.getOutputStream());
			ois = new ObjectInputStream(sk.getInputStream());
                       
			oos.writeObject(jugador);
			
                        jugador = (Jugador)ois.readObject();
			
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
}
