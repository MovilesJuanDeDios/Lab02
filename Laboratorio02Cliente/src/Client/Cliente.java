
package Client;

import ClientManager.GestorCliente;
import LogicaNegocio.Jugador;

public class Cliente {

    public static void main(String[] args) {
        GestorCliente gc = new GestorCliente();
        
        Jugador jugador = new Jugador("xyx", 5);
        gc.enviarJugador(jugador,"actualizar");

               
    }

}
