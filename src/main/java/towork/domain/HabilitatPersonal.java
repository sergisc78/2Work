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
public class HabilitatPersonal {
    private Integer codiCandidat;
    private Integer codiHab;

    public HabilitatPersonal(Integer codiCandidat, Integer codiHab) {
        this.codiCandidat = codiCandidat;
        this.codiHab = codiHab;
    }

    public HabilitatPersonal() {
    }

    public Integer getCodiCandidat() {
        return codiCandidat;
    }

    public Integer getCodiHab() {
        return codiHab;
    }

    public void setCodiCandidat(Integer codiCandidat) {
        this.codiCandidat = codiCandidat;
    }

    public void setCodiHab(Integer codiHab) {
        this.codiHab = codiHab;
    }
    
    
}
