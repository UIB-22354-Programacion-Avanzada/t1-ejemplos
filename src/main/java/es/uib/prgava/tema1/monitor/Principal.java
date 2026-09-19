// Principal.java (con observadores)
package es.uib.prgava.tema1.monitor;

import java.time.Duration;
import java.util.List;

public final class Principal {
    public static void main(String[] args) {
        List<Servicio> servicios = List.of(
                Servicio.desde("https://www.uib.es"),
                Servicio.desde("dns:uib.es"),
                Servicio.desde("tcp:mail.uib.es:25"));

        var monitor = new Monitor(
                new SondaSimulada(42L, 0.25, Duration.ofMillis(300)),
                new FallosConsecutivos(2),
                new RepositorioEnMemoria());

        var registro = new NotificadorRegistro();
        monitor.suscribir(new NotificadorConsola());
        monitor.suscribir(registro);

        for (int ronda = 0; ronda < 20; ronda++) {
            monitor.comprobar(servicios);
        }
        System.out.println("Alertas emitidas: " + registro.alertas().size());
    }
}
