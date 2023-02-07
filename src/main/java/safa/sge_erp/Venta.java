package safa.sge_erp;

public class Venta {

    Integer referenciaV;
    String nombre;
    String cliente;
    Float precio;
    Integer cantidad;
    Float total;
    String detalle;

    public Venta(Integer referencia, String nombre, String cliente, Float precio, Integer cantidad, Float total, String detalle) {
        this.referenciaV = referencia;
        this.nombre = nombre;
        this.cliente = cliente;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
        this.detalle = detalle;
    }

    public Integer getReferencia() {
        return referenciaV;
    }

    public void setReferencia(Integer referencia) {
        this.referenciaV = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }


}
