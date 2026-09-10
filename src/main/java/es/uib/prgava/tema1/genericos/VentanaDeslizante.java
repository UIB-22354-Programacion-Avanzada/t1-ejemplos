// VentanaDeslizante.java
package es.uib.prgava.tema1.genericos;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/** Conserva como máximo los últimos {@code capacidad} elementos añadidos, en orden de inserción. */
public final class VentanaDeslizante<T> {
    private final int capacidad;
    private final Deque<T> elementos;

    public VentanaDeslizante(int capacidad) {
        if (capacidad < 1) throw new IllegalArgumentException("capacidad ≥ 1");
        this.capacidad = capacidad;
        this.elementos = new ArrayDeque<>(capacidad);
    }

    public void anadir(T elemento) {
        if (elementos.size() == capacidad) {
            elementos.removeFirst();
        }
        elementos.addLast(elemento);
    }

    public List<T> elementos() {
        return List.copyOf(elementos);
    }

    public boolean llena() {
        return elementos.size() == capacidad;
    }
}
