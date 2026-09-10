// Descripciones.java
package es.uib.prgava.tema1.monitor;

public final class Descripciones {
    private Descripciones() { }

    public static String describir(Servicio servicio) {
        return switch (servicio) {
            case ServicioHttp h        -> "Servidor web en " + h.url();
            case ServicioDns d         -> "Servidor DNS para " + d.dominio();
            case PuertoTcp(var host, var puerto) -> "Puerto " + puerto + " de " + host;
        };
        // sin 'default': el compilador verifica que el switch es exhaustivo
    }
}
