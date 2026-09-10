// Puerto.java
package es.uib.prgava.tema1.poo;

public record Puerto(int numero) {
    public Puerto {                       // constructor compacto: sin lista de parámetros
        if (numero < 1 || numero > 65_535) {
            throw new IllegalArgumentException("Puerto fuera de rango: " + numero);
        }
    }
}
