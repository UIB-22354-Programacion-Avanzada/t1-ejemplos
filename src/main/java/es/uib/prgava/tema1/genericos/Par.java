// Par.java
package es.uib.prgava.tema1.genericos;

public record Par<A, B>(A primero, B segundo) {

    public <C> Par<C, B> conPrimero(C nuevo) {
        return new Par<>(nuevo, segundo);
    }

    public Par<B, A> invertido() {
        return new Par<>(segundo, primero);
    }
}
