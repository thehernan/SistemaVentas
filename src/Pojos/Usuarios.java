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
public class Usuarios {
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
    private boolean user;
    private boolean vender;      
    private String tema;
    private boolean  extornar;
    
    private boolean prodpendientes;
    private boolean mermas;
    private boolean consulmermas;
    private boolean sucursalida;
    private boolean sucurentrada;
    private boolean consultasucur;
    
    public Usuarios() {
    }

    public Usuarios(long id_usuario, String usuario, String clave, long idempleado, boolean cliente, boolean proveedor, boolean apertura, boolean consultarrepara, boolean consultarventa, boolean debe, boolean ckempleado, boolean familia, boolean haber, boolean ingreso, boolean inventario, boolean movimientos, boolean nueva, boolean pendientes, boolean productos, boolean user, boolean vender, String tema, boolean extornar, boolean prodpendientes, boolean mermas, boolean consulmermas, boolean sucursalida, boolean sucurentrada, boolean consultasucur) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.clave = clave;
        this.idempleado = idempleado;
        this.cliente = cliente;
        this.proveedor = proveedor;
        this.apertura = apertura;
        this.consultarrepara = consultarrepara;
        this.consultarventa = consultarventa;
        this.debe = debe;
        this.ckempleado = ckempleado;
        this.familia = familia;
        this.haber = haber;
        this.ingreso = ingreso;
        this.inventario = inventario;
        this.movimientos = movimientos;
        this.nueva = nueva;
        this.pendientes = pendientes;
        this.productos = productos;
        this.user = user;
        this.vender = vender;
        this.tema = tema;
        this.extornar = extornar;
        this.prodpendientes = prodpendientes;
        this.mermas = mermas;
        this.consulmermas = consulmermas;
        this.sucursalida = sucursalida;
        this.sucurentrada = sucurentrada;
        this.consultasucur = consultasucur;
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

    public long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(long idempleado) {
        this.idempleado = idempleado;
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

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public boolean isVender() {
        return vender;
    }

    public void setVender(boolean vender) {
        this.vender = vender;
    }

    
}
