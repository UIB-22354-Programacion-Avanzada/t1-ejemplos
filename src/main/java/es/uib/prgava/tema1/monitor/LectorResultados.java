// LectorResultados.java
package es.uib.prgava.tema1.monitor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LectorResultados {
    /** Historial de un servicio, del más antiguo al más reciente. */
    List<Resultado> historial(Servicio servicio);

    /** Último resultado registrado, si lo hay. */
    Optional<Resultado> ultimo(Servicio servicio);

    Set<Servicio> servicios();
}
