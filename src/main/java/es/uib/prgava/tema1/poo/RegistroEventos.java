// RegistroEventos.java
package es.uib.prgava.tema1.poo;

public class RegistroEventos {

    public void registrar(String evento) {
        System.out.println("[registro] " + evento);
    }

    public void registrarVarios(String... eventos) {
        for (String evento : eventos) {
            registrar(evento);                  // uso propio: se llama a sí misma
        }
    }
}
