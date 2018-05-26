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
public class Percepcion_Tipo {
    
    private long id;
    private String percepcion;
    private int op;

    public Percepcion_Tipo() {
    }

    public Percepcion_Tipo(long id, String percepcion, int op) {
        this.id = id;
        this.percepcion = percepcion;
        this.op = op;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPercepcion() {
        return percepcion;
    }

    public void setPercepcion(String percepcion) {
        this.percepcion = percepcion;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }
    
    
    
}
