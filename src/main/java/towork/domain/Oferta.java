
package towork.domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ofertes")
public class Oferta implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column(name="refOferta")
    private String refOferta;
    
    @NotNull
    @Column(name="codiEmp")
    private String codiEmp;
    
    @Column(name="habilitats")
    protected ArrayList<Habilitat> habilitats = new ArrayList();
    
    public Oferta(String refOferta, String codiEmp,ArrayList<Habilitat> habilitats){
        
        this.refOferta=refOferta;
        this.codiEmp=codiEmp;
        this.habilitats=habilitats;        
    }
    public Oferta(){
    
    }

    public String getRefOferta() {
        return refOferta;
    }

    public String getCodiEmp() {
        return codiEmp;
    }

    public ArrayList<Habilitat> getHabilitats() {
        return habilitats;
    }

    public void setRefOferta(String refOferta) {
        this.refOferta = refOferta;
    }

    public void setCodiEmp(String codiEmp) {
        this.codiEmp = codiEmp;
    }

    public void setHabilitats(ArrayList<Habilitat> habilitats) {
        this.habilitats = habilitats;
    }

    
}
