// PuntoRoto.java
package es.uib.prgava.tema1.poo;

import java.util.HashSet;

public class PuntoRoto {
    private final int x, y;

    public PuntoRoto(int x, int y) { this.x = x; this.y = y; }

    @Override
    public boolean equals(Object o) {
        return o instanceof PuntoRoto p && p.x == x && p.y == y;
    }
    // ¡Falta hashCode!

    public static void main(String[] args) {
        var conjunto = new HashSet<PuntoRoto>();
        conjunto.add(new PuntoRoto(1, 2));
        System.out.println(conjunto.contains(new PuntoRoto(1, 2)));
        // false (casi siempre): lo busca en otro "cajón"
        conjunto.add(new PuntoRoto(1, 2));
        System.out.println(conjunto.size());
        // 2: el "conjunto" tiene duplicados
    }
}
