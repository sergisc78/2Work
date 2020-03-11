
package towork.domain;

public class Habilitat {
    private String codiHab;
    private String nomHab;
    
    public Habilitat(String codiHab, String nomHab){
        this.codiHab=codiHab;
        this.nomHab=nomHab;
    }
    public Habilitat(){
    }

    public String getCodiHab() {
        return codiHab;
    }

    public String getNomHab() {
        return nomHab;
    }

    public void setCodiHab(String codiHab) {
        this.codiHab = codiHab;
    }

    public void setNomHab(String nomHab) {
        this.nomHab = nomHab;
    }
    
}
