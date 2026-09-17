// SondaFalsa.java  (en src/test/java)
package es.uib.prgava.tema1.monitor;

import java.time.Duration;
import java.util.List;

/** Devuelve, en orden, los estados indicados; repite el último indefinidamente. */
final class SondaFalsa implements Sonda {
    private final List<Estado> estados;
    private int siguiente = 0;

    SondaFalsa(List<Estado> estados) {
        if (estados.isEmpty()) throw new IllegalArgumentException("al menos un estado");
        this.estados = List.copyOf(estados);
    }

    static SondaFalsa con(Estado... estados) {
        return new SondaFalsa(List.of(estados));
    }

    @Override
    public Resultado sondear(Servicio servicio) {
        var actual = estados.get(siguiente);
        if (siguiente < estados.size() - 1) {
            siguiente++;                      // el último se repite indefinidamente
        }
        return switch (actual) {
            case ACTIVO    -> Resultado.activo(servicio, Duration.ofMillis(100));
            case DEGRADADO -> Resultado.degradado(servicio, Duration.ofMillis(900));
            case CAIDO     -> Resultado.caido(servicio);
        };
    }
}
