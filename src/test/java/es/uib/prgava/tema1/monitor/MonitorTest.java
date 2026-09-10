// MonitorTest.java
package es.uib.prgava.tema1.monitor;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static es.uib.prgava.tema1.monitor.Estado.ACTIVO;
import static es.uib.prgava.tema1.monitor.Estado.CAIDO;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MonitorTest {

    private static final Servicio WEB = Servicio.desde("https://www.uib.es");

    private RepositorioEnMemoria repositorio;
    private NotificadorRegistro registro;

    @BeforeEach
    void prepararColaboradores() {
        repositorio = new RepositorioEnMemoria();
        registro = new NotificadorRegistro();
    }

    private Monitor monitorCon(Sonda sonda) {
        var monitor = new Monitor(sonda, new FallosConsecutivos(2), repositorio);
        monitor.suscribir(registro);
        return monitor;
    }

    @Test
    void cadaComprobacionSeGuardaEnElRepositorio() {
        var monitor = monitorCon(SondaFalsa.con(ACTIVO, CAIDO, ACTIVO));

        monitor.comprobar(WEB);
        monitor.comprobar(WEB);
        monitor.comprobar(WEB);

        var historial = repositorio.historial(WEB);
        assertAll(
                () -> assertEquals(3, historial.size()),
                () -> assertEquals(List.of(ACTIVO, CAIDO, ACTIVO),
                                   historial.stream().map(Resultado::estado).toList()));
    }

    @Test
    void unFalloAisladoNoGeneraAlerta() {
        var monitor = monitorCon(SondaFalsa.con(ACTIVO, CAIDO, ACTIVO));

        monitor.comprobar(List.of(WEB, WEB, WEB));

        assertTrue(registro.alertas().isEmpty());
    }

    @Test
    void dosFallosSeguidosGeneranUnaAlertaParaEseServicio() {
        var monitor = monitorCon(SondaFalsa.con(ACTIVO, CAIDO, CAIDO));

        monitor.comprobar(List.of(WEB, WEB, WEB));

        assertAll(
                () -> assertEquals(1, registro.alertas().size()),
                () -> assertEquals(WEB, registro.alertas().getFirst().servicio()));
    }

    @Test
    void todosLosSuscriptoresRecibenLaAlerta() {
        var segundoRegistro = new NotificadorRegistro();
        var monitor = monitorCon(SondaFalsa.con(CAIDO));
        monitor.suscribir(segundoRegistro);

        monitor.comprobar(List.of(WEB, WEB));

        assertEquals(registro.alertas(), segundoRegistro.alertas());
    }
}
