/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gonem
 */
@Entity
@Table(name="candidatures")
public class Candidatura implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column (name="codiCandidatura")
    protected Integer codiCandidatura;
    
    @NotNull
    @Column (name="codiOferta")
    protected Integer codiOferta;
    
    @NotNull
    @Column (name="codiCandidat")
    protected Integer codiCandidat;
    
    @NotNull
    @Column (name="estat")
    protected Integer estat;

    public Candidatura(Integer codiCandidatura, Integer codiOferta, Integer codiCandidat, Integer estat) {
        this.codiCandidatura = codiCandidatura;
        this.codiOferta = codiOferta;
        this.codiCandidat = codiCandidat;
        this.estat = estat;
    }

    public Candidatura() {
    }

    public Integer getCodiCandidatura() {
        return codiCandidatura;
    }

    public Integer getCodiOferta() {
        return codiOferta;
    }

    public Integer getCodiCandidat() {
        return codiCandidat;
    }

    public Integer getEstat() {
        return estat;
    }

    public void setCodiCandidatura(Integer codiCandidatura) {
        this.codiCandidatura = codiCandidatura;
    }

    public void setCodiOferta(Integer codiOferta) {
        this.codiOferta = codiOferta;
    }

    public void setCodiCandidat(Integer codiCandidat) {
        this.codiCandidat = codiCandidat;
    }

    public void setEstat(Integer estat) {
        this.estat = estat;
    }
    
    
}
