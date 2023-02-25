package safa.sge_erp;

public class Factura {

    Integer idFactura;
    Double total;
    Integer idCompra;
    Integer idVenta;

    public Factura() {
    }

    public Factura(Integer idFactura, Double total, Integer idCompra, Integer idVenta) {
        this.idFactura = idFactura;
        this.total = total;
        this.idCompra = idCompra;
        this.idVenta = idVenta;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }
}
