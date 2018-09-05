/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;


import java.sql.Timestamp;

/**
 *
 * @author info2017
 */
public class Caja {
      private long id_caja;
    private long id_empleado;
    private Timestamp fechahora_apertura;
    private Timestamp fechahora_cierre;
    private double aperturadinero;
    private double cierradinero;
    private String estado;
    private String descripcion;
    private long id_sucursal;
    private double total;
    private String cajero;

    public Caja(long id_caja, long id_empleado, Timestamp fechahora_apertura, Timestamp fechahora_cierre, double aperturadinero, double cierradinero, String estado, String descripcion, long id_sucursal) {
        this.id_caja = id_caja;
        this.id_empleado = id_empleado;
        this.fechahora_apertura = fechahora_apertura;
        this.fechahora_cierre = fechahora_cierre;
        this.aperturadinero = aperturadinero;
        this.cierradinero = cierradinero;
        this.estado = estado;
        this.descripcion = descripcion;
        this.id_sucursal = id_sucursal;
    }

    public long getId_sucursal() {
        return id_sucursal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

    public void setId_sucursal(long id_sucursal) {
        this.id_sucursal = id_sucursal;
    }
    
    

    public Caja() {
    }

    public long getId_caja() {
        return id_caja;
    }

    public void setId_caja(long id_caja) {
        this.id_caja = id_caja;
    }

    public long getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(long id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Timestamp getFechahora_apertura() {
        return fechahora_apertura;
    }

    public void setFechahora_apertura(Timestamp fechahora_apertura) {
        this.fechahora_apertura = fechahora_apertura;
    }

    public Timestamp getFechahora_cierre() {
        return fechahora_cierre;
    }

    public void setFechahora_cierre(Timestamp fechahora_cierre) {
        this.fechahora_cierre = fechahora_cierre;
    }

    public double getAperturadinero() {
        return aperturadinero;
    }

    public void setAperturadinero(double aperturadinero) {
        this.aperturadinero = aperturadinero;
    }

    public double getCierradinero() {
        return cierradinero;
    }

    public void setCierradinero(double cierradinero) {
        this.cierradinero = cierradinero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
