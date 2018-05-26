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
public class Cliente {
    private long id_cliente;
    private String nombre_razons;
    private String rut;
    private String direccion;
    private String celular;
    private String email;
    private long idtipodoc;
    private String documento;

    public Cliente() {
    }

    public Cliente(long id_cliente, String nombre_razons, String rut, String direccion, String celular, String email, long idtipodoc, String documento) {
        this.id_cliente = id_cliente;
        this.nombre_razons = nombre_razons;
        this.rut = rut;
        this.direccion = direccion;
        this.celular = celular;
        this.email = email;
        this.idtipodoc = idtipodoc;
        this.documento = documento;
    }

    public long getIdtipodoc() {
        return idtipodoc;
    }

    public void setIdtipodoc(long idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

   

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_razons() {
        return nombre_razons;
    }

    public void setNombre_razons(String nombre_razons) {
        this.nombre_razons = nombre_razons;
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
   
}
