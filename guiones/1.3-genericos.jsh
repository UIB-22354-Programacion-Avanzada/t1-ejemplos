// Guion de la sección 1.3 — Genéricos, colecciones y Streams
// Uso:  jshell --class-path target/classes guiones/1.3-genericos.jsh

import es.uib.prgava.tema1.monitor.*;
import es.uib.prgava.tema1.genericos.*;
import java.time.Duration;
import java.util.*;

System.out.println("--- 1.3  Genéricos propios ---");

var par = new Par<>("estado", 200);
System.out.println(par + "   invertido -> " + par.invertido());
System.out.println("conPrimero -> " + par.conPrimero(404));

var ventana = new VentanaDeslizante<String>(3);
for (var s : List.of("a", "b", "c", "d")) ventana.anadir(s);
System.out.println("ventana de 3 tras a,b,c,d -> " + ventana.elementos() + "  llena=" + ventana.llena());

System.out.println();
System.out.println("--- 1.3  PECS: productor extends, consumidor super ---");

List<Integer> enteros = List.of(1, 2, 3);
List<Number> numeros = new ArrayList<>();
Copias.copiar(enteros, numeros);          // Collection<? extends T> -> Collection<? super T>
System.out.println("numeros = " + numeros);

System.out.println();
System.out.println("--- 1.3  Informes: pipelines sobre el historial ---");

var web = Servicio.desde("https://uib.es");
var dns = Servicio.desde("dns:uib.es");
var tcp = Servicio.desde("tcp:correo.uib.es:993");
var servicios = List.of(web, dns, tcp);

var repositorio = new RepositorioEnMemoria();
var monitor = new Monitor(new SondaSimulada(18L, 0.25, Duration.ofMillis(300)),
                          new FallosConsecutivos(2), repositorio);
for (int ronda = 0; ronda < 20; ronda++) monitor.comprobar(servicios);

var informes = new Informes(repositorio);
System.out.println(informes.resumen());
System.out.println();
System.out.println("recuento de " + dns.nombre() + ": " + informes.recuentoPorEstado(dns));
System.out.println("últimos 2 fallos: " + informes.ultimosFallos(dns, 2).size());
System.out.println("latencias de " + web.nombre() + ": " + informes.latencias(web));

System.out.println();
System.out.println("Prueba tú:  informes.disponibilidad(tcp)");
System.out.println("            informes.disponibilidadPorServicio()");
