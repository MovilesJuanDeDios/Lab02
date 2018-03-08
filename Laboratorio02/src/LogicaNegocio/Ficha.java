
package LogicaNegocio;

public class Ficha {
    
    public Ficha() {
        this.valorDer = 0;
        this.valorIzq = 0;
        this.total = 0;
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
        return "Ficha{" + "valorDerecho=" + valorDer + ", valorIzquierdo=" + valorIzq + '}';
    } 
    
    public static String[] nombreCampos(){
        return NOMBRE_CAMPOS;
    }
    
    public Object[] toArray(){
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
    
}
