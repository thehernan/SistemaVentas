/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion;

/**
 *
 * @author HERNAN
 */
public class Detalle {
    
    private String unidadm;
    private String codigo;
    private String detalleprod;
    private double cantidad;
    private double valorunitario;
    private double preciounitario;
    private double descuento;
    private double subtotal;
    private int tipoigv;
    private double igv;
    private double total;
    private boolean anticipo_reg;
    private String anticiposerie;
    private String anticipo_doc_num;

    public Detalle() {
    }

    public Detalle(String unidadm, String codigo, String detalleprod, double cantidad, double valorunitario, double preciounitario, double descuento, double subtotal, int tipoigv, double igv, double total, boolean anticipo_reg, String anticiposerie, String anticipo_doc_num) {
        this.unidadm = unidadm;
        this.codigo = codigo;
        this.detalleprod = detalleprod;
        this.cantidad = cantidad;
        this.valorunitario = valorunitario;
        this.preciounitario = preciounitario;
        this.descuento = descuento;
        this.subtotal = subtotal;
        this.tipoigv = tipoigv;
        this.igv = igv;
        this.total = total;
        this.anticipo_reg = anticipo_reg;
        this.anticiposerie = anticiposerie;
        this.anticipo_doc_num = anticipo_doc_num;
    }

    public String getUnidadm() {
        return unidadm;
    }

    public void setUnidadm(String unidadm) {
        this.unidadm = unidadm;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDetalleprod() {
        return detalleprod;
    }

    public void setDetalleprod(String detalleprod) {
        this.detalleprod = detalleprod;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(double valorunitario) {
        this.valorunitario = valorunitario;
    }

    public double getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(double preciounitario) {
        this.preciounitario = preciounitario;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getTipoigv() {
        return tipoigv;
    }

    public void setTipoigv(int tipoigv) {
        this.tipoigv = tipoigv;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isAnticipo_reg() {
        return anticipo_reg;
    }

    public void setAnticipo_reg(boolean anticipo_reg) {
        this.anticipo_reg = anticipo_reg;
    }

    public String getAnticiposerie() {
        return anticiposerie;
    }

    public void setAnticiposerie(String anticiposerie) {
        this.anticiposerie = anticiposerie;
    }

    public String getAnticipo_doc_num() {
        return anticipo_doc_num;
    }

    public void setAnticipo_doc_num(String anticipo_doc_num) {
        this.anticipo_doc_num = anticipo_doc_num;
    }
    
}
