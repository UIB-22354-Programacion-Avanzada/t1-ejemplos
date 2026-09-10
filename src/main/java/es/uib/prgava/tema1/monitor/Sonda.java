// Sonda.java (ampliada)
package es.uib.prgava.tema1.monitor;

@FunctionalInterface
public interface Sonda {
    Resultado sondear(Servicio servicio);

    default boolean disponible(Servicio servicio) {
        return sondear(servicio).estado() != Estado.CAIDO;
    }

    static Sonda siempreActiva() {
        return servicio -> Resultado.activo(servicio, java.time.Duration.ofMillis(10));
    }
}
