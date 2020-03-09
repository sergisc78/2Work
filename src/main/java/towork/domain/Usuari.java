
package towork.domain;

public class Usuari {

    String nom;
    String adreça;
    Integer telefon;
    String email;
    String pass;
    String cPass;
    Boolean sector;

    public Usuari(String nom, String adreça, Integer telefon, String email, String pass, String cPass, Boolean sector) {
        this.nom = nom;
        this.adreça = adreça;
        this.telefon = telefon;
        this.email = email;
        this.pass = pass;
        this.cPass = cPass;
        this.sector = sector;
    }

    // GETTERS
    
    public String getNom() {
        return nom;
    }
    public String getAdreça() {
        return adreça;
    }

    public Integer getTelefon() {
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

    public Boolean getSector() {
        return sector;
    }
    
    //SETTERS
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdreça(String adreça) {
        this.adreça = adreça;
    }

    public void setTelefon(Integer telefon) {
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

    public void setSector(Boolean sector) {
        this.sector = sector;
    }

}
