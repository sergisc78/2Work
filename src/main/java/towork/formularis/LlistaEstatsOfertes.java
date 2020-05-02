package towork.formularis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import towork.domain.Formacio;

public final class LlistaFormacions  implements Serializable{
 
      private List<Formacio> llista;
      
      public LlistaFormacions() {
            List<Formacio> forms = new ArrayList<>();
            forms.add(new Formacio(1,"Sense estudis finalitzats"));
            forms.add(new Formacio(2,"Graduat escolar"));
            forms.add(new Formacio(3,"ESO"));
            forms.add(new Formacio(4,"Batxillerat"));
            forms.add(new Formacio(5,"Grau mig en conducció Activitats Físicoesportives en el Medi Natural"));
            forms.add(new Formacio(6,"Grau mig en Jardineria i Floristeria"));
            // Hauriem de seguir omplint la llista
            
            this.setLlista(forms);
      }
      
      public LlistaFormacions(List<Formacio> llista) {
            this.llista = llista;
      }

      public List<Formacio> getLlista() {
            return llista;
      }

      public void setLlista(List<Formacio> llista) {
            this.llista = llista;
      }
         
}