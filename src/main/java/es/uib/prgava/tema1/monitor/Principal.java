// Principal.java (con observadores)
package es.uib.prgava.tema1.monitor;

import java.nio.file.Path;
import java.time.Duration;

public final class Principal {
    public static void main(String[] args) {
        var servicios = LectorConfiguracion.leer(Path.of(args[0]));

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
