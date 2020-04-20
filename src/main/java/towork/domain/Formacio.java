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


public class Formacio  {
    
    private Integer codiFormacio;
    private String nomFormacio;
    private ArrayList<Candidat> candidats=new ArrayList();

    public Formacio(Integer codiFormacio, String nomFormacio) {
        this.codiFormacio = codiFormacio;
        this.nomFormacio = nomFormacio;
        // this.candidats = new ArrayList<Candidat>();
    }

    public Formacio() {
    }

    public Integer getCodiFormacio() {
        return codiFormacio;
    }
  
    public String getNomFormacio() {
        return nomFormacio;
    }

    public ArrayList<Candidat> getCandidats() {
        return candidats;
    }

    public void setCodiFormacio(Integer codiFormacio) {
        this.codiFormacio = codiFormacio;
    }

    public void setNomFormacio(String nomFormacio) {
        this.nomFormacio = nomFormacio;
    }

    public void setCandidats(ArrayList<Candidat> candidats) {
        this.candidats = candidats;
    }
    
}
