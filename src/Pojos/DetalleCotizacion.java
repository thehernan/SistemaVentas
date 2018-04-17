/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

/**
 *
 * @author HERNAN
 */
public class DetalleCotizacion {
    private long id;
    private double precioprod;
    private long idcotiza;
    private long idprod;
    private double cantidad;

    public DetalleCotizacion() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrecioprod() {
        return precioprod;
    }

    public void setPrecioprod(double precioprod) {
        this.precioprod = precioprod;
    }

    public long getIdcotiza() {
        return idcotiza;
    }

    public void setIdcotiza(long idcotiza) {
        this.idcotiza = idcotiza;
    }

    public long getIdprod() {
        return idprod;
    }

    public void setIdprod(long idprod) {
        this.idprod = idprod;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
}
