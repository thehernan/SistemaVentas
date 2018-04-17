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
public class Producto {
    private String codigo;
    private String descripcion;
    private String observacion;
    private long idfamilia;
    
    private double precio;
    private double cantidad;
    private byte[] foto;
    private long idproducto;
    private String descripfamilia;
    
    private double margenG;
    private long id_sucursal;
    private boolean select;
    private double precio1;
    private double precio2;
    private double precio3;
    private String moneda;
    public Producto() {
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getPrecio1() {
        return precio1;
    }

    public void setPrecio1(double precio1) {
        this.precio1 = precio1;
    }

    public double getPrecio2() {
        return precio2;
    }

    public void setPrecio2(double precio2) {
        this.precio2 = precio2;
    }

    public double getPrecio3() {
        return precio3;
    }

    public void setPrecio3(double precio3) {
        this.precio3 = precio3;
    }



    public long getId_sucursal() {
        return id_sucursal;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public void setId_sucursal(long id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    

    public double getMargenG() {
        return margenG;
    }

    public void setMargenG(double margenG) {
        this.margenG = margenG;
    }

    

    public String getDescripfamilia() {
        return descripfamilia;
    }

    public void setDescripfamilia(String descripfamilia) {
        this.descripfamilia = descripfamilia;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public long getIdfamilia() {
        return idfamilia;
    }

    public void setIdfamilia(long idfamilia) {
        this.idfamilia = idfamilia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(long idproducto) {
        this.idproducto = idproducto;
    }
    
    
}
