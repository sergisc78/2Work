
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
@Table(name="empreses")
public class Empresa extends Usuari implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    
    @NotNull
    @Size(max=50)
    @Column (name="responsable")
    protected String responsable;    
    
    @Size(max=30)
    @Column(name="web")
    protected String web;
    
    @Column(name="tamany")
    protected Integer tamany;
    
    @NotNull    
    @Size(max=100)
    @Column (name="sector")
    protected String sector;
     
    @NotNull
    @Column (name="codiEmpresa")
    protected Integer codiEmpresa;
    
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Oferta> ofertes=new ArrayList();

    public Empresa( String nom, String responsable, String dniNif,String adreca, String ciutat, String provincia,
            String telefon,String web, Integer tamany, String email, 
            String pass, String cPass, String sector,Integer codiEmpresa) {
        super(nom,dniNif,adreca,ciutat,provincia,telefon,email,pass,cPass);
        this.responsable=responsable;
        this.web = web;
        this.tamany = tamany;
        this.sector=sector;
        this.codiEmpresa=codiEmpresa;
        this.ofertes= new ArrayList<Oferta>();
    }

    public Empresa() {
    }

    public String getResponsable() {
        return responsable;
    }

    public String getWeb() {
        return web;
    }

    public Integer getTamany() {
        return tamany;
    }

    public String getSector() {
        return sector;
    }

    public Integer getCodiEmpresa() {
        return codiEmpresa;
    }

    public ArrayList<Oferta> getOfertes() {
        return ofertes;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setTamany(Integer tamany) {
        this.tamany = tamany;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setCodiEmpresa(Integer codiEmpresa) {
        this.codiEmpresa = codiEmpresa;
    }

    public void addOfertes(ArrayList<Oferta> ofertes) {
        this.ofertes = ofertes;
    }

   
    
     
}
