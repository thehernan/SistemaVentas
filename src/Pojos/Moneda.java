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
public class Moneda {
    private long id;
    private String moneda;
    private double tipo_cambio;
    private int op;
    private String abrev;

    public Moneda() {
    }

    public Moneda(long id, String moneda, double tipo_cambio, int op, String abrev) {
        this.id = id;
        this.moneda = moneda;
        this.tipo_cambio = tipo_cambio;
        this.op = op;
        this.abrev = abrev;
    }

    public String getAbrev() {
        return abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getTipo_cambio() {
        return tipo_cambio;
    }

    public void setTipo_cambio(double tipo_cambio) {
        this.tipo_cambio = tipo_cambio;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }
    
    
    
}
