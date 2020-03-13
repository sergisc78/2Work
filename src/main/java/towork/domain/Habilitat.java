
package towork.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="habilitats")
public class Habilitat implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column(name="codiHab")
    private String codiHab;
    
    @NotNull
    @Size(max=100)
    @Column(name="nomHab")
    private String nomHab;
    
    public Habilitat(String codiHab, String nomHab){
        this.codiHab=codiHab;
        this.nomHab=nomHab;
    }
    public Habilitat(){
    }

    public String getCodiHab() {
        return codiHab;
    }

    public String getNomHab() {
        return nomHab;
    }

    public void setCodiHab(String codiHab) {
        this.codiHab = codiHab;
    }

    public void setNomHab(String nomHab) {
        this.nomHab = nomHab;
    }
    
}
