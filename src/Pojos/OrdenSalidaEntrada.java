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
public class OrdenSalidaEntrada {
    private long idordensalidaentrada;
    private String sucurenvia;
    private String sucursolicita;
    private Timestamp fecha_pedido;
    private Timestamp fecha_entrega;
    private String numero;
   
    private String autorizado;
    private String recibido;
    private String tipoop;
    private Long idsucurenvia;
    private Long idsucurrecep;
   

    public OrdenSalidaEntrada(long idordensalidaentrada, String sucurenvia, String sucursolicita, Timestamp fecha_pedido, Timestamp fecha_entrega, String numero, String autorizado, String recibido, String tipoop) {
        this.idordensalidaentrada = idordensalidaentrada;
        this.sucurenvia = sucurenvia;
        this.sucursolicita = sucursolicita;
        this.fecha_pedido = fecha_pedido;
        this.fecha_entrega = fecha_entrega;
        this.numero = numero;
        this.autorizado = autorizado;
        this.recibido = recibido;
        this.tipoop = tipoop;
    }

   

    public String getTipoop() {
        return tipoop;
    }

    public void setTipoop(String tipoop) {
        this.tipoop = tipoop;
    }

    public Long getIdsucurenvia() {
        return idsucurenvia;
    }

    public void setIdsucurenvia(Long idsucurenvia) {
        this.idsucurenvia = idsucurenvia;
    }

    public Long getIdsucurrecep() {
        return idsucurrecep;
    }

    public void setIdsucurrecep(Long idsucurrecep) {
        this.idsucurrecep = idsucurrecep;
    }

    
    

    public OrdenSalidaEntrada() {
    }

    public long getIdordensalidaentrada() {
        return idordensalidaentrada;
    }

    public void setIdordensalidaentrada(long idordensalidaentrada) {
        this.idordensalidaentrada = idordensalidaentrada;
    }

    public String getSucurenvia() {
        return sucurenvia;
    }

    public void setSucurenvia(String sucurenvia) {
        this.sucurenvia = sucurenvia;
    }

    public String getSucursolicita() {
        return sucursolicita;
    }

    public void setSucursolicita(String sucursolicita) {
        this.sucursolicita = sucursolicita;
    }

    public Timestamp getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Timestamp fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Timestamp getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Timestamp fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

   
    public String getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }

    public String getRecibido() {
        return recibido;
    }

    public void setRecibido(String recibido) {
        this.recibido = recibido;
    }
    
    
}
