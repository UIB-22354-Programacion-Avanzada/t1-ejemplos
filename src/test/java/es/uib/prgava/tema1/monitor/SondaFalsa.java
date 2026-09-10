// SondaFalsa.java  (en src/test/java)
package es.uib.prgava.tema1.monitor;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/** Devuelve, en orden, los estados indicados; repite el último indefinidamente. */
final class SondaFalsa implements Sonda {
    private final Deque<Estado> pendientes;
    private Estado ultimo;

    SondaFalsa(List<Estado> estados) {
        if (estados.isEmpty()) throw new IllegalArgumentException("al menos un estado");
        this.pendientes = new ArrayDeque<>(estados);
    }

    static SondaFalsa con(Estado... estados) {
        return new SondaFalsa(List.of(estados));
    }

    @Override
    public Resultado sondear(Servicio servicio) {
        if (!pendientes.isEmpty()) {
            ultimo = pendientes.removeFirst();
        }
        return switch (ultimo) {
            case ACTIVO    -> Resultado.activo(servicio, Duration.ofMillis(100));
            case DEGRADADO -> Resultado.degradado(servicio, Duration.ofMillis(900));
            case CAIDO     -> Resultado.caido(servicio);
        };
    }
}
