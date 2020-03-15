
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
    @Column (name="dniNif")            
    protected String dniNif;
    
    @NotNull
    @Size(max=75)
    @Column (name="nom")
    protected String nom;
    
    @NotNull
    @Size(max=100)
    @Column (name="adreca")
    protected String adreca;
    
    @NotNull
    @Size(max=20)
    @Column (name="telefon")
    protected String telefon;
    
    @Size(max=100)
    @Pattern(regexp="^(.+)@(.+)$",message="El correu no és vàlid")
    @Column (name="email")
    protected String email;
    
    @NotNull
    @Size(max=30, min=5)
    @Column (name="pass")
    protected String pass;
    
    @NotNull
    @Size(max=30, min=5)
    @Column (name="cPass")
    protected String cPass;
    
   

    public Usuari(String dniNif, String nom, String adreca, String telefon, String email, String pass, String cPass) {
        this.dniNif = dniNif;
        this.nom = nom;
        this.adreca = adreca;
        this.telefon = telefon;
        this.email = email;
        this.pass = pass;
        this.cPass = cPass;
    }

    public Usuari() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDniNif() {
        return dniNif;
    }

    public String getNom() {
        return nom;
    }

    public String getAdreca() {
        return adreca;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getcPass() {
        return cPass;
    }

    public void setDniNif(String dniNif) {
        this.dniNif = dniNif;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setcPass(String cPass) {
        this.cPass = cPass;
    }

    
}
