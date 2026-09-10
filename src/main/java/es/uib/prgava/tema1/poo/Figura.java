// Figura.java
package es.uib.prgava.tema1.poo;

public abstract class Figura {
    private final String nombre;

    protected Figura(String nombre) { this.nombre = nombre; }

    public abstract double area();

    public String describir() {
        return nombre + " de área " + area();   // llama al método abstracto: enlace dinámico
    }
}
