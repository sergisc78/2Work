
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
    @Column(name="codiOferta")
    private Integer codiOferta;
    
    @NotNull
    @Size(max=9)
    @Column(name="nifEmpresa")
    private String nifEmpresa;
    
    @NotNull
    @Size(max=50)
    @Column(name="titolOferta")
    private String titolOferta;
    
    @NotNull
    @Column(name="ocupacio")
    protected Integer ocupacio;
    
    @NotNull
    @Size(max=50)
    @Column(name="ciutat")
    private String ciutat;
    
    @NotNull
    @Size(max=50)
    @Column(name="provincia")
    private String provincia;
    
    @NotNull    
    @Column(name="sou")
    private Double sou;
    
    @NotNull
    @Size(max=15)
    @Column(name="horari")
    private String horari;
    
    @NotNull
    @Size(max=50)
    @Column(name="tipusContracte")
    private String tipusContracte;
    
    @NotNull
    @Column(name="formacio")
    private Integer formacio;
      
    @NotNull
    @Size(max=10)
    @Column(name="estat")
    private String estat;
    
    @Size(max=300)
    @Column(name="descripcio")
    private String descripcio;
    
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Habilitat> habilitats = new ArrayList();
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Candidat> candidats = new ArrayList();

    public Oferta(Integer codiOferta, String nifEmpresa, String titolOferta, 
            Integer ocupacio,String ciutat, String provincia, Double sou, String horari, 
            String tipusContracte, Integer formacio, String estat, 
            String descripcio) {
        this.codiOferta = codiOferta;
        this.nifEmpresa = nifEmpresa;
        this.titolOferta = titolOferta;
        this.ocupacio=ocupacio;
        this.ciutat=ciutat;
        this.provincia=provincia;
        this.sou = sou;
        this.horari = horari;
        this.tipusContracte = tipusContracte;
        this.formacio = formacio;
        this.estat = estat;
        this.descripcio = descripcio;
        this.habilitats=new ArrayList<Habilitat>();
        this.candidats=new ArrayList<Candidat>();
    }

    public Oferta() {
    }

    public Integer getCodiOferta() {
        return codiOferta;
    }

    public String getNifEmpresa() {
        return nifEmpresa;
    }

    public String getTitolOferta() {
        return titolOferta;
    }

    public Integer getOcupacio() {
        return ocupacio;
    }

    public String getCiutat() {
        return ciutat;
    }

    public String getProvincia() {
        return provincia;
    }

    public Double getSou() {
        return sou;
    }

    public String getHorari() {
        return horari;
    }

    public String getTipusContracte() {
        return tipusContracte;
    }

    public Integer getFormacio() {
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

    public void setCodiOferta(Integer codiOferta) {
        this.codiOferta = codiOferta;
    }

    public void setNifEmpresa(String nifEmpresa) {
        this.nifEmpresa = nifEmpresa;
    }

    public void setTitolOferta(String titolOferta) {
        this.titolOferta = titolOferta;
    }

    public void setOcupacio(Integer ocupacio) {
        this.ocupacio = ocupacio;
    }
    
    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setSou(Double sou) {
        this.sou = sou;
    }

    public void setHorari(String horari) {
        this.horari = horari;
    }

    public void setTipusContracte(String tipusContracte) {
        this.tipusContracte = tipusContracte;
    }

    public void setFormacio(Integer formacio) {
        this.formacio = formacio;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void addHabilitats(ArrayList<Habilitat> habilitats) {
        this.habilitats = habilitats;
    }

    public void addCandidats(ArrayList<Candidat> candidats) {
        this.candidats = candidats;
    }
    
}
