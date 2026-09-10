// PoliticaDeAlerta.java (con combinadores)
package es.uib.prgava.tema1.monitor;

import java.util.List;

@FunctionalInterface
public interface PoliticaDeAlerta {
    boolean debeAlertar(List<Resultado> historial);

    default PoliticaDeAlerta o(PoliticaDeAlerta otra) {
        return h -> this.debeAlertar(h) || otra.debeAlertar(h);
    }

    default PoliticaDeAlerta y(PoliticaDeAlerta otra) {
        return h -> this.debeAlertar(h) && otra.debeAlertar(h);
    }
}
