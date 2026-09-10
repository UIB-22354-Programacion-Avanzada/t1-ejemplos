// Enrutador.java
package es.uib.prgava.tema1.poo;

public class Enrutador extends DispositivoRed {

    private final int numeroDePuertos;     // atributo nuevo, solo de los enrutadores

    public Enrutador(String nombre, String direccionIp, int numeroDePuertos) {
        super(nombre, direccionIp);        // primero, el constructor de la superclase
        this.numeroDePuertos = numeroDePuertos;
    }

    public int getNumeroDePuertos() { return numeroDePuertos; }

    public void encaminar(String origen, String destino) {   // comportamiento nuevo
        System.out.println(getNombre() + " encamina un paquete de " + origen + " a " + destino);
    }

    @Override                                                 // comportamiento redefinido
    public String toString() {
        return "Enrutador[" + getNombre() + ", ip=" + getDireccionIp()
                + ", puertos=" + numeroDePuertos + "]";
    }
}
