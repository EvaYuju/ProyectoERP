package safa.sge_erp;

public class Compra {

    Integer referencia;
    String nombre;
    Float precio;
    Integer cantidad;
    Float total;
    String proveedor;
    String detalle;

    public Integer getReferencia() {
        return referencia;
    }

    public void setReferencia(Integer referencia) {
        this.referencia = referencia;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }





    // para crear desde formulario
    public Compra(String nombre, Float precio, Integer cantidad, Float total, String proveedor, String detalle) {

        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
        this.proveedor = proveedor;
        this.detalle = detalle;
    }
    //
    public Compra(Integer referencia, String nombre, Float precio, Integer cantidad, Float total, String proveedor, String detalle) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
        this.proveedor = proveedor;
        this.detalle = detalle;
    }
}