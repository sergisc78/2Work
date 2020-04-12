package towork.formularis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import towork.domain.Ocupacio;

public final class LlistaOcupacions  implements Serializable{
 
      private List<Ocupacio> llista;
      
      public LlistaOcupacions() {
            List<Ocupacio> ocupacions = new ArrayList<>();
            ocupacions.add(new Ocupacio(1,"Acoblador elements prefabricats edificis"));
            ocupacions.add(new Ocupacio(2,"Acompanyant o persona de companyia"));
            ocupacions.add(new Ocupacio(3,"Actor/actriu"));
            ocupacions.add(new Ocupacio(4,"Adober de cuirs i pells"));
            ocupacions.add(new Ocupacio(5,"Adornista de pedra o marbre"));
            ocupacions.add(new Ocupacio(6,"Advocat"));
            
            // Hauriem de seguir omplint la llista
            
            this.setLlista(ocupacions);
      }
      
      public LlistaOcupacions(List<Ocupacio> llista) {
            this.llista = llista;
      }

      public List<Ocupacio> getLlista() {
            return llista;
      }

      public void setLlista(List<Ocupacio> llista) {
            this.llista = llista;
      }
         
}