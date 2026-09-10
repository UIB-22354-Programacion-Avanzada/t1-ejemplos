// LectorConfiguracion.java
package es.uib.prgava.tema1.monitor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class LectorConfiguracion {
    private LectorConfiguracion() { }

    public static List<Servicio> leer(Path fichero) {
        var servicios = new ArrayList<Servicio>();
        try (var lineas = Files.lines(fichero)) {          // el stream se cierra al salir
            int numero = 0;
            for (var it = lineas.iterator(); it.hasNext(); ) {
                numero++;
                var linea = it.next().strip();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                servicios.add(interpretar(numero, linea));
            }
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo leer " + fichero, e);
        }
        return List.copyOf(servicios);
    }

    static Servicio interpretar(int numero, String linea) {
        try {
            if (linea.startsWith("http://") || linea.startsWith("https://")) {
                return new ServicioHttp(URI.create(linea));
            }
            if (linea.startsWith("dns:")) {
                return new ServicioDns(linea.substring(4));
            }
            if (linea.startsWith("tcp:")) {
                var partes = linea.substring(4).split(":");
                if (partes.length != 2) {
                    throw new ConfiguracionInvalidaException(numero, "se esperaba tcp:host:puerto");
                }
                return new PuertoTcp(partes[0], Integer.parseInt(partes[1]));
            }
            throw new ConfiguracionInvalidaException(numero, "tipo de servicio desconocido: " + linea);
        } catch (IllegalArgumentException e) {   // incluye NumberFormatException
            throw new ConfiguracionInvalidaException(numero, e.getMessage(), e);
        }
    }
}
