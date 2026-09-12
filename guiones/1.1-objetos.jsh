// Guion de la sección 1.1 — Objetos, clases, registros e interfaces selladas
// Uso:  jshell --class-path target/classes guiones/1.1-objetos.jsh
//
// Al terminar quedas dentro de JShell con las variables de abajo ya definidas.
// Escribe /vars para verlas, /list para revisar lo ejecutado y /exit para salir.

import es.uib.prgava.tema1.poo.*;
import es.uib.prgava.tema1.monitor.*;
import java.time.Duration;

System.out.println("--- 1.1  Clases, objetos y estado ---");

var r1 = new Enrutador("r1", "10.0.0.1", 24);
var sw1 = new Conmutador("sw1", "10.0.0.2", 48);
r1.activar();

System.out.println(r1);            // Enrutador redefine toString()
System.out.println(sw1);           // Conmutador hereda el de DispositivoRed
r1.encaminar("10.0.0.5", "8.8.8.8");
sw1.reenviarTrama();
System.out.println("dispositivos creados: " + DispositivoRed.getDispositivosCreados());

// Igualdad por estado, no por identidad:
var copia = new Enrutador("r1", "10.0.0.1", 24);
System.out.println("r1 == copia      -> " + (r1 == copia));
System.out.println("r1.equals(copia) -> " + r1.equals(copia));

System.out.println();
System.out.println("--- 1.1  Registros e interfaz sellada ---");

var web = Servicio.desde("https://uib.es");
var dns = Servicio.desde("dns:uib.es");
var tcp = Servicio.desde("tcp:correo.uib.es:993");

for (var s : java.util.List.of(web, dns, tcp)) {
    System.out.println(s.nombre() + "  ->  " + Descripciones.describir(s));
}

var ok = Resultado.activo(web, Duration.ofMillis(120));
System.out.println(ok);
System.out.println("¿es fallo? " + ok.esFallo());

System.out.println();
System.out.println("Prueba tú:  Servicio.desde(\"ftp://uib.es\")   /   Resultado.caido(dns)");
