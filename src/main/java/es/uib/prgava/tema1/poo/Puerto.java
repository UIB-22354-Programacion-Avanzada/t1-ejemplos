// Puerto.java (implementa Describible y Comparable)
package es.uib.prgava.tema1.poo;

public record Puerto(int numero) implements Describible, Comparable<Puerto> {
    public Puerto {
        if (numero < 1 || numero > 65_535) {
            throw new IllegalArgumentException("Puerto fuera de rango: " + numero);
        }
    }

    @Override
    public String describir() { return "puerto TCP " + numero; }

    @Override
    public int compareTo(Puerto otro) {
        return Integer.compare(numero, otro.numero);   // negativo, cero o positivo
    }
}
