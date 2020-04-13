
package towork.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Usuari {
    
    protected Integer codi;
    protected String nom;           
    protected String dniNif;
    protected String adreca;
    protected String poblacio;
    protected String provincia;
    protected String telefon;
    protected String email;
    protected String observacions;
    protected String pass;
    protected String cPass;
    
   

    public Usuari(Integer codi,String nom, String dniNif, String adreca, String poblacio, String provincia,String telefon, String email, String observacions, String pass, String cPass) {   
        this.codi=codi;
        this.nom = nom;
        this.dniNif = dniNif;
        this.adreca = adreca;
        this.poblacio=poblacio;
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

    public String getPoblacio() {
        return poblacio;
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

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
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
