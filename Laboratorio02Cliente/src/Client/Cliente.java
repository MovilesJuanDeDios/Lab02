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
		       
                         
			clients.add(new GestorCliente("ggg", 4, "yyy"));
                        clients.add(new GestorCliente("uuu", 9, "fff"));
                        clients.add(new GestorCliente("y",1, "aaaa"));
                        clients.add(new GestorCliente("t",2, "bbbb"));
                        clients.add(new GestorCliente("q",3, "ccc"));
		
		for (Thread thread : clients) {
			thread.start();
		}
	}
    
}
