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
public class DetalleCaja {
    private long iddetallecaja;
    private long idventa;
    private long idreparacion;
    private String documento;
    private String numero;
    private long idcaja;
    private double pagocon;
    private double abono;
    private boolean anulada;
    private String motivoanulacion;
    private String entrega;
   
    public DetalleCaja() {
    }

    public DetalleCaja(long iddetallecaja, long idventa, long idreparacion, String documento, String numero, long idcaja, double pagocon, double abono, boolean anulada, String motivoanulacion) {
        this.iddetallecaja = iddetallecaja;
        this.idventa = idventa;
        this.idreparacion = idreparacion;
        this.documento = documento;
        this.numero = numero;
        this.idcaja = idcaja;
        this.pagocon = pagocon;
        this.abono = abono;
        this.anulada = anulada;
        this.motivoanulacion = motivoanulacion;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

   

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public String getMotivoanulacion() {
        return motivoanulacion;
    }

    public void setMotivoanulacion(String motivoanulacion) {
        this.motivoanulacion = motivoanulacion;
    }

   

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

   

    public long getIdreparacion() {
        return idreparacion;
    }

    public void setIdreparacion(long idreparacion) {
        this.idreparacion = idreparacion;
    }

    

    public long getIddetallecaja() {
        return iddetallecaja;
    }

    public void setIddetallecaja(long iddetallecaja) {
        this.iddetallecaja = iddetallecaja;
    }

    public long getIdventa() {
        return idventa;
    }

    public void setIdventa(long idventa) {
        this.idventa = idventa;
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

    public long getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(long idcaja) {
        this.idcaja = idcaja;
    }

    public double getPagocon() {
        return pagocon;
    }

    public void setPagocon(double pagocon) {
        this.pagocon = pagocon;
    }
    
    
    
}
