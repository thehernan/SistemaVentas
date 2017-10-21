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
public class PrecioReparacion {
    private long id;
    private String dispositivo;
    private double precio;
    private double preciorevision;

    public PrecioReparacion() {
    }

    public PrecioReparacion(long id, String dispositivo, double precio, double preciorevision) {
        this.id = id;
        this.dispositivo = dispositivo;
        this.precio = precio;
        this.preciorevision = preciorevision;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPreciorevision() {
        return preciorevision;
    }

    public void setPreciorevision(double preciorevision) {
        this.preciorevision = preciorevision;
    }
    
    
    
}
