// DemoPunto.java
package es.uib.prgava.tema1.poo;

public class DemoPunto {
    public static void main(String[] args) {
        Punto p1 = new Punto(5, 10);
        Punto p2 = new Punto(5, 10);

        // 1. Uso de toString()
        // Se llama automáticamente al imprimir el objeto
        System.out.println("Imprimiendo p1: " + p1.toString()); 
        // Salida: Imprimiendo p1: Punto[x=5, y=10]

        // 2. Uso de hashCode()
        System.out.println("Hash de p1: " + p1.hashCode());
        System.out.println("Hash de p2: " + p2.hashCode());
        
        // Como tienen los mismos datos, el hashCode es idéntico
        System.out.println("¿Tienen el mismo hash? " + (p1.hashCode() == p2.hashCode())); 
        // Salida: true
    }
}

