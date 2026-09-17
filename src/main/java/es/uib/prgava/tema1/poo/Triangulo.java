// Triangulo.java
package es.uib.prgava.tema1.poo;

public final class Triangulo extends Figura {
    private final double base;
    private final double altura;

    public Triangulo(double base, double altura) {
        super("triángulo");
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double area() { return base * altura / 2; }

    @Override
    public Triangulo escalar(double factor) {
        return new Triangulo(base * factor, altura * factor);
    }
}
