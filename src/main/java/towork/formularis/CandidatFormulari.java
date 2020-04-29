
package towork.formularis;


import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.format.annotation.DateTimeFormat;
import towork.domain.Experiencia;
import towork.domain.Usuari;

/**
 * 
 * @author Daniel Sevilla i Junyent
 * Aquesta classe és pràcticament igual a la classe Candidat però adaptada a les necessitats dels formularis:
 *    L'atribut dataNaix és de tipus LocalDate (data sense dades horàries ni timezone)
 *    L'atribut experiències serà un array d'objectes de tipus Experiencia, perque ha de tenir les dades que conté aquest tipus, no un array de codis d'Experiències
 * 
 */
public class CandidatFormulari extends Usuari{
    
    protected String cognoms;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate dataNaix;
    protected Integer formacio;
    protected Integer ocupacio;
    
    protected ArrayList<Integer> habilitats = new ArrayList();
    protected ArrayList<Experiencia> experiencies = new ArrayList();


    public CandidatFormulari (Integer codi,String nom,String cognoms, String dniNif, LocalDate dataNaix,String adreca, String poblacio,String provincia,String telefon, String email, String observacions, String pass, String cPass,  Integer formacio,Integer ocupacio) {
        super(codi,nom,dniNif,adreca,poblacio,provincia,telefon,email,observacions,pass,cPass);
        this.cognoms = cognoms;
        this.dataNaix = dataNaix;
        this.formacio = formacio;
        this.ocupacio=ocupacio;   
        this.habilitats= new ArrayList<Integer>();
        this.experiencies= new ArrayList<Experiencia>();
    }
    
    public CandidatFormulari() {
    }

    public String getCognoms() {
        return cognoms;
    }

    public LocalDate getDataNaix() {
        return dataNaix;
    }

    public Integer getFormacio() {
        return formacio;
    }

    public Integer getOcupacio() {
        return ocupacio;
    } 

    public ArrayList<Integer> getHabilitats() {
        return habilitats;
    }

    public ArrayList<Experiencia> getExperiencies() {
        return experiencies;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public void setDataNaix(LocalDate dataNaix) {
        this.dataNaix = dataNaix;
    }

    public void setFormacio(Integer codiFormacio) {
        this.formacio = codiFormacio;
    }

    public void setOcupacio(Integer ocupacio) {
        this.ocupacio = ocupacio;
    }

    public void setHabilitats(ArrayList<Integer> habilitats) {
        this.habilitats = habilitats;
    }

    public void setExperiencies(ArrayList<Experiencia> experiencies) {
        this.experiencies = experiencies;
    }

        
}
