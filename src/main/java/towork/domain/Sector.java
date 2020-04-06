
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
@Table(name="sectors")
public class Sector implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column (name="codiSector")
    protected Integer codiSector;
    
    @NotNull    
    @Size(max=100)
    @Column (name="nomSector")
    protected String nomSector;
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
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

    public void addEmpreses(ArrayList<Empresa> empreses) {
        this.empreses = empreses;
    }

}
