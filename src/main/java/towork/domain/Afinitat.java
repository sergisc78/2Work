
package towork.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="afinitats")
public class Afinitat implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column(name="codiAfin")
    private String codiAfin;
    
    @NotNull
    @Column(name="refOferta")
    private String refOferta;
    
    @NotNull
    @Column(name="codiCand")
    private String codiCand;
    
    @NotNull
    @Column(name="estat")
    private String estat;
    
    public Afinitat(String codiAfin, String refOferta, String codiCand, String estat){
        this.codiAfin=codiAfin;
        this.refOferta=refOferta;
        this.codiCand=codiAfin;
        this.estat=estat;
    
    }
    public Afinitat(){
    }

    public String getCodiAfin() {
        return codiAfin;
    }

    public String getRefOferta() {
        return refOferta;
    }

    public String getCodiCand() {
        return codiCand;
    }

    public String getEstat() {
        return estat;
    }

    public void setCodiAfin(String codiAfin) {
        this.codiAfin = codiAfin;
    }

    public void setRefOferta(String refOferta) {
        this.refOferta = refOferta;
    }

    public void setCodiCand(String codiCand) {
        this.codiCand = codiCand;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }
    
}
