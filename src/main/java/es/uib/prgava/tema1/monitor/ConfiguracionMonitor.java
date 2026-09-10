// ConfiguracionMonitor.java
package es.uib.prgava.tema1.monitor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public record ConfiguracionMonitor(Duration intervalo, Duration tiempoDeEspera,
                                   int reintentos, Duration umbralLatencia,
                                   List<Servicio> servicios) {

    public ConfiguracionMonitor {
        if (intervalo.isNegative() || intervalo.isZero()) {
            throw new IllegalArgumentException("El intervalo debe ser positivo");
        }
        if (tiempoDeEspera.isNegative() || tiempoDeEspera.isZero()) {
            throw new IllegalArgumentException("El tiempo de espera debe ser positivo");
        }
        if (reintentos < 0) {
            throw new IllegalArgumentException("Los reintentos no pueden ser negativos");
        }
        if (servicios.isEmpty()) {
            throw new IllegalArgumentException("Hace falta al menos un servicio");
        }
        servicios = List.copyOf(servicios);
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Duration intervalo = Duration.ofSeconds(30);       // valores por defecto
        private Duration tiempoDeEspera = Duration.ofSeconds(2);
        private int reintentos = 1;
        private Duration umbralLatencia = Duration.ofMillis(300);
        private final List<Servicio> servicios = new ArrayList<>();

        private Builder() { }

        public Builder intervalo(Duration d)       { this.intervalo = d; return this; }
        public Builder tiempoDeEspera(Duration d)  { this.tiempoDeEspera = d; return this; }
        public Builder reintentos(int n)           { this.reintentos = n; return this; }
        public Builder umbralLatencia(Duration d)  { this.umbralLatencia = d; return this; }
        public Builder servicio(Servicio s)        { this.servicios.add(s); return this; }
        public Builder servicios(List<Servicio> l) { this.servicios.addAll(l); return this; }

        public ConfiguracionMonitor build() {
            return new ConfiguracionMonitor(intervalo, tiempoDeEspera, reintentos,
                                            umbralLatencia, servicios);
        }
    }
}
