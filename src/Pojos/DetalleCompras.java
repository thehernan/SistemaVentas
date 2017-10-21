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
public class DetalleCompras {
    private long iddetallecompra; 
    private long idproducto;
    private double cantidad;
    private double precio;
    private long idcompra;
    private double cantidadacord;
    public DetalleCompras() {
    }

    public DetalleCompras(long iddetallecompra, long idproducto, double cantidad, double precio, long idcompra, double cantidadacord) {
        this.iddetallecompra = iddetallecompra;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idcompra = idcompra;
        this.cantidadacord = cantidadacord;
    }

    public double getCantidadacord() {
        return cantidadacord;
    }

    public void setCantidadacord(double cantidadacord) {
        this.cantidadacord = cantidadacord;
    }

    

    public long getIddetallecompra() {
        return iddetallecompra;
    }

    public void setIddetallecompra(long iddetallecompra) {
        this.iddetallecompra = iddetallecompra;
    }

    public long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(long idproducto) {
        this.idproducto = idproducto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public long getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(long idcompra) {
        this.idcompra = idcompra;
    }
    
    
}
