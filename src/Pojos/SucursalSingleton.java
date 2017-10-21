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
public class SucursalSingleton {
    private long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private static SucursalSingleton Ssucursal;
    private double stockmin;
//    private SucursalSingleton sucursal;
    
    private SucursalSingleton() {
    }
    public static SucursalSingleton getinstancia(){
        if(Ssucursal == null){
            Ssucursal = new SucursalSingleton();
//            Ssucursal.setcargarsucursal(null);
        }
    return Ssucursal;
    }
    
    public SucursalSingleton setcargarsucursal(Sucursal sucursall){
        Ssucursal.setId(sucursall.getId());
        Ssucursal.setNombre(sucursall.getNombre());
        Ssucursal.setDireccion(sucursall.getDireccion());
        Ssucursal.setTelefono(sucursall.getTelefono());
        Ssucursal.setStockmin(sucursall.getStockmin());
       return Ssucursal;
        
    }
    public void getdestruir(){
        if(Ssucursal != null){
            Ssucursal=null;
        
        }
            
    
    }

    public SucursalSingleton(long id, String nombre, String direccion, String telefono, double stockmin) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.stockmin = stockmin;
    }

   

    public double getStockmin() {
        return stockmin;
    }

    public void setStockmin(double stockmin) {
        this.stockmin = stockmin;
    }
  

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
