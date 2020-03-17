
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

@Entity
@Table(name="formacions")
public class Formacio implements Serializable {
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column (name="codiFormPostUni")
    protected String codiFormPostUni;
    
    @NotNull    
    @Size(max=100)
    @Column (name="nomFormPostUni")
    protected String nomFormPostUni;
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Candidat> candidats=new ArrayList();

    public Formacio(String codiFormPostUni, String nomFormPostUni,ArrayList<Candidat> candidats) {
        this.codiFormPostUni = codiFormPostUni;
        this.nomFormPostUni = nomFormPostUni;
        this.candidats=candidats;
    }

    public Formacio() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCodiFormPostUni() {
        return codiFormPostUni;
    }

    public String getNomFormPostUni() {
        return nomFormPostUni;
    }

    public ArrayList<Candidat> getCandidats() {
        return candidats;
    }

    public void setCodiFormPostUni(String codiFormPostUni) {
        this.codiFormPostUni = codiFormPostUni;
    }

    public void setNomFormPostUni(String nomFormPostUni) {
        this.nomFormPostUni = nomFormPostUni;
    }

    public void setCandidats(ArrayList<Candidat> candidats) {
        this.candidats = candidats;
    }

    
}
