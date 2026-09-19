// Describible.java (ampliada)
package es.uib.prgava.tema1.poo;

public interface Describible {
    String describir();

    default void mostrar() {                       // derivado del método abstracto
        System.out.println(describir());
    }
}
