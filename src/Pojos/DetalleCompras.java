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
    private double importe;
    private String unidmed;
    
    private double precio1;
    private double precio2;
    private double precio3;
    private double precio4;
    
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

    public double getPrecio4() {
        return precio4;
    }

    public void setPrecio4(double precio4) {
        this.precio4 = precio4;
    }
    
    

    public String getUnidmed() {
        return unidmed;
    }

    public void setUnidmed(String unidmed) {
        this.unidmed = unidmed;
    }
    
    

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
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
