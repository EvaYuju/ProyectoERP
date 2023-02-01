package safa.sge_erp;

import java.util.List;

public class Usuario {

    String nombre;
    String clave;
    String email;
    List<String> basesDeDatos;

    public Usuario(String nombre, String clave, String email) {
        this.nombre = nombre;
        this.clave = clave;
        this.email = email;
    }

    public Usuario(String nombre, String clave, String email, List<String> basesDeDatos) {
        this.nombre = nombre;
        this.clave = clave;
        this.email = email;
        this.basesDeDatos = basesDeDatos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getBasesDeDatos() {
        return basesDeDatos;
    }

    public void setBasesDeDatos(List<String> basesDeDatos) {
        this.basesDeDatos = basesDeDatos;
    }
}
