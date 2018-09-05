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
public class Cabecera {
    private int tipo_comprobante;
    private String serie;
    private String numero;
    private int sunat_t;
    private int cliente_tipo_doc;
    private long cliente_num_doc;
    private String cliente_rs;
    private String cliente_direc;
    private String cliente_email;
    private String fechaemision;
    private int monedaop;
    private double moneda_tipoc;
    private double porcentaje_igv;
    private double totalgrav;
    private double totaligv;
    private double total;
    private boolean detraccion;
    private int tipo_doc_modifica;
    private String doc_modifica_serie;
    private String doc_modifica_numero;
    private int tipo_nota_cred;
    private int tipo_nota_deb;
    private boolean enviarautomaticamentesunat;
    private boolean enviarautomaticamentecliente;

    public Cabecera() {
    }

    public Cabecera(int tipo_comprobante, String serie, String numero, int sunat_t, int cliente_tipo_doc, long cliente_num_doc, String cliente_rs, String cliente_direc, String cliente_email, String fechaemision, int monedaop, double moneda_tipoc, double porcentaje_igv, double totalgrav, double totaligv, double total, boolean detraccion, int tipo_doc_modifica, String doc_modifica_serie, String doc_modifica_numero, int tipo_nota_cred, int tipo_nota_deb, boolean enviarautomaticamentesunat, boolean enviarautomaticamentecliente) {
        this.tipo_comprobante = tipo_comprobante;
        this.serie = serie;
        this.numero = numero;
        this.sunat_t = sunat_t;
        this.cliente_tipo_doc = cliente_tipo_doc;
        this.cliente_num_doc = cliente_num_doc;
        this.cliente_rs = cliente_rs;
        this.cliente_direc = cliente_direc;
        this.cliente_email = cliente_email;
        this.fechaemision = fechaemision;
        this.monedaop = monedaop;
        this.moneda_tipoc = moneda_tipoc;
        this.porcentaje_igv = porcentaje_igv;
        this.totalgrav = totalgrav;
        this.totaligv = totaligv;
        this.total = total;
        this.detraccion = detraccion;
        this.tipo_doc_modifica = tipo_doc_modifica;
        this.doc_modifica_serie = doc_modifica_serie;
        this.doc_modifica_numero = doc_modifica_numero;
        this.tipo_nota_cred = tipo_nota_cred;
        this.tipo_nota_deb = tipo_nota_deb;
        this.enviarautomaticamentesunat = enviarautomaticamentesunat;
        this.enviarautomaticamentecliente = enviarautomaticamentecliente;
    }

    public int getTipo_comprobante() {
        return tipo_comprobante;
    }

    public void setTipo_comprobante(int tipo_comprobante) {
        this.tipo_comprobante = tipo_comprobante;
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

    public int getSunat_t() {
        return sunat_t;
    }

    public void setSunat_t(int sunat_t) {
        this.sunat_t = sunat_t;
    }

    public int getCliente_tipo_doc() {
        return cliente_tipo_doc;
    }

    public void setCliente_tipo_doc(int cliente_tipo_doc) {
        this.cliente_tipo_doc = cliente_tipo_doc;
    }

    public long getCliente_num_doc() {
        return cliente_num_doc;
    }

    public void setCliente_num_doc(long cliente_num_doc) {
        this.cliente_num_doc = cliente_num_doc;
    }

    public String getCliente_rs() {
        return cliente_rs;
    }

    public void setCliente_rs(String cliente_rs) {
        this.cliente_rs = cliente_rs;
    }

    public String getCliente_direc() {
        return cliente_direc;
    }

    public void setCliente_direc(String cliente_direc) {
        this.cliente_direc = cliente_direc;
    }

    public String getCliente_email() {
        return cliente_email;
    }

    public void setCliente_email(String cliente_email) {
        this.cliente_email = cliente_email;
    }

    public String getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(String fechaemision) {
        this.fechaemision = fechaemision;
    }

    public int getMonedaop() {
        return monedaop;
    }

    public void setMonedaop(int monedaop) {
        this.monedaop = monedaop;
    }

    public double getMoneda_tipoc() {
        return moneda_tipoc;
    }

    public void setMoneda_tipoc(double moneda_tipoc) {
        this.moneda_tipoc = moneda_tipoc;
    }

    public double getPorcentaje_igv() {
        return porcentaje_igv;
    }

    public void setPorcentaje_igv(double porcentaje_igv) {
        this.porcentaje_igv = porcentaje_igv;
    }

    public double getTotalgrav() {
        return totalgrav;
    }

    public void setTotalgrav(double totalgrav) {
        this.totalgrav = totalgrav;
    }

    public double getTotaligv() {
        return totaligv;
    }

    public void setTotaligv(double totaligv) {
        this.totaligv = totaligv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isDetraccion() {
        return detraccion;
    }

    public void setDetraccion(boolean detraccion) {
        this.detraccion = detraccion;
    }

    public int getTipo_doc_modifica() {
        return tipo_doc_modifica;
    }

    public void setTipo_doc_modifica(int tipo_doc_modifica) {
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

    public int getTipo_nota_cred() {
        return tipo_nota_cred;
    }

    public void setTipo_nota_cred(int tipo_nota_cred) {
        this.tipo_nota_cred = tipo_nota_cred;
    }

    public int getTipo_nota_deb() {
        return tipo_nota_deb;
    }

    public void setTipo_nota_deb(int tipo_nota_deb) {
        this.tipo_nota_deb = tipo_nota_deb;
    }

    public boolean isEnviarautomaticamentesunat() {
        return enviarautomaticamentesunat;
    }

    public void setEnviarautomaticamentesunat(boolean enviarautomaticamentesunat) {
        this.enviarautomaticamentesunat = enviarautomaticamentesunat;
    }

    public boolean isEnviarautomaticamentecliente() {
        return enviarautomaticamentecliente;
    }

    public void setEnviarautomaticamentecliente(boolean enviarautomaticamentecliente) {
        this.enviarautomaticamentecliente = enviarautomaticamentecliente;
    }
    
}
