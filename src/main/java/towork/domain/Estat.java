
package towork.domain;

public class Estat {
    private String codiEstat;
    private String descripcio;
    
    public Estat(String codiEstat, String descripcio){
        this.codiEstat=codiEstat;
        this.descripcio=descripcio;
    }
    public Estat(){
    
    }

    public String getCodiEstat() {
        return codiEstat;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setCodiEstat(String codiEstat) {
        this.codiEstat = codiEstat;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
    
}
