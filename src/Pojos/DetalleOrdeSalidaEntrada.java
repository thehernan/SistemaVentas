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
public class DetalleOrdeSalidaEntrada {
    private long id;
    private double cantidad;
    private long idproducto;
    private long idordensalidaentrada;

    public DetalleOrdeSalidaEntrada() {
    }

    public DetalleOrdeSalidaEntrada(long id, double cantidad, long idproducto, long idordensalidaentrada) {
        this.id = id;
        this.cantidad = cantidad;
        this.idproducto = idproducto;
        this.idordensalidaentrada = idordensalidaentrada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getIdordensalidaentrada() {
        return idordensalidaentrada;
    }

    public void setIdordensalidaentrada(long idordensalidaentrada) {
        this.idordensalidaentrada = idordensalidaentrada;
    }
    
    
    
}
