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
        long totalMs = 0;                 // suma de latencias de las muestras que respondieron
        long activos = 0;                 // cuántas respondieron
        for (int i = historial.size() - muestras; i < historial.size(); i++) {
            var resultado = historial.get(i);
            if (!resultado.esFallo()) {
                totalMs += resultado.latencia().toMillis();
                activos++;
            }
        }
        return activos > 0 && totalMs / activos > umbral.toMillis();
    }

    @Override
    public String toString() { return "latencia media > " + umbral.toMillis() + " ms"; }
}
