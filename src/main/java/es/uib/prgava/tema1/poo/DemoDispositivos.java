// DemoDispositivos.java
package es.uib.prgava.tema1.poo;

public class DemoDispositivos {
    public static void main(String[] args) {
        var enrutador = new DispositivoRed("enrutador-1", "192.168.1.1");
        enrutador.activar();
        System.out.println(enrutador);                     // usa toString()

        var servidor = new DispositivoRed("servidor-a", "256.0.0.1");   // IP inválida
        System.out.println(servidor.getDireccionIp());     // 0.0.0.0

        var anonimo = new DispositivoRed();                // constructor sin parámetros
        System.out.println(anonimo);

        var alias = enrutador;                             // misma referencia, mismo objeto
        alias.desactivar();
        System.out.println(enrutador.isActivo());          // false: alias y enrutador son el mismo objeto

        var copia = new DispositivoRed("enrutador-1", "192.168.1.1");   // otro objeto, mismo estado
        System.out.println(enrutador == copia);            // false: identidades distintas
        System.out.println(enrutador.equals(copia));       // true: igualdad por estado

        System.out.println("Dispositivos creados: " + DispositivoRed.getDispositivosCreados());
    }
}
