package towork.formularis;

import java.io.Serializable;
import java.util.List;
import towork.domain.Candidatura;

public class LlistaCandidatures  implements Serializable{
    
      private List<Candidatura> llista;
      
      public LlistaCandidatures() {
      }
      
      public LlistaCandidatures(List<Candidatura> llista) {
            this.llista = llista;
      }

      public List<Candidatura> getLlista() {
            return llista;
      }

      public void setLlista(List<Candidatura> llista) {
            this.llista = llista;
      }
         
}