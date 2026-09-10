// Conmutador.java
package es.uib.prgava.tema1.poo;

public class Conmutador extends DispositivoRed {

    private final int numeroDePuertos;

    public Conmutador(String nombre, String direccionIp, int numeroDePuertos) {
        super(nombre, direccionIp);
        this.numeroDePuertos = numeroDePuertos;
    }

    public int getNumeroDePuertos() { return numeroDePuertos; }

    public void reenviarTrama() {
        System.out.println(getNombre() + " reenvía una trama");
    }
}
