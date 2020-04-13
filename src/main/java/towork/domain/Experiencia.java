
package towork.domain;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Experiencia {
    
    private Integer codiExperiencia;
    private Integer anys;
    private String nomEmpresa;
    private String dniCandidat;
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
