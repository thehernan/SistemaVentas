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
public class Asistencia {
    private long id_asistencia;
    private Date fecha;
    private String descripcion;
    private String observacion;
    private long id_empleado;

    public Asistencia() {
    }

    public Asistencia(long id_asistencia, Date fecha, String descripcion, String observacion, long id_empleado) {
        this.id_asistencia = id_asistencia;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.id_empleado = id_empleado;
    }

    public long getId_asistencia() {
        return id_asistencia;
    }

    public void setId_asistencia(long id_asistencia) {
        this.id_asistencia = id_asistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public long getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(long id_empleado) {
        this.id_empleado = id_empleado;
    }
    
    
}
