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
public class Unidad_Medida {
    private long id;
    private String medida;
    private String abreviatura;

    public Unidad_Medida() {
    }

    public Unidad_Medida(long id, String medida, String abreviatura) {
        this.id = id;
        this.medida = medida;
        this.abreviatura = abreviatura;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    
    
}
