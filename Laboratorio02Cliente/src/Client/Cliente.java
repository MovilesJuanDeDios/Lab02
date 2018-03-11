
package Client;

import java.util.ArrayList;
import ClientManager.GestorCliente;
import LogicaNegocio.Jugador;

public class Cliente {

    public static void main(String[] args) {
        GestorCliente gc = new GestorCliente();
        
        Jugador jugador = new Jugador("yxz", 1, "yyy");
        gc.ingresarJugador(jugador);

        

        
    }

}
