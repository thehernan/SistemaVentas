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
public class DetalleVenta {
    private long iddetalleventa;
    private long idproducto;
    private double precio;
    private double cantidad;
    private long idventa;

    public DetalleVenta() {
    }

    public DetalleVenta(long iddetalleventa, long idproducto, double precio, double cantidad, long idventa) {
        this.iddetalleventa = iddetalleventa;
        this.idproducto = idproducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idventa = idventa;
    }

    public long getIddetalleventa() {
        return iddetalleventa;
    }

    public void setIddetalleventa(long iddetalleventa) {
        this.iddetalleventa = iddetalleventa;
    }

    public long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(long idproducto) {
        this.idproducto = idproducto;
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

    public long getIdventa() {
        return idventa;
    }

    public void setIdventa(long idventa) {
        this.idventa = idventa;
    }
    
    
}
