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
public class GuiaTipo {
    private long id;
    private String serienumero;
    private String guiatipo;
    private long idventa;
    private int identguia;
    public GuiaTipo() {
    }

    public long getIdventa() {
        return idventa;
    }

    public int getIdentguia() {
        return identguia;
    }

    public void setIdentguia(int identguia) {
        this.identguia = identguia;
    }
    

    public void setIdventa(long idventa) {
        this.idventa = idventa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerienumero() {
        return serienumero;
    }

    public void setSerienumero(String serienumero) {
        this.serienumero = serienumero;
    }

    public String getGuiatipo() {
        return guiatipo;
    }

    public void setGuiatipo(String guiatipo) {
        this.guiatipo = guiatipo;
    }


}
