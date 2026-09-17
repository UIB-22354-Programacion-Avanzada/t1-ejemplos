// VentanaDeslizante.java
package es.uib.prgava.tema1.genericos;

import java.util.ArrayList;
import java.util.List;

/** Conserva como máximo los últimos {@code capacidad} elementos añadidos, en orden de inserción. */
public final class VentanaDeslizante<T> {
    private final int capacidad;
    private final List<T> elementos = new ArrayList<>();

    public VentanaDeslizante(int capacidad) {
        if (capacidad < 1) throw new IllegalArgumentException("capacidad ≥ 1");
        this.capacidad = capacidad;
    }

    public void anadir(T elemento) {
        if (elementos.size() == capacidad) {
            elementos.remove(0);           // sale el más antiguo
        }
        elementos.add(elemento);           // entra el más nuevo, al final
    }

    public List<T> elementos() {
        return List.copyOf(elementos);
    }

    public boolean llena() {
        return elementos.size() == capacidad;
    }
}
