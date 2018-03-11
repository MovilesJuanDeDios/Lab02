
package LogicaNegocio;

import java.io.Serializable;

public class Juego implements Serializable {   
    
    public Juego (){
        this.codigo = "";
        this.accion = "";
    }

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
    
    private static final String[] NOMBRE_CAMPOS = {"Codigo"};
    private String codigo;
    private String accion;
}
