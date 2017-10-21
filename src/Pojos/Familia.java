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
public class Familia {
    private long idfamilia;
    private String descripcion;
    private String observacion;

    public Familia() {
    }

    public Familia(long idfamilia, String descripcion, String observacion) {
        this.idfamilia = idfamilia;
        this.descripcion = descripcion;
        this.observacion = observacion;
    }

    public long getIdfamilia() {
        return idfamilia;
    }

    public void setIdfamilia(long idfamilia) {
        this.idfamilia = idfamilia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
}
