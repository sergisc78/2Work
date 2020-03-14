
package towork.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="estats")
public class Estat implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column(name="codiEstat")
    private String codiEstat;
    
    @Size(max=75)
    @Column(name="descripcio")
    private String descripcio;
    
    @Size(max=75)
    @Column(name="afinitat")
    private Afinitat afinitat;

    public Estat(String codiEstat, String descripcio, Afinitat afinitat) {
        this.codiEstat = codiEstat;
        this.descripcio = descripcio;
        this.afinitat = afinitat;
    }

    public Estat() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCodiEstat() {
        return codiEstat;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public Afinitat getAfinitat() {
        return afinitat;
    }

    public void setCodiEstat(String codiEstat) {
        this.codiEstat = codiEstat;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setAfinitat(Afinitat afinitat) {
        this.afinitat = afinitat;
    }
}
