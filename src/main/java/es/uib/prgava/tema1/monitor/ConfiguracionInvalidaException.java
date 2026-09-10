// ConfiguracionInvalidaException.java
package es.uib.prgava.tema1.monitor;

public class ConfiguracionInvalidaException extends RuntimeException {
    private final int linea;

    public ConfiguracionInvalidaException(int linea, String mensaje) {
        super("Línea " + linea + ": " + mensaje);
        this.linea = linea;
    }

    public ConfiguracionInvalidaException(int linea, String mensaje, Throwable causa) {
        super("Línea " + linea + ": " + mensaje, causa);
        this.linea = linea;
    }

    public int linea() { return linea; }
}
