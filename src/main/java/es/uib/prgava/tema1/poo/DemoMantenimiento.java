// DemoMantenimiento.java
package es.uib.prgava.tema1.poo;

public class DemoMantenimiento {

    static void mantenimiento(DispositivoRed dispositivo) {
        dispositivo.desactivar();                          // fuera de servicio
        boolean correcto = dispositivo.autocomprobar();    // cada uno sabe cómo probarse
        dispositivo.activar();                             // de vuelta al servicio
        System.out.println("  → " + dispositivo.getNombre()
                + (correcto ? ": correcto" : ": REVISAR"));
    }

    public static void main(String[] args) {
        mantenimiento(new Enrutador("enrutador-1", "192.168.1.1", 4));
        mantenimiento(new Conmutador("conmutador-a", "192.168.1.2", 24));
        mantenimiento(new Enrutador("enrutador-2", "192.168.1.3", 1));
        mantenimiento(new DispositivoRed("servidor-a", "256.0.0.1"));
    }
}
