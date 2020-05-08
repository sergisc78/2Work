/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.domain;

/**
 *
 * @author gonem
 */
public class HabilitatOferta {
    private Integer codiOferta;
    private Integer codiHab;

    public HabilitatOferta(Integer codiOferta, Integer codiHab) {
        this.codiOferta = codiOferta;
        this.codiHab = codiHab;
    }

    public HabilitatOferta() {
    }

    public Integer getCodiOferta() {
        return codiOferta;
    }

    public Integer getCodiHab() {
        return codiHab;
    }

    public void setCodiOferta(Integer codiOferta) {
        this.codiOferta = codiOferta;
    }

    public void setCodiHab(Integer codiHab) {
        this.codiHab = codiHab;
    }
    
}
