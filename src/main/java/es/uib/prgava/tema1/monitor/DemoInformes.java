// DemoInformes.java
package es.uib.prgava.tema1.monitor;

import java.time.Duration;
import java.util.List;

public final class DemoInformes {
    public static void main(String[] args) {
        var servicios = List.of(
                Servicio.desde("https://www.uib.es"),
                Servicio.desde("dns:uib.es"),
                Servicio.desde("tcp:mail.uib.es:25"));

        var repositorio = new RepositorioEnMemoria();
        var monitor = new Monitor(
                new SondaSimulada(7L, 0.20, Duration.ofMillis(300)),
                new FallosConsecutivos(2),
                repositorio);
        for (int i = 0; i < 50; i++) {
            monitor.comprobar(servicios);
        }

        var informes = new Informes(repositorio);
        System.out.println(informes.resumen());
        System.out.println(informes.recuentoPorEstado(servicios.get(0)));
        System.out.println(informes.ultimosFallos(servicios.get(0), 3).size() + " fallos recientes");
    }
}
