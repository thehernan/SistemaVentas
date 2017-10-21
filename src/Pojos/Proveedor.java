/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

/**
 *
 * @author info2017
 */
public class Proveedor {
    private String nombrerazons;
    private String rut;
    private String direccion;
    private String celular;
    private String email;
    private long idproveedor;

    public String getNombrerazons() {
        return nombrerazons;
    }

    public void setNombrerazons(String nombrerazons) {
        this.nombrerazons = nombrerazons;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(long idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Proveedor(String nombrerazons, String rut, String direccion, String celular, String email, long idproveedor) {
        this.nombrerazons = nombrerazons;
        this.rut = rut;
        this.direccion = direccion;
        this.celular = celular;
        this.email = email;
        this.idproveedor = idproveedor;
    }

    public Proveedor() {
    }

}
