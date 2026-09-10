// ServicioTest.java
package es.uib.prgava.tema1.monitor;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServicioTest {

    @Test
    @DisplayName("una URL https produce un ServicioHttp con esa URL")
    void urlHttpsProduceServicioHttp() {
        var servicio = Servicio.desde("https://www.uib.es");

        var http = assertInstanceOf(ServicioHttp.class, servicio);
        assertEquals(URI.create("https://www.uib.es"), http.url());
    }

    @Test
    void tcpProduceHostYPuerto() {
        var servicio = Servicio.desde("tcp:mail.uib.es:25");

        assertEquals(new PuertoTcp("mail.uib.es", 25), servicio);
    }

    @Test
    void puertoFueraDeRangoEsRechazado() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> Servicio.desde("tcp:mail.uib.es:70000"));

        assertEquals("Puerto fuera de rango: 70000", e.getMessage());
    }
}
