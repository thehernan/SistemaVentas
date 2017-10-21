/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.sql.Date;

/**
 *
 * @author info2017
 */
public class Merma {
    private long id_merma;
    private String motivo;
    private Date fecha;
    private String estado;

    public Merma() {
    }

    public Merma(long id_merma, String motivo, Date fecha, String estado) {
        this.id_merma = id_merma;
        this.motivo = motivo;
        this.fecha = fecha;
        this.estado = estado;
    }

    

    public long getId_merma() {
        return id_merma;
    }

    public void setId_merma(long id_merma) {
        this.id_merma = id_merma;
    }

   

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
