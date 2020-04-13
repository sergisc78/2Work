
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


public class Empresa extends Usuari{  
    
    protected String responsable;    
    protected String web;
    protected Integer tamany;
    protected Integer sector;
   

    public Empresa(Integer codi,String nom, String responsable, String dniNif,String adreca, String poblacio, String provincia,
            String telefon,String web, Integer tamany, String email, String observacions,
            String pass, String cPass, Integer sector) {
        super(codi,nom,dniNif,adreca,poblacio,provincia,telefon,email,observacions,pass,cPass);
        this.responsable=responsable;
        this.web = web;
        this.tamany = tamany;
        this.sector=sector;
    }

    public Empresa() {
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

    public void setSector(Integer sector) {
        this.sector = sector;
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

    public Integer getSector() {
        return sector;
    }

    
}
