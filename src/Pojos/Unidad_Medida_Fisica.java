/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

/**
 *
 * @author HERNAN
 */
public class Unidad_Medida_Fisica {
    
    private long id;
    private String descrip;
    private int conver;

    public Unidad_Medida_Fisica() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public int getConver() {
        return conver;
    }

    public void setConver(int conver) {
        this.conver = conver;
    }

}
