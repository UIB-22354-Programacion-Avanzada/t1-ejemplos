// DispositivoRed.java
package es.uib.prgava.tema1.poo;

import java.util.Objects;

public class DispositivoRed {

    // Atributo de clase (static): uno solo, compartido por todos los objetos
    private static int dispositivosCreados = 0;

    // Atributos de instancia: cada objeto tiene los suyos
    private String nombre;
    private String direccionIp;
    private boolean activo;

    // Constructor principal
    public DispositivoRed(String nombre, String direccionIp) {
        this.nombre = nombre;
        this.direccionIp = esIpValida(direccionIp) ? direccionIp : "0.0.0.0";
        this.activo = false;
        dispositivosCreados++;
    }

    // Constructor sobrecargado: delega en el principal con this(...)
    public DispositivoRed() {
        this("desconocido", null);
    }

    // Comportamiento
    public void activar()    { activo = true; }
    public void desactivar() { activo = false; }

    // Métodos de acceso y de modificación
    public String getNombre()               { return nombre; }
    public void setNombre(String nombre)    { this.nombre = nombre; }
    public String getDireccionIp()          { return direccionIp; }
    public boolean isActivo()               { return activo; }

    // Método de clase: se invoca como DispositivoRed.getDispositivosCreados()
    public static int getDispositivosCreados() { return dispositivosCreados; }

    // Igualdad por estado: dos dispositivos son el mismo si coinciden nombre e IP
    @Override
    public boolean equals(Object otro) {
        return otro instanceof DispositivoRed d
                && nombre.equals(d.nombre)
                && direccionIp.equals(d.direccionIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, direccionIp);
    }

    @Override
    public String toString() {
        return "DispositivoRed[nombre=" + nombre + ", ip=" + direccionIp + ", activo=" + activo + "]";
    }

    // Método auxiliar privado: comprueba el formato de una dirección IPv4
    private static boolean esIpValida(String ip) {
        if (ip == null || ip.isBlank()) {
            return false;
        }
        String octeto = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        return ip.matches("^" + octeto + "(\\." + octeto + "){3}$");
    }
}
