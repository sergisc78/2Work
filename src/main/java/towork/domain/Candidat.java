
package towork.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="candidats")
public class Candidat extends Usuari implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @NotNull
    @Column(name="codiCand")
    protected String codiCand;
    
    @NotNull
    @Size(max=75)
    @Column(name="cognoms")
    protected String cognoms;
    
    @NotNull
    @Column(name="dataNaix")
    protected Date dataNaix;
    
    @Column(name="formacio")
    protected String formacio;
    
    @Column(name="formPostUni")
    protected String formPostUni;
    
    @Column(name="habilitats")
    protected ArrayList<Habilitat> habilitats = new ArrayList();
    
    
    public Candidat(String dniNif,String nom, String adreca, String telefon, 
            String email, String pass, String cPass, String sector,
            String codiCand,String cognoms,Date dataNaix,String formacio,
            String formPostUni,ArrayList<Habilitat> habilitats ){
        
            super(dniNif,nom,adreca,telefon,email,pass,cPass,sector);
            this.codiCand=codiCand;
            this.cognoms=cognoms;
            this.dataNaix=dataNaix;
            this.formacio=formacio;
            this.formPostUni=formPostUni;
            this.habilitats=habilitats;
            
    }
    public Candidat(){
        super();
    }

    public String getCodiCand() {
        return codiCand;
    }

    public String getCognoms() {
        return cognoms;
    }

    public Date getDataNaix() {
        return dataNaix;
    }

    public String getFormacio() {
        return formacio;
    }

    public String getFormPostUni() {
        return formPostUni;
    }

    public ArrayList<Habilitat> getHabilitats() {
        return habilitats;
    }

    public void setCodiCand(String codiCand) {
        this.codiCand = codiCand;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public void setDataNaix(Date dataNaix) {
        this.dataNaix = dataNaix;
    }

    public void setFormacio(String formacio) {
        this.formacio = formacio;
    }

    public void setFormPostUni(String formPostUni) {
        this.formPostUni = formPostUni;
    }

    public void setHabilitats(ArrayList<Habilitat> habilitats) {
        this.habilitats = habilitats;
    }

   
}
