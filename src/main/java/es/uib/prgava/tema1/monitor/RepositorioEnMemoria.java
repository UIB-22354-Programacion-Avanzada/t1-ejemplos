// RepositorioEnMemoria.java
package es.uib.prgava.tema1.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class RepositorioEnMemoria implements RepositorioResultados {
    private final Map<Servicio, List<Resultado>> datos = new HashMap<>();

    @Override
    public void guardar(Resultado resultado) {
        datos.computeIfAbsent(resultado.servicio(), s -> new ArrayList<>()).add(resultado);
    }

    @Override
    public List<Resultado> historial(Servicio servicio) {
        return List.copyOf(datos.getOrDefault(servicio, List.of()));
    }

    @Override
    public Optional<Resultado> ultimo(Servicio servicio) {
        var lista = datos.get(servicio);
        return lista == null || lista.isEmpty() ? Optional.empty() : Optional.of(lista.getLast());
    }

    @Override
    public Set<Servicio> servicios() {
        return Set.copyOf(datos.keySet());
    }
}
