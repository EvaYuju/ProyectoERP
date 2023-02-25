package safa.sge_erp;

public class Venta {

    Integer idVenta;
    Integer cantidad;
    Float total;
    String cliente;
    String detalle;
    Integer idProducto;

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Venta(Integer idVenta, Integer cantidad, Float total, String cliente, String detalle, Integer idProducto) {
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.total = total;
        this.cliente = cliente;
        this.detalle = detalle;
        this.idProducto = idProducto;
    }

    public Venta(Integer cantidad, Float total, String cliente, String detalle, Integer idProducto) {
        this.cantidad = cantidad;
        this.total = total;
        this.cliente = cliente;
        this.detalle = detalle;
        this.idProducto = idProducto;
    }

    public Venta(Integer cantidad, String cliente, String detalle, Integer idProducto) {
        this.cantidad = cantidad;
        this.cliente = cliente;
        this.detalle = detalle;
        this.idProducto = idProducto;
    }
}
