
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
    private String anyos;
    
    @NotNull
    @Column(name="nomEmpresa")
    private String nomEmpresa;
    
    @NotNull
    @Column(name="candidat")
    private Candidat candidat;

    public Experiencia(String anyos, String nomEmpresa, Candidat candidat) {
        this.anyos = anyos;
        this.nomEmpresa = nomEmpresa;
        this.candidat = candidat;
    }

    public Experiencia() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAnyos() {
        return anyos;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setAnyos(String anyos) {
        this.anyos = anyos;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }
    
    
    
}
