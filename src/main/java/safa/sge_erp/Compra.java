package safa.sge_erp;

public class Compra {
    Integer referenciaC;
    String nombreC;
    Float precioC;
    Integer cantidadC;
    Float totalC;
    String proveedorC;
    String detelleC;

    public Compra(int referenciaC, String nombreC, float precioC, int cantidadC, float totalC, String proveedorC, String detelleC) {
        this.referenciaC = referenciaC;
        this.nombreC = nombreC;
        this.precioC = precioC;
        this.cantidadC = cantidadC;
        this.totalC = totalC;
        this.proveedorC = proveedorC;
        this.detelleC = detelleC;
    }

    public int getReferenciaC() {
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

    public float getPrecioC() {
        return precioC;
    }

    public void setPrecioC(float precioC) {
        this.precioC = precioC;
    }

    public int getcantidadC() {
        return cantidadC;
    }

    public void setcantidadC(int cantidadC) {
        this.cantidadC = cantidadC;
    }

    public float getTotalC() {
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

    public String getDetelleC() {
        return detelleC;
    }

    public void setDetelleC(String detelleC) {
        this.detelleC = detelleC;
    }
}
