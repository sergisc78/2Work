
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
@Table(name="formacionsPostUni")
public class FormPostUni implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column (name="codiFormacio")
    protected String codiFormacio;
    
    @NotNull    
    @Size(max=100)
    @Column (name="nomFormacio")
    protected String nomFormacio;
    
    @Column(name="candidats")
    protected ArrayList<Candidat> candidats=new ArrayList();

    public FormPostUni(String codiFormacio, String nomFormacio,ArrayList<Candidat> candidats) {
        this.codiFormacio = codiFormacio;
        this.nomFormacio = nomFormacio;
        this.candidats = candidats;
    }

    public FormPostUni() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCodiFormacio() {
        return codiFormacio;
    }

    public String getNomFormacio() {
        return nomFormacio;
    }

    public ArrayList<Candidat> getCandidats() {
        return candidats;
    }

    public void setCodiFormacio(String codiFormacio) {
        this.codiFormacio = codiFormacio;
    }

    public void setNomFormacio(String nomFormacio) {
        this.nomFormacio = nomFormacio;
    }

    public void setCandidats(ArrayList<Candidat> candidats) {
        this.candidats = candidats;
    }
    
    
    
    
}
