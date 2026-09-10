// FalloSimple.java
package es.uib.prgava.tema1.monitor;

import java.util.List;

public final class FalloSimple implements PoliticaDeAlerta {
    @Override
    public boolean debeAlertar(List<Resultado> historial) {
        return !historial.isEmpty() && historial.getLast().esFallo();
    }

    @Override
    public String toString() { return "fallo simple"; }
}
