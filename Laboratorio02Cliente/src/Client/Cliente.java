/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.util.ArrayList;
import ClientManager.GestorCliente;
/**
 *
 * @author casca
 */
public class Cliente {

    public static void main(String[] args) {
	ArrayList<Thread> clients = new ArrayList<Thread>();
		for (int i = 0; i < 5; i++) {
			clients.add(new GestorCliente());
		}
		for (Thread thread : clients) {
			thread.start();
		}
	}
    
}
