// Cuadrado.java (con escalar)
package es.uib.prgava.tema1.poo;

public final class Cuadrado extends Figura {
    private final double lado;

    public Cuadrado(double lado) {
        super("cuadrado");
        this.lado = lado;
    }

    @Override
    public double area() { return lado * lado; }

    @Override
    public Cuadrado escalar(double factor) { return new Cuadrado(lado * factor); }
}
