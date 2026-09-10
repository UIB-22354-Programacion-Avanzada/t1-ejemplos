// ConjuntoContador.java (versión con composición)
package es.uib.prgava.tema1.poo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ConjuntoContador<E> implements Iterable<E> {
    private final Set<E> interno = new HashSet<>();
    private int intentos = 0;

    public boolean add(E e) {
        intentos++;
        return interno.add(e);
    }

    public boolean addAll(Collection<? extends E> c) {
        boolean cambiado = false;
        for (E e : c) {
            cambiado |= add(e);
        }
        return cambiado;
    }

    public boolean contains(Object o) { return interno.contains(o); }
    public int size() { return interno.size(); }
    public int intentos() { return intentos; }

    @Override
    public Iterator<E> iterator() { return interno.iterator(); }

    public static void main(String[] args) {
        var c = new ConjuntoContador<String>();
        c.addAll(List.of("a", "b", "c"));
        System.out.println(c.intentos());   // 3
    }
}
