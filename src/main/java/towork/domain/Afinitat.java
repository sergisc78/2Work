
package towork.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
    @Column(name="oferta")
    private Oferta oferta;
    
    @NotNull
    @Column(name="empresa")
    private Empresa empresa;
    
    @NotNull
    @Column(name="estat")
    private Estat estat;
    
    @Column(name="candidats")
    protected ArrayList<Habilitat> candidats = new ArrayList();
    
    
    public Afinitat(String codiAfin, Oferta oferta, Empresa empresa,Estat estat,
            ArrayList<Habilitat> candidats){
        this.codiAfin=codiAfin;
        this.oferta=oferta;
        this.empresa=empresa;
        this.estat=estat;
        this.candidats=candidats;
    }
    public Afinitat(){
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCodiAfin() {
        return codiAfin;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public Estat getEstat() {
        return estat;
    }

    public ArrayList<Habilitat> getCandidats() {
        return candidats;
    }

    public void setCodiAfin(String codiAfin) {
        this.codiAfin = codiAfin;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public void setEstat(Estat estat) {
        this.estat = estat;
    }

    public void setCandidats(ArrayList<Habilitat> candidats) {
        this.candidats = candidats;
    }

  
}
