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
public class Reparacion {
    private long idreparacion;
    private long idcliente;
    private String cliente;
    private String empleado;
    private long idempleado;
    private String atendido;
    private String marca;
    private String modelo;
    private String fallas;
    private String causas;
    private String observacion;
    private String diagnostico;
    private Timestamp fechaR;
//    private Date fechaA;
    private String fechaE;
    private String estado;
    private byte[] foto;
    private String entregado;
    private Double precio;
    private String codigo;
    private String hora;
    private long id_sucural;
    private double preciorevision;
    private double descuento;
    private String motivo;
    private double total;
    private double abono;
    private double importe;
    private double subtotal;
    private double igv;
  
    
    public Reparacion() {
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

   

    public double getPreciorevision() {
        return preciorevision;
    }

    public void setPreciorevision(double preciorevision) {
        this.preciorevision = preciorevision;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

   

    public long getId_sucural() {
        return id_sucural;
    }

    public void setId_sucural(long id_sucural) {
        this.id_sucural = id_sucural;
    }

    

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

   

    public String getEntregado() {
        return entregado;
    }

    public void setEntregado(String entregado) {
        this.entregado = entregado;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    

    public long getIdreparacion() {
        return idreparacion;
    }

    public void setIdreparacion(long idreparacion) {
        this.idreparacion = idreparacion;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(long idempleado) {
        this.idempleado = idempleado;
    }

    public String getAtendido() {
        return atendido;
    }

    public void setAtendido(String atendido) {
        this.atendido = atendido;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFallas() {
        return fallas;
    }

    public void setFallas(String fallas) {
        this.fallas = fallas;
    }

    public String getCausas() {
        return causas;
    }

    public void setCausas(String causas) {
        this.causas = causas;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Timestamp getFechaR() {
        return fechaR;
    }

    public void setFechaR(Timestamp fechaR) {
        this.fechaR = fechaR;
    }

    public String getFechaE() {
        return fechaE;
    }

    public void setFechaE(String fechaE) {
        this.fechaE = fechaE;
    }

    
   

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
}
