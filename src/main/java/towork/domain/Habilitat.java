
package towork.domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="habilitats")
public class Habilitat implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column(name="codiHab")
    private String codiHab;
    
    @NotNull
    @Size(max=100)
    @Column(name="nomHab")
    private String nomHab;
    
    @Column(name="candidats")
    protected ArrayList<Habilitat> candidats = new ArrayList();
    
    @Column(name="ofertes")
    protected ArrayList<Oferta> ofertes=new ArrayList();
    
    public Habilitat(String codiHab, String nomHab,ArrayList<Habilitat> candidats,
            ArrayList<Oferta> ofertes){
        this.codiHab=codiHab;
        this.nomHab=nomHab;
        this.candidats=candidats;
        this.ofertes=ofertes;
    }
    public Habilitat(){
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCodiHab() {
        return codiHab;
    }

    public String getNomHab() {
        return nomHab;
    }

    public ArrayList<Habilitat> getCandidats() {
        return candidats;
    }

    public ArrayList<Oferta> getOfertes() {
        return ofertes;
    }

    public void setCodiHab(String codiHab) {
        this.codiHab = codiHab;
    }

    public void setNomHab(String nomHab) {
        this.nomHab = nomHab;
    }

    public void setCandidats(ArrayList<Habilitat> candidats) {
        this.candidats = candidats;
    }

    public void setOfertes(ArrayList<Oferta> ofertes) {
        this.ofertes = ofertes;
    }
    
}
