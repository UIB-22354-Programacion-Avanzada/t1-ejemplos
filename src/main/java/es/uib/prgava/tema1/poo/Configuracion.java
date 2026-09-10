// Configuracion.java
package es.uib.prgava.tema1.poo;

import java.time.Duration;
import java.util.List;

public record Configuracion(Duration intervalo, List<String> servicios) {
    public Configuracion {
        if (intervalo.isNegative() || intervalo.isZero()) {
            throw new IllegalArgumentException("El intervalo debe ser positivo");
        }
        servicios = List.copyOf(servicios);   // copia inmutable; rechaza null y elementos null
    }
}
