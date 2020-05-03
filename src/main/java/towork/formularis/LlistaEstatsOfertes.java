package towork.formularis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class LlistaEstatsOfertes  implements Serializable{
 
      private List<String> llista;
      
      public LlistaEstatsOfertes() {
            List<String> estats = new ArrayList<>();
            // A la bbdd tenim assignat un màxim de 10 caracters a aquest valor. Si ens passem petarà.
            estats.add("Pública");
            estats.add("No pública");
            this.setLlista(estats);
      }
      
      public LlistaEstatsOfertes(List<String> llista) {
            this.llista = llista;
      }

      public List<String> getLlista() {
            return llista;
      }

      public void setLlista(List<String> llista) {
            this.llista = llista;
      }
         
}