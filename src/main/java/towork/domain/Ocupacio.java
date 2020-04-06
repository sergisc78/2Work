/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gonem
 */
@Entity
@Table(name="ocupacions")
public class Ocupacio implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column (name="codiOcupacio")
    protected Integer codiOcupacio;
    
    @NotNull    
    @Size(max=100)
    @Column (name="nomOcupacio")
    protected String nomOcupacio;
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Candidat> candidats=new ArrayList();
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Habilitat> habilitats=new ArrayList();

    public Ocupacio(Integer codiOcupacio, String nomOcupacio) {
        this.codiOcupacio = codiOcupacio;
        this.nomOcupacio = nomOcupacio;
        this.candidats=new ArrayList<Candidat>();
        this.habilitats=new ArrayList<Habilitat>();
    }

    public Ocupacio() {
    }

    public Integer getCodiOcupacio() {
        return codiOcupacio;
    }

    public String getNomOcupacio() {
        return nomOcupacio;
    }

    public ArrayList<Candidat> getCandidats() {
        return candidats;
    }

    public ArrayList<Habilitat> getHabilitats() {
        return habilitats;
    }

    public void setCodiOcupacio(Integer codiOcupacio) {
        this.codiOcupacio = codiOcupacio;
    }

    public void setNomOcupacio(String nomOcupacio) {
        this.nomOcupacio = nomOcupacio;
    }

    public void addCandidats(ArrayList<Candidat> candidats) {
        this.candidats = candidats;
    }

    public void addHabilitats(ArrayList<Habilitat> habilitats) {
        this.habilitats = habilitats;
    }
    
    
}
