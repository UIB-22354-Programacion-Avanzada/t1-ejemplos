// SondaSimulada.java (como subclase de SondaBase)
package es.uib.prgava.tema1.monitor;

import java.time.Duration;
import java.util.Optional;
import java.util.Random;

public final class SondaSimulada extends SondaBase {
    private final Random aleatorio;
    private final double probabilidadFallo;

    public SondaSimulada(long semilla, double probabilidadFallo, Duration umbral) {
        super(umbral);
        this.aleatorio = new Random(semilla);
        this.probabilidadFallo = probabilidadFallo;
    }

    @Override
    protected Optional<Duration> medir(Servicio servicio) {
        if (aleatorio.nextDouble() < probabilidadFallo) {
            return Optional.empty();
        }
        return Optional.of(Duration.ofMillis(50 + aleatorio.nextInt(400)));
    }
}
