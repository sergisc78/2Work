
package towork.domain;


public class Empresa extends Usuari{
    protected String codiEmp;
    protected String web;
    protected Integer tamany;
    
    public Empresa(String nom, String adreca, String telefon, String email, 
            String pass, String cPass, String sector,String codiEmp, String web,Integer tamany){
        super(nom,adreca,telefon,email,pass,cPass,sector);
        this.codiEmp=codiEmp;
        this.web=web;
        this.tamany=tamany;
    }
    public Empresa(){
        super();
    }

    public String getCodiEmp() {
        return codiEmp;
    }

    public String getWeb() {
        return web;
    }

    public Integer getTamany() {
        return tamany;
    }

    public void setCodiEmp(String codiEmp) {
        this.codiEmp = codiEmp;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setTamany(Integer tamany) {
        this.tamany = tamany;
    }
    
}
