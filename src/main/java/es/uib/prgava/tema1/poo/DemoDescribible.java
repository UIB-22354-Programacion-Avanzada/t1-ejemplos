// DemoDescribible.java
package es.uib.prgava.tema1.poo;

public class DemoDescribible {
    public static void main(String[] args) {
        Describible[] cosas = {
            new Circulo(1.0),      // una figura
            new Puerto(443),       // un puerto TCP: ninguna relación con la anterior
            new Cuadrado(3.0)
        };

        for (var cosa : cosas) {
            cosa.mostrar();        // método default, heredado de la interfaz
        }
    }
}
