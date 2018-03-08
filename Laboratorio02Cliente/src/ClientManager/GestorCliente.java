/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientManager;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;
/**
 *
 * @author casca
 */
public class GestorCliente extends Thread {
	protected Socket sk;
	protected DataOutputStream dos;
	protected DataInputStream dis;
	private int id;
	public GestorCliente(int id) {
		this.id = id;
		}
		@Override
		public void run() {
		try {
			sk = new Socket("127.0.0.1", 10578);
			dos = new DataOutputStream(sk.getOutputStream());
			dis = new DataInputStream(sk.getInputStream());
			System.out.println(id + " envía saludo");
			dos.writeUTF("hola");
			String respuesta="";
			respuesta = dis.readUTF();
			System.out.println(id + " Servidor devuelve saludo: " + respuesta);
			dis.close();
			dos.close();
			sk.close();
		} catch (IOException ex) {
			Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
