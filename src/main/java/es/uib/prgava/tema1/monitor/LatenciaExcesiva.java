// LatenciaExcesiva.java
package es.uib.prgava.tema1.monitor;

import java.time.Duration;
import java.util.List;

public final class LatenciaExcesiva implements PoliticaDeAlerta {
    private final Duration umbral;
    private final int muestras;

    public LatenciaExcesiva(Duration umbral, int muestras) {
        this.umbral = umbral;
        this.muestras = muestras;
    }

    @Override
    public boolean debeAlertar(List<Resultado> historial) {
        if (historial.size() < muestras) return false;
        var recientes = historial.subList(historial.size() - muestras, historial.size());
        long totalMs = recientes.stream()
                .filter(r -> !r.esFallo())
                .mapToLong(r -> r.latencia().toMillis())
                .sum();
        long activos = recientes.stream().filter(r -> !r.esFallo()).count();
        return activos > 0 && totalMs / activos > umbral.toMillis();
    }

    @Override
    public String toString() { return "latencia media > " + umbral.toMillis() + " ms"; }
}
