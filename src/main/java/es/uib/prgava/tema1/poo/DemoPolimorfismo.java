// DemoPolimorfismo.java
package es.uib.prgava.tema1.poo;

public class DemoPolimorfismo {

    // Dos métodos con el mismo nombre y distinto tipo de parámetro: sobrecarga
    static String etiquetar(Figura figura)   { return "[figura]  " + figura.describir(); }
    static String etiquetar(Circulo circulo) { return "[círculo] " + circulo.describir(); }

    public static void main(String[] args) {

        // 1. Una variable, dos tipos
        Figura f = new Circulo(1.0);
        System.out.println(f.describir());        // se ejecuta Circulo.area()

        // 2. Muchos objetos distintos tratados como uno solo
        Figura[] figuras = { new Circulo(1.0), new Cuadrado(3.0), new Anillo(2.0, 1.0) };
        double total = 0;
        for (var figura : figuras) {
            System.out.println(figura.describir());
            total += figura.area();               // cada una aporta su propia fórmula
        }
        System.out.println("área total: " + total);

        // 3. Lo que devuelve escalar() también es polimórfico
        for (var figura : figuras) {
            System.out.println(figura.escalar(2).describir());
        }

        // 4. Sobrescritura frente a sobrecarga, sobre EL MISMO objeto
        Circulo c = new Circulo(1.0);
        Figura g = c;                             // misma referencia al mismo objeto

        System.out.println(c.area() == g.area()); // true: sobrescritura -> tipo dinámico
        System.out.println(etiquetar(c));         // etiquetar(Circulo)
        System.out.println(etiquetar(g));         // etiquetar(Figura), ¡mismo objeto!
    }
}
