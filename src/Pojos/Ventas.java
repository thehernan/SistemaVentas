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
public class Ventas {
    private long idventa;
    private String codigo;
//    private String documento;
//    private String numero;
    private long idcliente;
    private Date fecha;
    private long idempleado;
//    private long idcaja;
    private String estado;
    private long id_sucursal;
    private double descuento;
    private String motivodescuento;
    public Ventas() {
    }

    public Ventas(long idventa, String codigo, long idcliente, Date fecha, long idempleado, String estado, long id_sucursal, double descuento) {
        this.idventa = idventa;
        this.codigo = codigo;
        this.idcliente = idcliente;
        this.fecha = fecha;
        this.idempleado = idempleado;
        this.estado = estado;
        this.id_sucursal = id_sucursal;
        this.descuento = descuento;
    }

    public String getMotivodescuento() {
        return motivodescuento;
    }

    public void setMotivodescuento(String motivodescuento) {
        this.motivodescuento = motivodescuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

   

    public long getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(long id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

   
    public long getIdventa() {
        return idventa;
    }

    public void setIdventa(long idventa) {
        this.idventa = idventa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

//    public String getDocumento() {
//        return documento;
//    }
//
//    public void setDocumento(String documento) {
//        this.documento = documento;
//    }
//
//    public String getNumero() {
//        return numero;
//    }
//
//    public void setNumero(String numero) {
//        this.numero = numero;
//    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(long idempleado) {
        this.idempleado = idempleado;
    }
//
//    public long getIdcaja() {
//        return idcaja;
//    }
//
//    public void setIdcaja(long idcaja) {
//        this.idcaja = idcaja;
//    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
