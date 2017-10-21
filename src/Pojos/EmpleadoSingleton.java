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
public class EmpleadoSingleton {
    private String nombre;
//    private String apellido;
    private String rut;
    private String celular;
    private String direccion;
    private String email;
    private String cargo;
    private byte[] foto;
    private long id_empleado;
    private static EmpleadoSingleton empleado;
   
    public static EmpleadoSingleton getinstancia(){
        if(empleado == null){
            empleado= new EmpleadoSingleton();
                    
        }
        
    return empleado;
    
    }
    public void getdestruir(){
        if(empleado != null){
            empleado = null;
        }  
    
    }

    private EmpleadoSingleton() {
    }

    private EmpleadoSingleton(String nombre,  String rut, String celular, String direccion, String email, String cargo, byte[] foto, long id_empleado) {
        this.nombre = nombre;
//        this.apellido = apellido;
        this.rut = rut;
        this.celular = celular;
        this.direccion = direccion;
        this.email = email;
        this.cargo = cargo;
        this.foto = foto;
        this.id_empleado = id_empleado;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

////    public String getApellido() {
////        return apellido;
////    }
////
////    public void setApellido(String apellido) {
////        this.apellido = apellido;
////    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

   

    public long getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(long id_empleado) {
        this.id_empleado = id_empleado;
    }
    
}
