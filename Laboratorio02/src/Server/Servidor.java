package Server;

import java.io.*;
import java.net.*;
import java.util.logging.*;

/**
 *
 * @author Andres Cascante Salas Jose Andres Slon Conejo Giancarlo Navarro
 * Valverde
 */

public class Servidor {

    public static void main(String args[]) throws IOException {
        ServerSocket ss;
        System.out.print("Inicializando servidor... ");
        try {
            ss = new ServerSocket(10578);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: " + socket);
                Socket socket2 = new Socket("127.0.0.1", 10579);
                ((ServidorHilo) new ServidorHilo(socket, socket2)).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
