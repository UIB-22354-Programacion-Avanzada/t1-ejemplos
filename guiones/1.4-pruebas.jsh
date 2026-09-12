// Guion de la sección 1.4 — Diseñar para probar: dobles escritos a mano
// Uso:  jshell --class-path target/classes guiones/1.4-pruebas.jsh
//
// Aquí no usamos JUnit: el objetivo es ver POR QUÉ el diseño de 1.2 hace
// que Monitor sea comprobable, construyendo los dobles a mano.

import es.uib.prgava.tema1.monitor.*;
import java.time.Duration;
import java.util.*;

var web = Servicio.desde("https://uib.es");

System.out.println("--- 1.4  Un stub: una sonda que devuelve lo que yo decida ---");

// Doble de prueba: sin azar, sin red, sin esperas. El resultado es el que fijo yo.
class SondaFija implements Sonda {
    private final Deque<Estado> guion;
    SondaFija(Estado... estados) { this.guion = new ArrayDeque<>(List.of(estados)); }
    @Override public Resultado sondear(Servicio servicio) {
        var estado = guion.isEmpty() ? Estado.ACTIVO : guion.removeFirst();
        return switch (estado) {
            case ACTIVO    -> Resultado.activo(servicio, Duration.ofMillis(100));
            case DEGRADADO -> Resultado.degradado(servicio, Duration.ofMillis(800));
            case CAIDO     -> Resultado.caido(servicio);
        };
    }
}

var sonda = new SondaFija(Estado.ACTIVO, Estado.CAIDO, Estado.CAIDO, Estado.ACTIVO);
var repositorio = new RepositorioEnMemoria();
var espia = new NotificadorRegistro();        // el spy ya lo teníamos escrito

var monitor = new Monitor(sonda, new FallosConsecutivos(2), repositorio);
monitor.suscribir(espia);

for (int i = 0; i < 4; i++) monitor.comprobar(web);

System.out.println("historial : " + repositorio.historial(web).stream().map(Resultado::estado).toList());
System.out.println("alertas   : " + espia.alertas().size() + "   (se espera 1, tras los dos CAIDO seguidos)");
System.out.println("resultado : " + (espia.alertas().size() == 1 ? "correcto" : "INCORRECTO"));

System.out.println();
System.out.println("--- 1.4  Por qué esto es posible ---");
System.out.println("Monitor no hace 'new' de su sonda: la recibe. Sustituirla por un doble");
System.out.println("es cambiar un argumento del constructor. Con 'new SondaSimulada(...)'");
System.out.println("dentro de Monitor, esta prueba no se podría escribir.");

System.out.println();
System.out.println("Prueba tú:  cambia el guion de SondaFija y predice el número de alertas antes de ejecutar.");
System.out.println("            new SondaFija(Estado.CAIDO, Estado.ACTIVO, Estado.CAIDO)  -> ¿cuántas?");
