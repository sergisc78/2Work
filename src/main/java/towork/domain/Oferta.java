
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
@Table(name="ofertes")
public class Oferta implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column(name="refOferta")
    private String refOferta;
    
    @NotNull
    @Column(name="nifEmpresa")
    private String nifEmpresa;
    
    @NotNull
    @Size(max=100)
    @Column(name="titolOferta")
    private String titolOferta;
    
    @NotNull
    @Size(max=100)
    @Column(name="localitat")
    private String localitat;
    
    @NotNull    
    @Column(name="sou")
    private Double sou;
    
    @NotNull
    @Column(name="horesDia")
    private Integer horesDia;
    
    @NotNull
    @Size(max=10)
    @Column(name="torn")
    private String torn;
    
    @NotNull
    @Size(max=50)
    @Column(name="tipusContracte")
    private String tipusContracte;
    
    @NotNull
    @Size(max=100)
    @Column(name="formacio")
    private String formacio;
      
    @NotNull
    @Size(max=50)
    @Column(name="estat")
    private String estat;
    
    @Column(name="descripcio")
    private String descripcio;
    
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Habilitat> habilitats = new ArrayList();
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Candidat> candidats = new ArrayList();

    public Oferta(String refOferta, String nifEmpresa, String titolOferta, 
            String localitat, Double sou, Integer horesDia, String torn, 
            String tipusContracte, String formacio, String estat, 
            String descripcio,ArrayList<Habilitat> habilitats,
            ArrayList<Candidat> candidats) {
        this.refOferta = refOferta;
        this.nifEmpresa = nifEmpresa;
        this.titolOferta = titolOferta;
        this.localitat = localitat;
        this.sou = sou;
        this.horesDia = horesDia;
        this.torn = torn;
        this.tipusContracte = tipusContracte;
        this.formacio = formacio;
        this.estat = estat;
        this.descripcio = descripcio;
        this.habilitats=habilitats;
        this.candidats=candidats;
    }

    public Oferta() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRefOferta() {
        return refOferta;
    }

    public String getNifEmpresa() {
        return nifEmpresa;
    }

    public String getTitolOferta() {
        return titolOferta;
    }

    public String getLocalitat() {
        return localitat;
    }

    public Double getSou() {
        return sou;
    }

    public Integer getHoresDia() {
        return horesDia;
    }

    public String getTorn() {
        return torn;
    }

    public String getTipusContracte() {
        return tipusContracte;
    }

    public String getFormacio() {
        return formacio;
    }

    public String getEstat() {
        return estat;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public ArrayList<Habilitat> getHabilitats() {
        return habilitats;
    }

    public ArrayList<Candidat> getCandidats() {
        return candidats;
    }

    public void setRefOferta(String refOferta) {
        this.refOferta = refOferta;
    }

    public void setNifEmpresa(String nifEmpresa) {
        this.nifEmpresa = nifEmpresa;
    }

    public void setTitolOferta(String titolOferta) {
        this.titolOferta = titolOferta;
    }

    public void setLocalitat(String localitat) {
        this.localitat = localitat;
    }

    public void setSou(Double sou) {
        this.sou = sou;
    }

    public void setHoresDia(Integer horesDia) {
        this.horesDia = horesDia;
    }

    public void setTorn(String torn) {
        this.torn = torn;
    }

    public void setTipusContracte(String tipusContracte) {
        this.tipusContracte = tipusContracte;
    }

    public void setFormacio(String formacio) {
        this.formacio = formacio;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setHabilitats(ArrayList<Habilitat> habilitats) {
        this.habilitats = habilitats;
    }

    public void setCandidats(ArrayList<Candidat> candidats) {
        this.candidats = candidats;
    }

    
}
