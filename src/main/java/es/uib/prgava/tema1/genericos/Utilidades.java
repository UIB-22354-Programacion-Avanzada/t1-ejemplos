// Utilidades.java
package es.uib.prgava.tema1.genericos;

import java.util.List;
import java.util.Optional;

public final class Utilidades {
    private Utilidades() { }

    public static <T extends Comparable<T>> Optional<T> maximo(List<T> elementos) {
        T mejor = null;
        for (T elemento : elementos) {
            if (mejor == null || elemento.compareTo(mejor) > 0) {
                mejor = elemento;
            }
        }
        return Optional.ofNullable(mejor);
    }
}
