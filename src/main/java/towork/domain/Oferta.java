
package towork.domain;

public class Oferta {
    private String refOferta;
    private String codiEmp;
    private String habilitat1;
    private String habilitat2;
    private String habilitat3;
    private String habilitat4;
    private String habilitat5;
    private String habilitat6;
    private String habilitat7;
    
    public Oferta(String refOferta, String codiEmp,String habilitat1,
        String habilitat2,String habilitat3,String habilitat4,
        String habilitat5,String habilitat6,String habilitat7){
        
        this.refOferta=refOferta;
        this.codiEmp=codiEmp;
        this.habilitat1=habilitat1;
        this.habilitat2=habilitat2;
        this.habilitat3=habilitat3;
        this.habilitat4=habilitat4;
        this.habilitat5=habilitat5;
        this.habilitat6=habilitat6;
        this.habilitat7=habilitat7;
    }
    public Oferta(){
    
    }

    public String getRefOferta() {
        return refOferta;
    }

    public String getCodiEmp() {
        return codiEmp;
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

    public void setRefOferta(String refOferta) {
        this.refOferta = refOferta;
    }

    public void setCodiEmp(String codiEmp) {
        this.codiEmp = codiEmp;
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
