// FallosConsecutivosTest.java
package es.uib.prgava.tema1.monitor;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FallosConsecutivosTest {

    private static final Servicio WEB = Servicio.desde("https://www.uib.es");
    private static final Resultado OK = Resultado.activo(WEB, Duration.ofMillis(100));
    private static final Resultado FALLO = Resultado.caido(WEB);

    private PoliticaDeAlerta politica;

    @BeforeEach
    void prepararPolitica() {
        politica = new FallosConsecutivos(2);
    }

    @Test
    void historialVacioNoAlerta() {
        assertFalse(politica.debeAlertar(List.of()));
    }

    @Test
    void unSoloFalloNoAlerta() {
        assertFalse(politica.debeAlertar(List.of(OK, FALLO)));
    }

    @Test
    void dosFallosSeguidosAlertan() {
        assertTrue(politica.debeAlertar(List.of(OK, FALLO, FALLO)));
    }

    @Test
    void unExitoEntreFallosReiniciaLaCuenta() {
        assertFalse(politica.debeAlertar(List.of(FALLO, OK, FALLO)));
    }

    @Test
    void masFallosDeLosNecesariosTambienAlertan() {
        assertTrue(politica.debeAlertar(List.of(FALLO, FALLO, FALLO)));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -10 })
    void minimoNoPositivoEsRechazado(int minimo) {
        assertThrows(IllegalArgumentException.class, () -> new FallosConsecutivos(minimo));
    }
}
