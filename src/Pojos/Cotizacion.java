/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.sql.Timestamp;

/**
 *
 * @author HERNAN
 */
public class Cotizacion {
    private long id;
    private Timestamp fecha;
    private long idcliente;
    private String emitida;
    private String condicionpago;
    private long idsucur;

    public Cotizacion() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public String getEmitida() {
        return emitida;
    }

    public void setEmitida(String emitida) {
        this.emitida = emitida;
    }

    public String getCondicionpago() {
        return condicionpago;
    }

    public void setCondicionpago(String condicionpago) {
        this.condicionpago = condicionpago;
    }

    public long getIdsucur() {
        return idsucur;
    }

    public void setIdsucur(long idsucur) {
        this.idsucur = idsucur;
    }
    
    
}
