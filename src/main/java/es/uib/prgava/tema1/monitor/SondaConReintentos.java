// SondaConReintentos.java
package es.uib.prgava.tema1.monitor;

import java.util.Objects;

public final class SondaConReintentos implements Sonda {
    private final Sonda interna;
    private final int intentos;

    public SondaConReintentos(Sonda interna, int intentos) {
        if (intentos < 1) throw new IllegalArgumentException("intentos ≥ 1");
        this.interna = Objects.requireNonNull(interna);
        this.intentos = intentos;
    }

    @Override
    public Resultado sondear(Servicio servicio) {
        Resultado ultimo = null;
        for (int i = 0; i < intentos; i++) {
            ultimo = interna.sondear(servicio);
            if (!ultimo.esFallo()) {
                return ultimo;
            }
        }
        return ultimo;   // todos los intentos fallaron
    }
}
