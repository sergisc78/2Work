
package towork.domain;

public class Usuari {

    String nom;
    String adreca;
    String telefon;
    String email;
    String pass;
    String cPass;
    String sector;

    public Usuari(String nom, String adreca, String telefon, String email, String pass, String cPass, String sector) {
        this.nom = nom;
        this.adreca = adreca;
        this.telefon = telefon;
        this.email = email;
        this.pass = pass;
        this.cPass = cPass;
        this.sector = sector;
    }
    public Usuari(){
    }
    // GETTERS
    
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

    public String getSector() {
        return sector;
    }
    
    //SETTERS
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

    public void setSector(String sector) {
        this.sector = sector;
    }

}
