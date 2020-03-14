
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
@Table(name="ofertes")
public class Oferta implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column(name="refOferta")
    private String refOferta;
    
    @NotNull
    @Column(name="empresa")
    private Empresa empresa;
    
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
    private Integer sou;
    
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
      
    @Column(name="habilitats")
    protected ArrayList<Habilitat> habilitats = new ArrayList();
    
    @Column(name="afinitats")
    protected ArrayList<Afinitat> afinitats = new ArrayList();

    public Oferta(String refOferta, Empresa empresa, String titolOferta, String localitat, Integer sou, Integer horesDia, String torn, String tipusContracte, String formacio) {
        this.refOferta = refOferta;
        this.empresa = empresa;
        this.titolOferta = titolOferta;
        this.localitat = localitat;
        this.sou = sou;
        this.horesDia = horesDia;
        this.torn = torn;
        this.tipusContracte = tipusContracte;
        this.formacio = formacio;
    }

    public Oferta() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRefOferta() {
        return refOferta;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public String getTitolOferta() {
        return titolOferta;
    }

    public String getLocalitat() {
        return localitat;
    }

    public Integer getSou() {
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

    public ArrayList<Habilitat> getHabilitats() {
        return habilitats;
    }

    public ArrayList<Afinitat> getAfinitats() {
        return afinitats;
    }

    public void setRefOferta(String refOferta) {
        this.refOferta = refOferta;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setTitolOferta(String titolOferta) {
        this.titolOferta = titolOferta;
    }

    public void setLocalitat(String localitat) {
        this.localitat = localitat;
    }

    public void setSou(Integer sou) {
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

    public void setHabilitats(ArrayList<Habilitat> habilitats) {
        this.habilitats = habilitats;
    }

    public void setAfinitats(ArrayList<Afinitat> afinitats) {
        this.afinitats = afinitats;
    }

   
}
