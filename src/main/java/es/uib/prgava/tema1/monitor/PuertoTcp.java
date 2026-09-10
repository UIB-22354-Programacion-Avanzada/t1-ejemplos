// PuertoTcp.java
package es.uib.prgava.tema1.monitor;

public record PuertoTcp(String host, int puerto) implements Servicio {
    public PuertoTcp {
        if (host == null || host.isBlank()) {
            throw new IllegalArgumentException("Host vacío");
        }
        if (puerto < 1 || puerto > 65_535) {
            throw new IllegalArgumentException("Puerto fuera de rango: " + puerto);
        }
    }

    @Override
    public String nombre() { return host + ":" + puerto; }
}
