/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

/**
 *
 * @author slon
 */
public class Ficha {
    
    private int valorDerecho;
    private int valorIzquierdo;

    public Ficha() {
    }

    public Ficha(int valorDerecho, int valorIzquierdo) {
        this.valorDerecho = valorDerecho;
        this.valorIzquierdo = valorIzquierdo;
    }

    public int getValorDerecho() {
        return valorDerecho;
    }

    public int getValorIzquierdo() {
        return valorIzquierdo;
    }

    public void setValorDerecho(int valorDerecho) {
        this.valorDerecho = valorDerecho;
    }

    public void setValorIzquierdo(int valorIzquierdo) {
        this.valorIzquierdo = valorIzquierdo;
    }

    @Override
    public String toString() {
        return "Ficha{" + "valorDerecho=" + valorDerecho + ", valorIzquierdo=" + valorIzquierdo + '}';
    }
    
}
