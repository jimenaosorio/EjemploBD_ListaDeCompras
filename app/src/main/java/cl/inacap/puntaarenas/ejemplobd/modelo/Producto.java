package cl.inacap.puntaarenas.ejemplobd.modelo;

public class Producto {
    private String nombre;
    private int cantidad;
    private String unidad;
    private boolean pendiente;
    private static final boolean PENDIENTE=true;

    public Producto(String nombre, int cantidad, String unidad, boolean pendiente) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.pendiente = pendiente;
    }

    public Producto(String nombre, int cantidad, String unidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.pendiente=PENDIENTE;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    @Override
    public String toString() {
        String comprado;
        if(pendiente) comprado="pendiente";
        else comprado="comprado";
        return nombre+": "+comprado;
    }
}
