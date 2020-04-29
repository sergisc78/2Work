
package towork.domain;


public class Experiencia {
    
    private Integer codiExperiencia;
    private Integer anys;
    private String nomEmpresa;
    private Integer codiCandidat;
    private String descripcio;

    public Experiencia(Integer codiExperiencia,Integer anys, String nomEmpresa, Integer codiCandidat, String descripcio) {
        this.codiExperiencia=codiExperiencia;
        this.anys = anys;
        this.nomEmpresa = nomEmpresa;
        this.codiCandidat = codiCandidat;
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

    public Integer getCodiCandidat() {
        return codiCandidat;
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

    public void setCodiCandidat(Integer codiCandidat) {
        this.codiCandidat = codiCandidat;

    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setCodiExperiencia(Integer codiExperiencia) {
        this.codiExperiencia = codiExperiencia;
    }
    
    
}
