package towork.domain;

import java.io.Serializable;
import java.util.List;

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