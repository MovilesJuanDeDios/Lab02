package LogicaNegocio;

import java.io.Serializable;

/**
 *
 * @author 
 * Andres Cascante Salas
 * Jose Andres Slon Conejo
 * Giancarlo Navarro Valverde
 */

public class Ficha implements Serializable {

    public Ficha() {
        this.valorDer = 0;
        this.valorIzq = 0;
        this.total = 0;
        this.accion = "";
    }

    public Ficha(int valorDer, int valorIzq, int total) {
        this.valorDer = valorDer;
        this.valorIzq = valorIzq;
        this.total = total;
    }

    public void setValorDer(int valorDer) {
        this.valorDer = valorDer;
    }

    public void setValorIzq(int valorIzq) {
        this.valorIzq = valorIzq;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }

    public int getValorDer() {
        return valorDer;
    }

    public int getValorIzq() {
        return valorIzq;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Ficha{" + "valorIzquierdo=" + valorIzq + ", valorDerecho=" + valorDer + ", total=" + total + '}';
    }

    public static String[] nombreCampos() {
        return NOMBRE_CAMPOS;
    }

    public Object[] toArray() {
        Object[] r = new Object[3];
        r[0] = getValorDer();
        r[1] = getValorIzq();
        r[2] = getTotal();
        return r;
    }

    private static final String[] NOMBRE_CAMPOS = {"Valor Derecho", "Valor Izquierdo", "Total"};
    private int valorDer;
    private int valorIzq;
    private int total;
    private String accion;

}
