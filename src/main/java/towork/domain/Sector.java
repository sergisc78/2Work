
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


public class Sector {
    
    protected Integer codiSector;
    protected String nomSector;
    protected ArrayList<Empresa> empreses=new ArrayList();
    
    public Sector(Integer codiSector, String nomSector) {
        this.codiSector = codiSector;
        this.nomSector = nomSector;
        this.empreses= new ArrayList<Empresa>();
    }

    public Sector() {
    }

    public Integer getCodiSector() {
        return codiSector;
    }

    public String getNomSector() {
        return nomSector;
    }

    public ArrayList<Empresa> getEmpreses() {
        return empreses;
    }

    public void setCodiSector(Integer codiSector) {
        this.codiSector = codiSector;
    }

    public void setNomSector(String nomSector) {
        this.nomSector = nomSector;
    }

    public void setEmpreses(ArrayList<Empresa> empreses) {
        this.empreses = empreses;
    }

}
