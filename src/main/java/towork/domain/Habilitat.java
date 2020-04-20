
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


public class Habilitat {
    
    private Integer codiHab;
    private String nomHab;
    private Integer ocupacio;
    private ArrayList<Habilitat> candidats = new ArrayList();
    private ArrayList<Oferta> ofertes=new ArrayList();

    public Habilitat(Integer codiHab, String nomHab, Integer ocupacio) {
        this.codiHab = codiHab;
        this.nomHab = nomHab;
        this.ocupacio = ocupacio;
        // this.candidats=new ArrayList<Habilitat>();
        // this.ofertes=new ArrayList<Oferta>();
    }

    public Habilitat() {
    }
    
    public Integer getCodiHab() {
        return codiHab;
    }

    public String getNomHab() {
        return nomHab;
    }

    public Integer getOcupacio() {
        return ocupacio;
    }

    public ArrayList<Habilitat> getCandidats() {
        return candidats;
    }

    public ArrayList<Oferta> getOfertes() {
        return ofertes;
    }

    public void setCodiHab(Integer codiHab) {
        this.codiHab = codiHab;
    }
    public void setNomHab(String nomHab) {
        this.nomHab = nomHab;
    }

    public void setOcupacio(Integer ocupacio) {
        this.ocupacio = ocupacio;
    }

    public void setCandidats(ArrayList<Habilitat> candidats) {
        this.candidats = candidats;
    }

    public void setOfertes(ArrayList<Oferta> ofertes) {
        this.ofertes = ofertes;
    }
   
}
