package towork.formularis;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class LlistaSectors  implements Serializable{
 
      private Map<Integer, String> llista;
      
      public LlistaSectors() {
            Map<Integer, String> sectors = new HashMap<>();
            sectors.put(1,"Activitats físiques i esportives");
            sectors.put(2,"Adminstració i gestió");
            sectors.put(3,"Agricultura i ramaderia");
            sectors.put(4,"Indústria alimentària");
            sectors.put(5,"Comerç");
            sectors.put(6,"Construcció");
            sectors.put(7,"Disseny i arts gràfiques");
            sectors.put(8,"Educació");
            sectors.put(9,"Finances");
            sectors.put(10,"Informàtica");
            sectors.put(11,"Enyingeria");
            sectors.put(12,"Legal");
            sectors.put(13,"Màrqueting i comunicació");
            sectors.put(14,"Recursos humans");
            sectors.put(15,"Sanitari");
            sectors.put(16,"Turisme");
            sectors.put(17,"Hostaleria i turisme");
            sectors.put(18,"Mediambiental");
            sectors.put(19,"Pesca i agricultura");
            sectors.put(20,"Química");
            sectors.put(21,"Estètica i perruqueria");
            
            this.setLlista(sectors);
      }
      
      public LlistaSectors(Map<Integer, String> llista) {
            this.llista = llista;
      }

      public Map<Integer, String> getLlista() {
            return llista;
      }

      public void setLlista(Map<Integer, String> llista) {
            this.llista = llista;
      }
         
}