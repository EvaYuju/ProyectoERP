package safa.sge_erp;

public class Compra {

    Integer idCompra;
    Integer cantidad;
    Float total;
    String proveedor;
    String detalle;
    Integer idProducto;

    public Compra(Integer cantidad, String proveedor, String detalle, Integer idProducto) {
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.detalle = detalle;
        this.idProducto = idProducto;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
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

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Compra(Integer idCompra, Integer cantidad, Float total, String proveedor, String detalle, Integer idProducto) {
        this.idCompra = idCompra;
        this.cantidad = cantidad;
        this.total = total;
        this.proveedor = proveedor;
        this.detalle = detalle;
        this.idProducto = idProducto;
    }

    public Compra(Integer cantidad, Float total, String proveedor, String detalle, Integer idProducto) {
        this.cantidad = cantidad;
        this.total = total;
        this.proveedor = proveedor;
        this.detalle = detalle;
        this.idProducto = idProducto;
    }
}
