
package towork.domain;

public class Afinitat {
    private String codiAfin;
    private String refOferta;
    private String codiCand;
    private String estat;
    
    public Afinitat(String codiAfin, String refOferta, String codiCand, String estat){
        this.codiAfin=codiAfin;
        this.refOferta=refOferta;
        this.codiCand=codiAfin;
        this.estat=estat;
    
    }
    public Afinitat(){
    }

    public String getCodiAfin() {
        return codiAfin;
    }

    public String getRefOferta() {
        return refOferta;
    }

    public String getCodiCand() {
        return codiCand;
    }

    public String getEstat() {
        return estat;
    }

    public void setCodiAfin(String codiAfin) {
        this.codiAfin = codiAfin;
    }

    public void setRefOferta(String refOferta) {
        this.refOferta = refOferta;
    }

    public void setCodiCand(String codiCand) {
        this.codiCand = codiCand;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }
    
}
