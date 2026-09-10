// Varianza.java
package es.uib.prgava.tema1.genericos;

public final class Varianza {
    public static void main(String[] args) {
        Integer[] enteros = { 1, 2, 3 };
        Object[] objetos = enteros;           // legal: los arrays son covariantes
        objetos[0] = "sorpresa";              // compila; lanza ArrayStoreException al ejecutarse
    }
}
