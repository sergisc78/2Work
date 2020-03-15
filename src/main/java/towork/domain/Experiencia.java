
package towork.domain;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="experiencies")
public class Experiencia implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column(name="anyos")
    private Integer anyos;
    
    @NotNull
    @Column(name="nomEmpresa")
    private String nomEmpresa;
    
    @NotNull
    @Column(name="dniCandidat")
    private String dniCandidat;

    @Column(name="descripcio")
    private String descripcio;

    public Experiencia(Integer anyos, String nomEmpresa, String dniCandidat, String descripcio) {
        this.anyos = anyos;
        this.nomEmpresa = nomEmpresa;
        this.dniCandidat = dniCandidat;
        this.descripcio = descripcio;
    }

    public Experiencia() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAnyos() {
        return anyos;
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

    public void setAnyos(Integer anyos) {
        this.anyos = anyos;
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
    
    
}
