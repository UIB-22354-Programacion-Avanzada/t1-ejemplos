// Anillo.java
package es.uib.prgava.tema1.poo;

public final class Anillo extends Figura {
    private final double exterior;
    private final double interior;

    public Anillo(double exterior, double interior) {
        super("anillo");
        this.exterior = exterior;
        this.interior = interior;
    }

    @Override
    public double area() { return Math.PI * (exterior * exterior - interior * interior); }

    @Override
    public Anillo escalar(double factor) {
        return new Anillo(exterior * factor, interior * factor);
    }
}
