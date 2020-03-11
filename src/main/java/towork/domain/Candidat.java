
package towork.domain;

import java.util.Date;

public class Candidat extends Usuari{
    protected String codiCand;
    protected String cognoms;
    protected Date dataNaix;
    protected String formacio;
    protected String formPostUni;
    protected String habilitat1;
    protected String habilitat2;
    protected String habilitat3;
    protected String habilitat4;
    protected String habilitat5;
    protected String habilitat6;
    protected String habilitat7;
    
    public Candidat(String nom, String adreca, String telefon, 
            String email, String pass, String cPass, String sector,
            String codiCand,String cognoms,Date dataNaix,String formacio,
            String formPostUni, String habilitat1,String habilitat2,
            String habilitat3, String habilitat4, String habilitat5,
            String habilitat6,String habilitat7){
        
            super(nom,adreca,telefon,email,pass,cPass,sector);
            this.codiCand=codiCand;
            this.cognoms=cognoms;
            this.dataNaix=dataNaix;
            this.formacio=formacio;
            this.formPostUni=formPostUni;
            this.habilitat1=habilitat1;
            this.habilitat2=habilitat2;
            this.habilitat3=habilitat3;
            this.habilitat4=habilitat4;
            this.habilitat5=habilitat5;
            this.habilitat6=habilitat6;
            this.habilitat7=habilitat7;
    }
    public Candidat(){
        super();
    }

    public String getCodiCand() {
        return codiCand;
    }

    public String getCognoms() {
        return cognoms;
    }

    public Date getDataNaix() {
        return dataNaix;
    }

    public String getFormacio() {
        return formacio;
    }

    public String getFormPostUni() {
        return formPostUni;
    }

    public String getHabilitat1() {
        return habilitat1;
    }

    public String getHabilitat2() {
        return habilitat2;
    }

    public String getHabilitat3() {
        return habilitat3;
    }

    public String getHabilitat4() {
        return habilitat4;
    }

    public String getHabilitat5() {
        return habilitat5;
    }

    public String getHabilitat6() {
        return habilitat6;
    }

    public String getHabilitat7() {
        return habilitat7;
    }

    public void setCodiCand(String codiCand) {
        this.codiCand = codiCand;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public void setDataNaix(Date dataNaix) {
        this.dataNaix = dataNaix;
    }

    public void setFormacio(String formacio) {
        this.formacio = formacio;
    }

    public void setFormPostUni(String formPostUni) {
        this.formPostUni = formPostUni;
    }

    public void setHabilitat1(String habilitat1) {
        this.habilitat1 = habilitat1;
    }

    public void setHabilitat2(String habilitat2) {
        this.habilitat2 = habilitat2;
    }

    public void setHabilitat3(String habilitat3) {
        this.habilitat3 = habilitat3;
    }

    public void setHabilitat4(String habilitat4) {
        this.habilitat4 = habilitat4;
    }

    public void setHabilitat5(String habilitat5) {
        this.habilitat5 = habilitat5;
    }

    public void setHabilitat6(String habilitat6) {
        this.habilitat6 = habilitat6;
    }

    public void setHabilitat7(String habilitat7) {
        this.habilitat7 = habilitat7;
    }
    
}
