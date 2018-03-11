
package LogicaNegocio;

import java.io.Serializable;

public class Jugador implements Serializable{   
    
    public Jugador(){
        this.nickName = "";
        this.puntaje = 0;
        this.accion = "";
    }

    public Jugador(String nickName, int puntaje) {
        this.nickName = nickName;
        this.puntaje = puntaje;
    }   

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
  
    public String getNickName() {
        return nickName;
    }

    public int getPuntaje() {
        return puntaje;
    }
 
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public static String[] nombreCampos(){
        return NOMBRE_CAMPOS;
    }
    
    public Object[] toArray(){
        Object[] r = new Object[2];
        r[0] = getNickName();
        r[1] = getPuntaje();
        return r;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nickName=" + nickName + ", puntaje=" + puntaje + '}';
    }
    
    private static final String[] NOMBRE_CAMPOS = {"Nick Name", "Puntaje"};
    private String nickName;
    private int puntaje;

    public static String[] getNOMBRE_CAMPOS() {
        return NOMBRE_CAMPOS;
    }

    private String accion;
}
