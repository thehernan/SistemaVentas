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
public class Compras {
    private long id_compra;
    private String documento;
    private String numero;
    private long id_proveedor;
    private String proveedor;
    private String docprov;
    private double descuento_global;
    private Date fecha;
    private long id_sucursal;
    private String estado;
    private String tipopago;
    private double abono;
    private long idcomprobant;
    private String comprobante;
    private long idmoneda;
    private String moneda;
    private String abrevimoneda;
    private double tipocambio;
    private boolean  incluyeigv;
    public Compras() {
    }

    public String getProveedor() {
        return proveedor;
    }

    public double getTipocambio() {
        return tipocambio;
    }

    public void setTipocambio(double tipocambio) {
        this.tipocambio = tipocambio;
    }

    public boolean isIncluyeigv() {
        return incluyeigv;
    }

    public void setIncluyeigv(boolean incluyeigv) {
        this.incluyeigv = incluyeigv;
    }

    

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getDocprov() {
        return docprov;
    }

    public void setDocprov(String docprov) {
        this.docprov = docprov;
    }

    public long getIdcomprobant() {
        return idcomprobant;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public long getIdmoneda() {
        return idmoneda;
    }

    public void setIdmoneda(long idmoneda) {
        this.idmoneda = idmoneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getAbrevimoneda() {
        return abrevimoneda;
    }

    public void setAbrevimoneda(String abrevimoneda) {
        this.abrevimoneda = abrevimoneda;
    }

    public void setIdcomprobant(long idcomprobant) {
        this.idcomprobant = idcomprobant;
    }

    public Compras(long id_compra, String documento, String numero, long id_proveedor, double descuento_global, Date fecha, long id_sucursal, String estado, String tipopago, double abono) {
        this.id_compra = id_compra;
        this.documento = documento;
        this.numero = numero;
        this.id_proveedor = id_proveedor;
        this.descuento_global = descuento_global;
        this.fecha = fecha;
        this.id_sucursal = id_sucursal;
        this.estado = estado;
        this.tipopago = tipopago;
        this.abono = abono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    

    public long getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(long id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

   

    public long getId_compra() {
        return id_compra;
    }

    public void setId_compra(long id_compra) {
        this.id_compra = id_compra;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public long getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(long id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public double getDescuento_global() {
        return descuento_global;
    }

    public void setDescuento_global(double descuento_global) {
        this.descuento_global = descuento_global;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   

}
