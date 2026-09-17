// CualquieraDeDos.java
package es.uib.prgava.tema1.monitor;

import java.util.List;

/** Alerta si alerta cualquiera de las dos políticas que recibe. */
public final class CualquieraDeDos implements PoliticaDeAlerta {
    private final PoliticaDeAlerta primera;
    private final PoliticaDeAlerta segunda;

    public CualquieraDeDos(PoliticaDeAlerta primera, PoliticaDeAlerta segunda) {
        this.primera = primera;
        this.segunda = segunda;
    }

    @Override
    public boolean debeAlertar(List<Resultado> historial) {
        return primera.debeAlertar(historial) || segunda.debeAlertar(historial);
    }

    @Override
    public String toString() { return primera + " o " + segunda; }
}
