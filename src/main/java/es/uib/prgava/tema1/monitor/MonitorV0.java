// MonitorV0.java
package es.uib.prgava.tema1.monitor;

import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonitorV0 {
    private final List<Resultado> historial = new ArrayList<>();
    private final Random aleatorio = new Random(42);
    private final Duration umbral = Duration.ofMillis(300);

    public void ejecutar(Path configuracion, int rondas) {
        var servicios = LectorConfiguracion.leer(configuracion);
        for (int ronda = 1; ronda <= rondas; ronda++) {
            for (var servicio : servicios) {
                // 1. Comprobar (simulado): latencia aleatoria, 10 % de fallos
                Resultado resultado;
                if (aleatorio.nextInt(10) == 0) {
                    resultado = Resultado.caido(servicio);
                } else {
                    var latencia = Duration.ofMillis(50 + aleatorio.nextInt(400));
                    resultado = latencia.compareTo(umbral) > 0
                            ? Resultado.degradado(servicio, latencia)
                            : Resultado.activo(servicio, latencia);
                }
                // 2. Guardar
                historial.add(resultado);
                // 3. Decidir si hay que avisar: dos fallos seguidos
                int seguidos = 0;
                for (int i = historial.size() - 1; i >= 0; i--) {
                    var r = historial.get(i);
                    if (!r.servicio().equals(servicio)) continue;
                    if (r.esFallo()) seguidos++; else break;
                }
                // 4. Avisar por consola
                if (seguidos >= 2) {
                    System.out.println("ALERTA: " + servicio.nombre() + " lleva "
                            + seguidos + " fallos consecutivos");
                }
            }
        }
        // 5. Informe final
        for (var servicio : servicios) {
            long fallos = 0;
            for (var resultado : historial) {
                if (resultado.servicio().equals(servicio) && resultado.esFallo()) {
                    fallos++;
                }
            }
            System.out.println(servicio.nombre() + ": " + fallos + " fallos de " + rondas);
        }
    }

    public static void main(String[] args) {
        new MonitorV0().ejecutar(Path.of(args[0]), 20);
    }
}
