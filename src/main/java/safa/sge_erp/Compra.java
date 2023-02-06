package safa.sge_erp;

public class Compra {
    Integer referenciaC;
    String nombreC;
    Float precioC;
    Integer cantidadC;
    Float totalC;
    String proveedorC;

    public Compra(Integer referenciaC, String nombreC, Float precioC, Integer cantidadC, Float totalC, String proveedorC, String detalleC) {
        this.referenciaC = referenciaC;
        this.nombreC = nombreC;
        this.precioC = precioC;
        this.cantidadC = cantidadC;
        this.totalC = totalC;
        this.proveedorC = proveedorC;
        this.detalleC = detalleC;
    }

    String detalleC;

    public Compra(String nombreC, Float precioC, Integer cantidadC, Float totalC, String proveedorC, String detalleC) {
        this.nombreC = nombreC;
        this.precioC = precioC;
        this.cantidadC = cantidadC;
        this.totalC = totalC;
        this.proveedorC = proveedorC;
        this.detalleC = detalleC;
    }

    public Integer getReferenciaC() {
        return referenciaC;
    }

    public void setReferenciaC(int referenciaC) {
        this.referenciaC = referenciaC;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public Float getPrecioC() {
        return precioC;
    }

    public void setPrecioC(float precioC) {
        this.precioC = precioC;
    }

    public Integer getcantidadC() {
        return cantidadC;
    }

    public void setcantidadC(int cantidadC) {
        this.cantidadC = cantidadC;
    }

    public Float getTotalC() {
        return totalC;
    }

    public void setTotalC(float totalC) {
        this.totalC = totalC;
    }

    public String getProveedorC() {
        return proveedorC;
    }

    public void setProveedorC(String proveedorC) {
        this.proveedorC = proveedorC;
    }

    public String getdetalleC() {
        return detalleC;
    }

    public void setdetalleC(String detalleC) {
        this.detalleC = detalleC;
    }
}
