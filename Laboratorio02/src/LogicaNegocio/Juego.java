
package LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;

public class Juego implements Serializable {   
    
    private static final String[] NOMBRE_CAMPOS = {"Codigo"};
    private String codigo;
    private String accion;
    private ArrayList<Ficha> fichas;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Ficha> tablero;    
    
    public Juego (){
        this.codigo = "";
        this.accion = "";
    }
        
        //////////////////////////// Inicializacion de las 28 Fichas ////////////////////////////////////
//        fichas = new ArrayList();
//        for(int i = 0; i <= 6; i++){            
//            for(int j = i; j <= 6; j++)
//                fichas.add(new Ficha(i, j, j+i));
//        }
//        
//        //////////////////////////// Creacion de los Jugadores ////////////////////////////////////
//        jugadores = new ArrayList();
//        jugadores.add(new Jugador("Giancarlo", 0));
//        jugadores.add(new Jugador("Andres", 0));
//        jugadores.add(new Jugador("Slon", 0));
//        
//        tablero = new ArrayList();
//    }

    public Juego(String codigo) {
        this.codigo = codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Ficha> fichas) {
        this.fichas = fichas;
    }
    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    public ArrayList<Ficha> getTablero() {
        return tablero;
    }

    public void setTablero(ArrayList<Ficha> tablero) {
        this.tablero = tablero;
    }

    @Override
    public String toString() {
        return "Juego{" + "codigo=" + codigo + '}';
    }
    
    public static String[] nombreCampos(){
        return NOMBRE_CAMPOS;
    }
    
    public Object[] toArray(){
        Object[] r = new Object[1];
        r[0] = getCodigo();
        return r;
    }
    
//    public void iniciarPartida(){
//        repartirFichas();
//        System.out.print("Giancarlo: " + getJugadores().get(0).toString() + "\n");
//        System.out.print("Andres: " + getJugadores().get(1).toString() + "\n");
//        System.out.print("Slon: " + getJugadores().get(2).toString() + "\n");
//        System.out.print("Pozo: " + getFichas().toString() + "\n \n");        
//        
//        int primero = piezaMayor();
//        realizarJugada(jugadores.get(primero),(int)(Math.random() * jugadores.get(primero).fichasJugador.size()));
//        System.out.print("Turno de " + jugadores.get(primero).getNickName() + "\n \n");
//        System.out.print("Giancarlo: " + getJugadores().get(0).toString() + "\n");
//        System.out.print("Andres: " + getJugadores().get(1).toString() + "\n");
//        System.out.print("Slon: " + getJugadores().get(2).toString() + "\n");
//        System.out.print("Pozo: " + getFichas().toString() + "\n");
//        System.out.print("Tablero: " + getTablero().toString() + "\n \n"); 
//        
//        int jugAct = primero;
//        boolean jugada = false; 
//        int cont = 0;
//        boolean empate = true;
//        while(finJuego() && empate){
//            jugada = false;
//            jugAct++;
//            if(jugAct >= jugadores.size())
//                jugAct = 0;
//            System.out.print("Turno de " + jugadores.get(jugAct).getNickName() + "\n \n");
//            for(int i = 0; i < jugadores.get(jugAct).fichasJugador.size(); i++){
//                if(realizarJugada(jugadores.get(jugAct),i)){
//                    jugada = true;
//                    cont = 0;
//                    break;
//                }                   
//            }
//            if(!jugada){
//                System.out.print("No hay jugadas disponibles" + "\n");
//                if(!comer(jugadores.get(jugAct))){
//                    cont++;
//                    System.out.print("No hay fichas en el pozo pasa el turno" + "\n \n");
//                }
//            }
//            
//            System.out.print("Giancarlo: " + getJugadores().get(0).toString() + "\n");
//            System.out.print("Andres: " + getJugadores().get(1).toString() + "\n");
//            System.out.print("Slon: " + getJugadores().get(2).toString() + "\n");
//            System.out.print("Pozo: " + getFichas().toString() + "\n");
//            System.out.print("Tablero: " + getTablero().toString() + "\n \n");
//            
//            if(cont == 3){
//                empate = false;
//                System.out.print("Fin del Juego ¡Empate!" + "\n \n");
//            }  
//        }  
//    }
//    
//    public int piezaMayor(){
//        int mayor = 0; 
//        int aux = -1;
//        for(int i = 0; i < jugadores.size(); i++)
//            for(int j = 0; j < jugadores.get(i).fichasJugador.size(); j++)
//                if(mayor < jugadores.get(i).fichasJugador.get(j).getTotal()){
//                    mayor = jugadores.get(i).fichasJugador.get(j).getTotal();
//                    aux = i;
//                }                    
//        return aux;    
//    }
//    
//    public boolean comer(Jugador jugador){        
//        if(fichas.isEmpty())
//            return false;
//        int random;
//        Ficha f;
//        random = (int)(Math.random() * fichas.size());
//        f = fichas.remove(random);
//        jugador.fichasJugador.add(f);
//        return true;
//    }
//    
//    public void repartirFichas(){
//        int random;
//        Ficha f;
//        
//        for(int i = 0; i < jugadores.size(); i++){
//            for(int j = 0; j < 7; j++){
//                random = (int)(Math.random() * fichas.size());
//                f = fichas.remove(random);
//                jugadores.get(i).fichasJugador.add(f);
//            }                
//        }
//    }
//    
//    public boolean realizarJugada(Jugador jugador, int n){
//        Ficha ficha = jugador.fichasJugador.get(n);
//        if(tablero.isEmpty()){
//            jugador.fichasJugador.remove(n);
//            tablero.add(ficha);
//            return true;
//        }
//        else if(tablero.get(0).getValorIzq() == ficha.getValorDer()){
//            jugador.fichasJugador.remove(n);
//            tablero.add(0, ficha);
//            return true;
//        }
//        else if(tablero.get(0).getValorIzq() == ficha.getValorIzq()){
//            jugador.fichasJugador.remove(n);
//            int aux = ficha.getValorDer();
//            ficha.setValorDer(ficha.getValorIzq());
//            ficha.setValorIzq(aux);
//            tablero.add(0, ficha);
//            return true;
//        }
//        else if(tablero.get(tablero.size()-1).getValorDer() == ficha.getValorIzq()){
//            jugador.fichasJugador.remove(n);
//            tablero.add(ficha);
//            return true;
//        }
//        else if(tablero.get(tablero.size()-1).getValorDer() == ficha.getValorDer()){
//            jugador.fichasJugador.remove(n);
//            int aux = ficha.getValorDer();
//            ficha.setValorDer(ficha.getValorIzq());
//            ficha.setValorIzq(aux);
//            tablero.add(ficha);
//            return true;
//        }
//        else
//            return false;
//    }
//    
//    public boolean finJuego(){
//        for(int i = 0; i < jugadores.size(); i++){
//            if(jugadores.get(i).fichasJugador.isEmpty()){
//                System.out.print("Fin del Juego Ganador: " + jugadores.get(i));
//                sumaPuntaje(jugadores.get(i));
//                return false;
//            }
//        }
//        return true;
//    }      
//    
//    public void sumaPuntaje(Jugador jugador){
//        int puntaje = 0;
//        for(int i = 0; i < jugadores.size(); i++)
//            for(int j = 0; j < jugadores.get(i).fichasJugador.size(); j++)
//                puntaje += jugadores.get(i).fichasJugador.get(j).getTotal();
//        jugador.setPuntaje(puntaje);
//    }
    
//    public static void main(String args[]){
//        Juego juego = new Juego();
//        juego.iniciarPartida();
//    }
}


