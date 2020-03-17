
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
    @Column (name="codiSector")
    protected String codiSector;
     
    @NotNull
    @Size(max=100)
    @Column(name="web")
    protected String web;
    
    @Column(name="tamany")
    protected Integer tamany;
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    protected ArrayList<Oferta> ofertes=new ArrayList();

    public Empresa(String dniNif, String nom, String adreca, String telefon, String email, 
            String pass, String cPass, String codiSector,String web, Integer tamany, 
            ArrayList<Oferta> ofertes) {
        super(dniNif, nom, adreca, telefon, email, pass, cPass);
        this.codiSector=codiSector;
        this.web = web;
        this.tamany = tamany;
        this.ofertes=ofertes;
    }

    public Empresa() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCodiSector() {
        return codiSector;
    }

    public String getWeb() {
        return web;
    }

    public Integer getTamany() {
        return tamany;
    }

    public ArrayList<Oferta> getOfertes() {
        return ofertes;
    }

    public void setCodiSector(String codiSector) {
        this.codiSector = codiSector;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setTamany(Integer tamany) {
        this.tamany = tamany;
    }

    public void setOfertes(ArrayList<Oferta> ofertes) {
        this.ofertes = ofertes;
    }

   
}
