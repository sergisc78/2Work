
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
@Table(name="setors")
public class Sector implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column (name="codiSector")
    protected String codiSector;
    
    @NotNull    
    @Size(max=100)
    @Column (name="nomSector")
    protected String nomSector;
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Empresa> empreses=new ArrayList();
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Candidat> candidats=new ArrayList();
   
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Habilitat> habilitats = new ArrayList();

    public Sector(String codiSector, String nomSector,ArrayList<Empresa> empreses,
            ArrayList<Candidat> candidats,ArrayList<Habilitat> habilitats) {
        this.codiSector = codiSector;
        this.nomSector = nomSector;
        this.empreses=empreses;
        this.candidats=candidats;
        this.habilitats=habilitats;
    }

    public Sector() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCodiSector() {
        return codiSector;
    }

    public String getNomSector() {
        return nomSector;
    }

    public ArrayList<Empresa> getEmpreses() {
        return empreses;
    }

    public ArrayList<Candidat> getCandidats() {
        return candidats;
    }

    public ArrayList<Habilitat> getHabilitats() {
        return habilitats;
    }

    public void setCodiSector(String codiSector) {
        this.codiSector = codiSector;
    }

    public void setNomSector(String nomSector) {
        this.nomSector = nomSector;
    }

    public void setEmpreses(ArrayList<Empresa> empreses) {
        this.empreses = empreses;
    }

    public void setCandidats(ArrayList<Candidat> candidats) {
        this.candidats = candidats;
    }

    public void setHabilitats(ArrayList<Habilitat> habilitats) {
        this.habilitats = habilitats;
    }

}
