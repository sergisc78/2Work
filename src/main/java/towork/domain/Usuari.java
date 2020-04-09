
package towork.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity

public class Usuari implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @NotNull
    @Column (name="codi")
    protected Integer codi;
    
    
    @NotNull
    @Size(max=20)
    @Column (name="nom")
    protected String nom;
    
    
    @NotNull
    @Size(max=9)
    @Column (name="dniNif")            
    protected String dniNif;
    
    @NotNull
    @Size(max=50)
    @Column (name="adreca")
    protected String adreca;
    
    @NotNull
    @Size(max=50)
    @Column (name="ciutat")
    protected String ciutat;
    
    @NotNull
    @Size(max=50)
    @Column (name="provincia")
    protected String provincia;
    
    @NotNull
    @Size(max=12)
    @Column (name="telefon")
    protected String telefon;
    
    @Size(max=50)
    @Pattern(regexp="^(.+)@(.+)$",message="El correu no és vàlid")
    @Column (name="email")
    protected String email;
    
    @Size(max=500)
    @Column (name="observacions")
    protected String observacions;
    
    @NotNull
    @Size(max=8)
    @Column (name="pass")
    protected String pass;
    
    @NotNull
    @Size(max=8)
    @Column (name="cPass")
    protected String cPass;
    
   

    public Usuari(Integer codi,String nom, String dniNif, String adreca, String ciutat, String provincia,String telefon, String email, String observacions, String pass, String cPass) {   
        this.codi=codi;
        this.nom = nom;
        this.dniNif = dniNif;
        this.adreca = adreca;
        this.ciutat=ciutat;
        this.provincia=provincia;
        this.telefon = telefon;
        this.email = email;
        this.observacions = observacions;
        this.pass = pass;
        this.cPass = cPass;
    }

    public Usuari() {
    }

    public Integer getCodi() {
        return codi;
    }
    
    public String getNom() {
        return nom;
    }

    public String getDniNif() {
        return dniNif;
    }

    public String getAdreca() {
        return adreca;
    }

    public String getCiutat() {
        return ciutat;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }
    
    public String getObservacions() {
          return observacions;
    }

    public String getPass() {
        return pass;
    }

    public String getcPass() {
        return cPass;
    }

    public void setCodi(Integer codi) {
        this.codi = codi;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDniNif(String dniNif) {
        this.dniNif = dniNif;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setObservacions(String observacions) {
          this.observacions = observacions;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setcPass(String cPass) {
        this.cPass = cPass;
    }

    
}
