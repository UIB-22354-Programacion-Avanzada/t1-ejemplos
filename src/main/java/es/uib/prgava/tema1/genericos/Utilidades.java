// Utilidades.java
package es.uib.prgava.tema1.genericos;

import java.util.Collection;
import java.util.Optional;

public final class Utilidades {
    private Utilidades() { }

    public static <T extends Comparable<? super T>> Optional<T> maximo(Collection<? extends T> elementos) {
        T mejor = null;
        for (T e : elementos) {
            if (mejor == null || e.compareTo(mejor) > 0) {
                mejor = e;
            }
        }
        return Optional.ofNullable(mejor);
    }
}
