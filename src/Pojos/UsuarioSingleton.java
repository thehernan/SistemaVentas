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
public class UsuarioSingleton {
    private long id_usuario;
    private String usuario;
    private String clave;
    private long idempleado;
    private boolean cliente;
    private boolean proveedor;
    private boolean apertura;
    private boolean consultarrepara;
    private boolean consultarventa;
    private boolean debe;
    private boolean ckempleado;
    private boolean familia;
    private boolean haber;
    private boolean ingreso;
    private boolean inventario;
    private boolean movimientos;
    private boolean nueva;
    private boolean pendientes;
    private boolean productos;
    private boolean useri;
    private boolean vender;  
    private String tema;
    private boolean extornar;
    
    private boolean prodpendientes;
    private boolean mermas;
    private boolean consulmermas;
    private boolean sucursalida;
    private boolean sucurentrada;
    private boolean consultasucur;
    
     private boolean web;
    private boolean cotizacion;
    private boolean buscar_cot;
    private boolean mant_prod;
    private boolean kardex;
    private boolean act_precios;
    private boolean facturas;
    private boolean boletas;
    private boolean ncredito;
    private boolean ndebito;
    
    private static  UsuarioSingleton user;
    
    public static UsuarioSingleton getintancia(){
        if (user== null){
        user= new UsuarioSingleton();
        }
    
    return user;
    }
    public void getdestruir(){
        if(user != null){
            user = null;
        } 
    }
            
    
    private UsuarioSingleton() {
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public boolean isCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(boolean cotizacion) {
        this.cotizacion = cotizacion;
    }

    public boolean isBuscar_cot() {
        return buscar_cot;
    }

    public void setBuscar_cot(boolean buscar_cot) {
        this.buscar_cot = buscar_cot;
    }

    public boolean isMant_prod() {
        return mant_prod;
    }

    public void setMant_prod(boolean mant_prod) {
        this.mant_prod = mant_prod;
    }

    public boolean isKardex() {
        return kardex;
    }

    public void setKardex(boolean kardex) {
        this.kardex = kardex;
    }

    public boolean isAct_precios() {
        return act_precios;
    }

    public void setAct_precios(boolean act_precios) {
        this.act_precios = act_precios;
    }

    public boolean isFacturas() {
        return facturas;
    }

    public void setFacturas(boolean facturas) {
        this.facturas = facturas;
    }

    public boolean isBoletas() {
        return boletas;
    }

    public void setBoletas(boolean boletas) {
        this.boletas = boletas;
    }

    public boolean isNcredito() {
        return ncredito;
    }

    public void setNcredito(boolean ncredito) {
        this.ncredito = ncredito;
    }

    public boolean isNdebito() {
        return ndebito;
    }

    public void setNdebito(boolean ndebito) {
        this.ndebito = ndebito;
    }

    

    public boolean isProdpendientes() {
        return prodpendientes;
    }

    public void setProdpendientes(boolean prodpendientes) {
        this.prodpendientes = prodpendientes;
    }

    public boolean isMermas() {
        return mermas;
    }

    public void setMermas(boolean mermas) {
        this.mermas = mermas;
    }

    public boolean isConsulmermas() {
        return consulmermas;
    }

    public void setConsulmermas(boolean consulmermas) {
        this.consulmermas = consulmermas;
    }

    public boolean isSucursalida() {
        return sucursalida;
    }

    public void setSucursalida(boolean sucursalida) {
        this.sucursalida = sucursalida;
    }

    public boolean isSucurentrada() {
        return sucurentrada;
    }

    public void setSucurentrada(boolean sucurentrada) {
        this.sucurentrada = sucurentrada;
    }

    public boolean isConsultasucur() {
        return consultasucur;
    }

    public void setConsultasucur(boolean consultasucur) {
        this.consultasucur = consultasucur;
    }

   

    public boolean isExtornar() {
        return extornar;
    }

    public void setExtornar(boolean extornar) {
        this.extornar = extornar;
    }

    

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    

    public boolean isCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public boolean isProveedor() {
        return proveedor;
    }

    public void setProveedor(boolean proveedor) {
        this.proveedor = proveedor;
    }

    public boolean isApertura() {
        return apertura;
    }

    public void setApertura(boolean apertura) {
        this.apertura = apertura;
    }

    public boolean isConsultarrepara() {
        return consultarrepara;
    }

    public void setConsultarrepara(boolean consultarrepara) {
        this.consultarrepara = consultarrepara;
    }

    public boolean isConsultarventa() {
        return consultarventa;
    }

    public void setConsultarventa(boolean consultarventa) {
        this.consultarventa = consultarventa;
    }

    public boolean isDebe() {
        return debe;
    }

    public void setDebe(boolean debe) {
        this.debe = debe;
    }

    public boolean isCkempleado() {
        return ckempleado;
    }

    public void setCkempleado(boolean ckempleado) {
        this.ckempleado = ckempleado;
    }

    public boolean isFamilia() {
        return familia;
    }

    public void setFamilia(boolean familia) {
        this.familia = familia;
    }

    public boolean isHaber() {
        return haber;
    }

    public void setHaber(boolean haber) {
        this.haber = haber;
    }

    public boolean isIngreso() {
        return ingreso;
    }

    public void setIngreso(boolean ingreso) {
        this.ingreso = ingreso;
    }

    public boolean isInventario() {
        return inventario;
    }

    public void setInventario(boolean inventario) {
        this.inventario = inventario;
    }

    public boolean isMovimientos() {
        return movimientos;
    }

    public void setMovimientos(boolean movimientos) {
        this.movimientos = movimientos;
    }

    public boolean isNueva() {
        return nueva;
    }

    public void setNueva(boolean nueva) {
        this.nueva = nueva;
    }

    public boolean isPendientes() {
        return pendientes;
    }

    public void setPendientes(boolean pendientes) {
        this.pendientes = pendientes;
    }

    public boolean isProductos() {
        return productos;
    }

    public void setProductos(boolean productos) {
        this.productos = productos;
    }

    public boolean isUseri() {
        return useri;
    }

    public void setUseri(boolean useri) {
        this.useri = useri;
    }

    public boolean isVender() {
        return vender;
    }

    public void setVender(boolean vender) {
        this.vender = vender;
    }

    public static UsuarioSingleton getUser() {
        return user;
    }

    public static void setUser(UsuarioSingleton user) {
        UsuarioSingleton.user = user;
    }

    

    public long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(long idempleado) {
        this.idempleado = idempleado;
    }

   

   
    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
}
