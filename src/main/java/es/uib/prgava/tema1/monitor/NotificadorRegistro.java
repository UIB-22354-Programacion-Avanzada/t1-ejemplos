// NotificadorRegistro.java
package es.uib.prgava.tema1.monitor;

import java.util.ArrayList;
import java.util.List;

/** Conserva las alertas recibidas; útil para informes y, como veremos en 1.4, para pruebas. */
public final class NotificadorRegistro implements Notificador {
    private final List<Alerta> alertas = new ArrayList<>();

    @Override
    public void notificar(Alerta alerta) {
        alertas.add(alerta);
    }

    public List<Alerta> alertas() {
        return List.copyOf(alertas);
    }
}
