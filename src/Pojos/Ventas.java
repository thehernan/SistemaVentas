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
    
    private String clientetipodoc;
    private String clientenumdoc;
    private String clienteRS;
    private String clientedirec;
    private String clienteemail;
    
    
    private Date fecha;
    private long idempleado;
//    private long idcaja;
    private String estado;
    private long id_sucursal;
    private double descuento;
    private String motivodescuento;
    private String motivo;
    private long idcomprobante;
    private int comprobante;
    private String serie;
    private String numero;
    private long idsunattrans;
    private String fechaven;
    private int porcentajeigv;
    private double descuentoglob;
    private double totaldesc;
    private double totalanticipo;
    private double totalgravada;
    private double totalinfecta;
    private double totalexonerada;
    private double totaligv;
    private double totalgratuita;
    private double totalotrosc;
    private double percepcionbase;
    private double totalpercep;
    private double totalincluido;
    private boolean detraccion;
    private String observacion;
    private String condicionpago;
    private String enlace;
    private boolean aceptadasunat;
    private String sunatdescrip;
    private String sunatnote;
    private String suantresposeode;
    private String sunatsoap;
    private String qr;
    private String codhash;
    private String codbarra;
    private String sunatticket;
    private long idpercepcion;
    private String estadosunat;
    
    private long idmoneda;
    private int modenaop;
    private String abreviaturamoneda;
    private long idtiponota;
    private String comprobanteref;
    private double tipocambio;
    private String tipo_doc_modifica;
    private String doc_modifica_serie;
    private String doc_modifica_numero;
    private String tipo_nota_cred;
    private String tipo_nota_deb;
    
    private String pdf_zip_base64;
    private String xml_zip_base64;
    private String cdr_zip_base64;
    
    
    private double iva;
    private double total;
    private double subtotal;
    private String docref;
    
    public Ventas() {
    }

    public String getPdf_zip_base64() {
        return pdf_zip_base64;
    }

    public void setPdf_zip_base64(String pdf_zip_base64) {
        this.pdf_zip_base64 = pdf_zip_base64;
    }

    public String getXml_zip_base64() {
        return xml_zip_base64;
    }

    public void setXml_zip_base64(String xml_zip_base64) {
        this.xml_zip_base64 = xml_zip_base64;
    }

    public String getCdr_zip_base64() {
        return cdr_zip_base64;
    }

    public void setCdr_zip_base64(String cdr_zip_base64) {
        this.cdr_zip_base64 = cdr_zip_base64;
    }

    public int getPorcentajeigv() {
        return porcentajeigv;
    }

    public void setPorcentajeigv(int porcentajeigv) {
        this.porcentajeigv = porcentajeigv;
    }

    public String getTipo_doc_modifica() {
        return tipo_doc_modifica;
    }

    public String getAbreviaturamoneda() {
        return abreviaturamoneda;
    }

    public void setAbreviaturamoneda(String abreviaturamoneda) {
        this.abreviaturamoneda = abreviaturamoneda;
    }

    public void setTipo_doc_modifica(String tipo_doc_modifica) {
        this.tipo_doc_modifica = tipo_doc_modifica;
    }

    public String getDoc_modifica_serie() {
        return doc_modifica_serie;
    }

    public void setDoc_modifica_serie(String doc_modifica_serie) {
        this.doc_modifica_serie = doc_modifica_serie;
    }

    public String getDoc_modifica_numero() {
        return doc_modifica_numero;
    }

    public void setDoc_modifica_numero(String doc_modifica_numero) {
        this.doc_modifica_numero = doc_modifica_numero;
    }

    public String getTipo_nota_cred() {
        return tipo_nota_cred;
    }

    public void setTipo_nota_cred(String tipo_nota_cred) {
        this.tipo_nota_cred = tipo_nota_cred;
    }

    public String getTipo_nota_deb() {
        return tipo_nota_deb;
    }

    public void setTipo_nota_deb(String tipo_nota_deb) {
        this.tipo_nota_deb = tipo_nota_deb;
    }

    public int getComprobante() {
        return comprobante;
    }

    public void setComprobante(int comprobante) {
        this.comprobante = comprobante;
    }

    public String getClientetipodoc() {
        return clientetipodoc;
    }

    public void setClientetipodoc(String clientetipodoc) {
        this.clientetipodoc = clientetipodoc;
    }


    public String getClientenumdoc() {
        return clientenumdoc;
    }

    public void setClientenumdoc(String clientenumdoc) {
        this.clientenumdoc = clientenumdoc;
    }

    public String getClienteRS() {
        return clienteRS;
    }

    public void setClienteRS(String clienteRS) {
        this.clienteRS = clienteRS;
    }

    public String getClientedirec() {
        return clientedirec;
    }

    public void setClientedirec(String clientedirec) {
        this.clientedirec = clientedirec;
    }

    public String getClienteemail() {
        return clienteemail;
    }

    public void setClienteemail(String clienteemail) {
        this.clienteemail = clienteemail;
    }

    public int getModenaop() {
        return modenaop;
    }

    public void setModenaop(int modenaop) {
        this.modenaop = modenaop;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDocref() {
        return docref;
    }

    public void setDocref(String docref) {
        this.docref = docref;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getTipocambio() {
        return tipocambio;
    }

    public void setTipocambio(double tipocambio) {
        this.tipocambio = tipocambio;
    }

    public long getIdcomprobante() {
        return idcomprobante;
    }

    public void setIdcomprobante(long idcomprobante) {
        this.idcomprobante = idcomprobante;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public long getIdsunattrans() {
        return idsunattrans;
    }

    public void setIdsunattrans(long idsunattrans) {
        this.idsunattrans = idsunattrans;
    }

    public String getFechaven() {
        return fechaven;
    }

    public void setFechaven(String fechaven) {
        this.fechaven = fechaven;
    }

    

    public double getDescuentoglob() {
        return descuentoglob;
    }

    public void setDescuentoglob(double descuentoglob) {
        this.descuentoglob = descuentoglob;
    }

    public double getTotaldesc() {
        return totaldesc;
    }

    public void setTotaldesc(double totaldesc) {
        this.totaldesc = totaldesc;
    }

    public double getTotalanticipo() {
        return totalanticipo;
    }

    public void setTotalanticipo(double totalanticipo) {
        this.totalanticipo = totalanticipo;
    }

    public double getTotalgravada() {
        return totalgravada;
    }

    public void setTotalgravada(double totalgravada) {
        this.totalgravada = totalgravada;
    }

    public double getTotalinfecta() {
        return totalinfecta;
    }

    public void setTotalinfecta(double totalinfecta) {
        this.totalinfecta = totalinfecta;
    }

    public double getTotalexonerada() {
        return totalexonerada;
    }

    public void setTotalexonerada(double totalexonerada) {
        this.totalexonerada = totalexonerada;
    }

    public double getTotaligv() {
        return totaligv;
    }

    public void setTotaligv(double totaligv) {
        this.totaligv = totaligv;
    }

    public double getTotalgratuita() {
        return totalgratuita;
    }

    public void setTotalgratuita(double totalgratuita) {
        this.totalgratuita = totalgratuita;
    }

    public double getTotalotrosc() {
        return totalotrosc;
    }

    public void setTotalotrosc(double totalotrosc) {
        this.totalotrosc = totalotrosc;
    }

    public double getPercepcionbase() {
        return percepcionbase;
    }

    public void setPercepcionbase(double percepcionbase) {
        this.percepcionbase = percepcionbase;
    }

    public double getTotalpercep() {
        return totalpercep;
    }

    public void setTotalpercep(double totalpercep) {
        this.totalpercep = totalpercep;
    }

    public double getTotalincluido() {
        return totalincluido;
    }

    public void setTotalincluido(double totalincluido) {
        this.totalincluido = totalincluido;
    }

    public boolean isDetraccion() {
        return detraccion;
    }

    public void setDetraccion(boolean detraccion) {
        this.detraccion = detraccion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCondicionpago() {
        return condicionpago;
    }

    public void setCondicionpago(String condicionpago) {
        this.condicionpago = condicionpago;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public boolean isAceptadasunat() {
        return aceptadasunat;
    }

    public void setAceptadasunat(boolean aceptadasunat) {
        this.aceptadasunat = aceptadasunat;
    }

    public String getSunatdescrip() {
        return sunatdescrip;
    }

    public void setSunatdescrip(String sunatdescrip) {
        this.sunatdescrip = sunatdescrip;
    }

    public String getSunatnote() {
        return sunatnote;
    }

    public void setSunatnote(String sunatnote) {
        this.sunatnote = sunatnote;
    }

    public String getSuantresposeode() {
        return suantresposeode;
    }

    public void setSuantresposeode(String suantresposeode) {
        this.suantresposeode = suantresposeode;
    }

    public String getSunatsoap() {
        return sunatsoap;
    }

    public void setSunatsoap(String sunatsoap) {
        this.sunatsoap = sunatsoap;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getCodhash() {
        return codhash;
    }

    public void setCodhash(String codhash) {
        this.codhash = codhash;
    }

    public String getCodbarra() {
        return codbarra;
    }

    public void setCodbarra(String codbarra) {
        this.codbarra = codbarra;
    }

    public String getSunatticket() {
        return sunatticket;
    }

    public void setSunatticket(String sunatticket) {
        this.sunatticket = sunatticket;
    }

    public long getIdpercepcion() {
        return idpercepcion;
    }

    public void setIdpercepcion(long idpercepcion) {
        this.idpercepcion = idpercepcion;
    }

    public String getEstadosunat() {
        return estadosunat;
    }

    public void setEstadosunat(String estadosunat) {
        this.estadosunat = estadosunat;
    }

    public long getIdmoneda() {
        return idmoneda;
    }

    public void setIdmoneda(long idmoneda) {
        this.idmoneda = idmoneda;
    }

    public long getIdtiponota() {
        return idtiponota;
    }

    public void setIdtiponota(long idtiponota) {
        this.idtiponota = idtiponota;
    }

    public String getComprobanteref() {
        return comprobanteref;
    }

    public void setComprobanteref(String comprobanteref) {
        this.comprobanteref = comprobanteref;
    }

   

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
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
