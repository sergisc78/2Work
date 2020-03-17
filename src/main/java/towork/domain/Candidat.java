
package towork.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name="candidats")
public class Candidat extends Usuari implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @NotNull
    @Size(max=75)
    @Column(name="cognoms")
    protected String cognoms;
    
    @NotNull
    @Column(name="dataNaix")
    protected Date dataNaix;
    
    @Column(name="codiFormacio")
    protected String codiFormacio;
    
    @Column(name="codiFormPostUni")
    protected String codiFormPostUni;
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Sector> sectors = new ArrayList();
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Formacio> formacions = new ArrayList();
     
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<FormPostUni> formacionsPostUni = new ArrayList();
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Habilitat> habilitats = new ArrayList();
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Experiencia> experiencies = new ArrayList();

    public Candidat( String dniNif, String nom, String adreca, String telefon,
            String email, String pass, String cPass, 
            String cognoms, Date dataNaix, String codiFormacio, String codiFormPostUni,
            ArrayList<Sector> sectors,ArrayList<Formacio> formacions,
            ArrayList<FormPostUni> formacionsPostUni,ArrayList<Habilitat> habilitats ,
            ArrayList<Experiencia> experiencies) {
        super(dniNif, nom, adreca, telefon, email, pass, cPass);
        this.cognoms = cognoms;
        this.dataNaix = dataNaix;
        this.codiFormacio = codiFormacio;
        this.codiFormPostUni = codiFormPostUni;
        this.sectors=sectors;
        this.formacions=formacions;
        this.formacionsPostUni=formacionsPostUni;
        this.habilitats=habilitats;
        this.experiencies=experiencies;
    }

    public Candidat() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCognoms() {
        return cognoms;
    }

    public Date getDataNaix() {
        return dataNaix;
    }

    public String getCodiFormacio() {
        return codiFormacio;
    }

    public String getCodiFormPostUni() {
        return codiFormPostUni;
    }

    public ArrayList<Sector> getSectors() {
        return sectors;
    }

    public ArrayList<Formacio> getFormacions() {
        return formacions;
    }

    public ArrayList<FormPostUni> getFormacionsPostUni() {
        return formacionsPostUni;
    }

    public ArrayList<Habilitat> getHabilitats() {
        return habilitats;
    }

    public ArrayList<Experiencia> getExperiencies() {
        return experiencies;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public void setDataNaix(Date dataNaix) {
        this.dataNaix = dataNaix;
    }

    public void setCodiFormacio(String codiFormacio) {
        this.codiFormacio = codiFormacio;
    }

    public void setCodiFormPostUni(String codiFormPostUni) {
        this.codiFormPostUni = codiFormPostUni;
    }

    public void setSectors(ArrayList<Sector> sectors) {
        this.sectors = sectors;
    }

    public void setFormacions(ArrayList<Formacio> formacions) {
        this.formacions = formacions;
    }

    public void setFormacionsPostUni(ArrayList<FormPostUni> formacionsPostUni) {
        this.formacionsPostUni = formacionsPostUni;
    }

    public void setHabilitats(ArrayList<Habilitat> habilitats) {
        this.habilitats = habilitats;
    }

    public void setExperiencies(ArrayList<Experiencia> experiencies) {
        this.experiencies = experiencies;
    }

    
}
