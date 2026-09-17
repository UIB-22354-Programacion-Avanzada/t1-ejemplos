// DemoEscalar.java
package es.uib.prgava.tema1.poo;

public class DemoEscalar {
    public static void main(String[] args) {
        Figura[] figuras = { new Circulo(1), new Triangulo(3, 4), new Anillo(2, 1) };

        for (var figura : figuras) {          // no sabe de qué tipo es ninguna
            System.out.println(figura.describir());
            System.out.println("  al doble → " + figura.escalar(2).describir());
        }
    }
}
