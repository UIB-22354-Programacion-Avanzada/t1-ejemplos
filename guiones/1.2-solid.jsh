// Guion de la sección 1.2 — SOLID, inyección de dependencias y patrones
// Uso:  jshell --class-path target/classes guiones/1.2-solid.jsh

import es.uib.prgava.tema1.monitor.*;
import java.time.Duration;
import java.util.List;

var web = Servicio.desde("https://uib.es");
var dns = Servicio.desde("dns:uib.es");
var servicios = List.of(web, dns);

System.out.println("--- 1.2  Cableado: el monitor recibe sus colaboradores ---");

// Semilla fija: la simulación es reproducible. Con 18L hay fallos consecutivos;
// con la 42L de Principal, en 10 rondas no llega a haberlos y no salta ninguna alerta.
var sonda = new SondaSimulada(18L, 0.25, Duration.ofMillis(300));
var repositorio = new RepositorioEnMemoria();
var registro = new NotificadorRegistro();

var monitor = new Monitor(sonda, new FallosConsecutivos(2), repositorio);
monitor.suscribir(new NotificadorConsola());
monitor.suscribir(registro);

for (int ronda = 0; ronda < 10; ronda++) monitor.comprobar(servicios);

System.out.println();
System.out.println("comprobaciones de " + web.nombre() + ": " + repositorio.historial(web).size());
System.out.println("alertas recogidas: " + registro.alertas().size());

System.out.println();
System.out.println("--- 1.2  Cambiar la política no toca Monitor (OCP) ---");

var repositorio2 = new RepositorioEnMemoria();
var monitor2 = new Monitor(sonda, new LatenciaExcesiva(Duration.ofMillis(200), 5), repositorio2);
var registro2 = new NotificadorRegistro();
monitor2.suscribir(registro2);
for (int ronda = 0; ronda < 10; ronda++) monitor2.comprobar(web);
System.out.println("con LatenciaExcesiva -> " + registro2.alertas().size() + " alertas");

System.out.println();
System.out.println("--- 1.2  Decorator: envolver la sonda sin modificarla ---");

var sondaRobusta = new SondaConReintentos(new SondaConTiempoDeEspera(sonda, Duration.ofMillis(500)), 3);
System.out.println(sondaRobusta.sondear(dns));

System.out.println();
System.out.println("Prueba tú:  cambia FallosConsecutivos(2) por FalloSimple y repite el bucle.");
System.out.println("            monitor.desuscribir(registro) y observa qué deja de registrarse.");
