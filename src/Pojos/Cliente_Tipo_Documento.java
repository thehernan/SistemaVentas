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
public class Cliente_Tipo_Documento {
    private long id;
    private String documento;
    private String op;

    public Cliente_Tipo_Documento() {
    }

    public Cliente_Tipo_Documento(long id, String documento, String op) {
        this.id = id;
        this.documento = documento;
        this.op = op;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
    
    
}
