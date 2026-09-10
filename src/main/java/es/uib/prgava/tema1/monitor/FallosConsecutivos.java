// FallosConsecutivos.java
package es.uib.prgava.tema1.monitor;

import java.util.List;

public final class FallosConsecutivos implements PoliticaDeAlerta {
    private final int minimo;

    public FallosConsecutivos(int minimo) {
        if (minimo < 1) throw new IllegalArgumentException("El mínimo debe ser ≥ 1");
        this.minimo = minimo;
    }

    @Override
    public boolean debeAlertar(List<Resultado> historial) {
        if (historial.size() < minimo) return false;
        return historial.subList(historial.size() - minimo, historial.size())
                        .stream()
                        .allMatch(Resultado::esFallo);
    }

    @Override
    public String toString() { return minimo + " fallos consecutivos"; }
}
