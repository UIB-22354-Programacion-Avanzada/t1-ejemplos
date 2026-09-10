// NotificadorConsola.java
package es.uib.prgava.tema1.monitor;

public final class NotificadorConsola implements Notificador {
    @Override
    public void notificar(Alerta alerta) {
        System.out.printf("[%s] ALERTA %s: %s%n",
                alerta.instante(), alerta.servicio().nombre(), alerta.mensaje());
    }
}
