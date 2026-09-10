// Informes.java
package es.uib.prgava.tema1.monitor;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Informes {
    private final LectorResultados lector;

    public Informes(LectorResultados lector) {
        this.lector = Objects.requireNonNull(lector);
    }

    /** Fracción de comprobaciones que no fueron fallo, en [0, 1]; vacío si no hay datos. */
    public OptionalDouble disponibilidad(Servicio servicio) {
        var historial = lector.historial(servicio);
        if (historial.isEmpty()) {
            return OptionalDouble.empty();
        }
        long correctas = historial.stream().filter(r -> !r.esFallo()).count();
        return OptionalDouble.of((double) correctas / historial.size());
    }

    public Map<Estado, Long> recuentoPorEstado(Servicio servicio) {
        return lector.historial(servicio).stream()
                .collect(Collectors.groupingBy(
                        Resultado::estado,
                        () -> new EnumMap<>(Estado.class),   // mapa ordenado por el enum
                        Collectors.counting()));
    }

    public List<Resultado> ultimosFallos(Servicio servicio, int cuantos) {
        var historial = lector.historial(servicio);
        return historial.reversed().stream()       // del más reciente al más antiguo
                .filter(Resultado::esFallo)
                .limit(cuantos)
                .toList();
    }

    public LongSummaryStatistics latencias(Servicio servicio) {
        return lector.historial(servicio).stream()
                .filter(r -> !r.esFallo())
                .mapToLong(r -> r.latencia().toMillis())
                .summaryStatistics();
    }

    public Map<Servicio, Double> disponibilidadPorServicio() {
        return lector.servicios().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        s -> disponibilidad(s).orElse(0.0)));
    }

    /** Una línea por servicio, de peor a mejor disponibilidad. */
    public String resumen() {
        return disponibilidadPorServicio().entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(e -> String.format("%-28s %6.1f %%   latencia media %5.0f ms",
                        e.getKey().nombre(),
                        e.getValue() * 100,
                        latencias(e.getKey()).getAverage()))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
