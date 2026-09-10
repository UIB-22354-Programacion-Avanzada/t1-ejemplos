// Copias.java
package es.uib.prgava.tema1.genericos;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public final class Copias {
    private Copias() { }

    /** Copia todos los elementos de origen (productor) en destino (consumidor). */
    public static <T> void copiar(Collection<? extends T> origen, Collection<? super T> destino) {
        for (T elemento : origen) {
            destino.add(elemento);
        }
    }

    public static void main(String[] args) {
        List<Integer> enteros = List.of(1, 2, 3);
        List<Number> numeros = new ArrayList<>();
        List<Object> objetos = new ArrayList<>();
        copiar(enteros, numeros);     // T = Integer o Number; ambos válidos
        copiar(enteros, objetos);
        copiar(numeros, objetos);
        System.out.println(objetos);  // [1, 2, 3, 1, 2, 3]
    }
}
