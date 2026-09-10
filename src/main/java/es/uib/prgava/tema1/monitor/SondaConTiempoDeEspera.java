// SondaConTiempoDeEspera.java
package es.uib.prgava.tema1.monitor;

import java.time.Duration;
import java.util.Objects;

/** Considera caído un servicio cuya latencia supere el máximo. (En el Tema 4 será un tiempo de espera real.) */
public final class SondaConTiempoDeEspera implements Sonda {
    private final Sonda interna;
    private final Duration maximo;

    public SondaConTiempoDeEspera(Sonda interna, Duration maximo) {
        this.interna = Objects.requireNonNull(interna);
        this.maximo = Objects.requireNonNull(maximo);
    }

    @Override
    public Resultado sondear(Servicio servicio) {
        var resultado = interna.sondear(servicio);
        return resultado.latencia().compareTo(maximo) > 0
                ? Resultado.caido(servicio)
                : resultado;
    }
}
