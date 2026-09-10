// PuertoTcpTest.java
package es.uib.prgava.tema1.monitor;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PuertoTcpTest {

    @ParameterizedTest
    @CsvSource({
        "mail.uib.es, 25,    mail.uib.es:25",
        "localhost,   1,     localhost:1",
        "localhost,   65535, localhost:65535"
    })
    void nombreCombinaHostYPuerto(String host, int puerto, String esperado) {
        assertEquals(esperado, new PuertoTcp(host, puerto).nombre());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 65536, -1 })
    void puertoFueraDeRangoEsRechazado(int puerto) {
        assertThrows(IllegalArgumentException.class, () -> new PuertoTcp("localhost", puerto));
    }
}
