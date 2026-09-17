// Circulo.java (con escalar)
package es.uib.prgava.tema1.poo;

public final class Circulo extends Figura {
    private final double radio;

    public Circulo(double radio) {
        super("círculo");
        this.radio = radio;
    }

    @Override
    public double area() { return Math.PI * radio * radio; }

    @Override
    public Circulo escalar(double factor) { return new Circulo(radio * factor); }
}
