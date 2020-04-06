
package towork.domain;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="experiencies")
public class Experiencia implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column(name="codiExperiencia")
    private Integer codiExperiencia;
   
    @NotNull
    @Column(name="anys")
    private Integer anys;
    
    @NotNull
    @Size(max=50)
    @Column(name="nomEmpresa")
    private String nomEmpresa;
    
    
    @NotNull
    @Size(max=9)
    @Column(name="dniCandidat")
    private String dniCandidat;

    @Size(max=300)
    @Column(name="descripcio")
    private String descripcio;

    public Experiencia(Integer codiExperiencia,Integer anys, String nomEmpresa, String dniCandidat, String descripcio) {
        this.codiExperiencia=this.codiExperiencia;
        this.anys = anys;
        this.nomEmpresa = nomEmpresa;
        this.dniCandidat = dniCandidat;
        this.descripcio = descripcio;
    }

    public Experiencia() {
    }

    public Integer getCodiExperiencia() {
        return codiExperiencia;
    }

    public Integer getAnys() {
        return anys;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public String getDniCandidat() {
        return dniCandidat;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setAnys(Integer anys) {
        this.anys = anys;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public void setDniCandidat(String dniCandidat) {
        this.dniCandidat = dniCandidat;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setCodiExperiencia(Integer codiExperiencia) {
        this.codiExperiencia = codiExperiencia;
    }
    
    
}
