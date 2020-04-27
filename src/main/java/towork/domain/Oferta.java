
package towork.domain;

import java.util.ArrayList;


public class Oferta {
    
    private Integer codiOferta;
    private Integer codiEmpresa;
    private String titolOferta;
    private Integer ocupacio;
    private String poblacio;
    private String provincia;
    private Double sou;
    private String horari;
    private String tipusContracte;
    private Integer formacio;
    private String estat;
    private String descripcio;
    
    private ArrayList<Integer> habilitats = new ArrayList();
    private ArrayList<Integer> candidats = new ArrayList();

    public Oferta(Integer codiOferta, Integer codiEmpresa, String titolOferta, Integer ocupacio,String poblacio, String provincia, Double sou, String horari, String tipusContracte, Integer formacio, String estat, String descripcio) {
        this.codiOferta = codiOferta;
        this.codiEmpresa = codiEmpresa;
        this.titolOferta = titolOferta;
        this.ocupacio=ocupacio;
        this.poblacio=poblacio;
        this.provincia=provincia;
        this.sou = sou;
        this.horari = horari;
        this.tipusContracte = tipusContracte;
        this.formacio = formacio;
        this.estat = estat;
        this.descripcio = descripcio;
        this.habilitats = new ArrayList<Integer>();
        this.candidats =new ArrayList<Integer>();
    }

    public Oferta() {
    }

    
    // getters
    
    
    public Integer getCodiOferta() {
        return codiOferta;
    }

    public Integer getCodiEmpresa() {
        return codiEmpresa;
    }

    public String getTitolOferta() {
        return titolOferta;
    }

    public Integer getOcupacio() {
        return ocupacio;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public String getProvincia() {
        return provincia;
    }

    public Double getSou() {
        return sou;
    }

    public String getHorari() {
        return horari;
    }

    public String getTipusContracte() {
        return tipusContracte;
    }

    public Integer getFormacio() {
        return formacio;
    }

    public String getEstat() {
        return estat;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public ArrayList<Integer> getHabilitats() {
        return habilitats;
    }

    public ArrayList<Integer> getCandidats() {
        return candidats;
    }

    // setters
    
    public void setCodiOferta(Integer codiOferta) {
        this.codiOferta = codiOferta;
    }

    public void setCodiEmpresa(Integer codiEmpresa) {
        this.codiEmpresa = codiEmpresa;
    }

    public void setTitolOferta(String titolOferta) {
        this.titolOferta = titolOferta;
    }

    public void setOcupacio(Integer ocupacio) {
        this.ocupacio = ocupacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setSou(Double sou) {
        this.sou = sou;
    }

    public void setHorari(String horari) {
        this.horari = horari;
    }

    public void setTipusContracte(String tipusContracte) {
        this.tipusContracte = tipusContracte;
    }

    public void setFormacio(Integer formacio) {
        this.formacio = formacio;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setHabilitats(ArrayList<Integer> habilitats) {
        this.habilitats = habilitats;
    }

    public void setCandidats(ArrayList<Integer> candidats) {
        this.candidats = candidats;
    }
    
}
