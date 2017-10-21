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
public class DetalleMerma {
    private long iddetallemerma;
    private long idproducto;
    private double cantidad;
    private long idmerma;

    public DetalleMerma(long iddetallemerma, long idproducto, double cantidad, long idmerma) {
        this.iddetallemerma = iddetallemerma;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.idmerma = idmerma;
    }

    public DetalleMerma() {
    }

    public long getIddetallemerma() {
        return iddetallemerma;
    }

    public void setIddetallemerma(long iddetallemerma) {
        this.iddetallemerma = iddetallemerma;
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

    public long getIdmerma() {
        return idmerma;
    }

    public void setIdmerma(long idmerma) {
        this.idmerma = idmerma;
    }
    
    
}
