// Composicion.java
package es.uib.prgava.tema1.genericos;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import es.uib.prgava.tema1.monitor.*;

public final class Composicion {
    public static void main(String[] args) {
        var web = Servicio.desde("https://www.uib.es");
        var dns = Servicio.desde("dns:uib.es");
        var resultados = List.of(
                Resultado.activo(web, Duration.ofMillis(120)),
                Resultado.caido(dns),
                Resultado.degradado(web, Duration.ofMillis(650)),
                Resultado.activo(dns, Duration.ofMillis(40)));

        Predicate<Resultado> fallo = Resultado::esFallo;
        Predicate<Resultado> lento = r -> r.latencia().compareTo(Duration.ofMillis(500)) > 0;
        Predicate<Resultado> problematico = fallo.or(lento);

        Function<Resultado, String> nombre = r -> r.servicio().nombre();
        Function<Resultado, String> etiqueta = nombre.andThen(String::toUpperCase);

        Comparator<Resultado> porServicioYLatencia = Comparator
                .comparing(nombre)
                .thenComparing(Resultado::latencia, Comparator.reverseOrder());

        System.out.println(resultados.stream().filter(problematico).map(etiqueta).toList());
        // [DNS:UIB.ES, HTTPS://WWW.UIB.ES]
        System.out.println(resultados.stream().sorted(porServicioYLatencia).map(Resultado::latencia).toList());
        // [PT0.04S, PT0S, PT0.65S, PT0.12S]
    }
}
