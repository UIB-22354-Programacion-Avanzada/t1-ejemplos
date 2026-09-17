// RegistroContador.java (versión con composición)
package es.uib.prgava.tema1.poo;

public class RegistroContador {

    private final RegistroEventos interno = new RegistroEventos();   // TIENE un registro
    private int registrados = 0;                                     // en vez de SER uno

    public void registrar(String evento) {
        registrados++;
        interno.registrar(evento);
    }

    public void registrarVarios(String... eventos) {
        for (String evento : eventos) {
            registrar(evento);            // aquí el uso propio lo controlamos nosotros
        }
    }

    public int registrados() { return registrados; }

    public static void main(String[] args) {
        var registro = new RegistroContador();
        registro.registrarVarios("enlace caído", "enlace restaurado", "latencia alta");
        System.out.println(registro.registrados());   // 3
    }
}
