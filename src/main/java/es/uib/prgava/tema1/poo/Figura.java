// Figura.java (implementa Describible)
package es.uib.prgava.tema1.poo;

public abstract class Figura implements Describible {
    private final String nombre;

    protected Figura(String nombre) { this.nombre = nombre; }

    public abstract double area();

    /** Devuelve una figura del mismo tipo con el tamaño multiplicado por el factor. */
    public abstract Figura escalar(double factor);

    @Override
    public String describir() {
        return nombre + " de área " + area();
    }
}
