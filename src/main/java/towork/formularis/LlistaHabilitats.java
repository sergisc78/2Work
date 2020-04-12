package towork.formularis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import towork.domain.Habilitat;

public final class LlistaHabilitats  implements Serializable{
 
      private List<Habilitat> llista;
      
      public LlistaHabilitats() {
      }
      
      public LlistaHabilitats(boolean plena) {
            List<Habilitat> habs = new ArrayList<>();
            habs.add(new Habilitat(1,"Costura",1));
            habs.add(new Habilitat(2,"Python",2));
            habs.add(new Habilitat(3,"Pastisseria moderna",3));
            habs.add(new Habilitat(4,"Escriptura creativa",4));
            habs.add(new Habilitat(5,"Lluita lliure africana",5));
            habs.add(new Habilitat(6,"Enologia aplicada",6));
            habs.add(new Habilitat(7,"Assembler",1));
            habs.add(new Habilitat(8,"Pintura a l'oli",2));
            habs.add(new Habilitat(9,"Dramatúrgia",3));
            habs.add(new Habilitat(10,"Disseny d'interiors",4));
            habs.add(new Habilitat(11,"Modelat 3D",5));
            habs.add(new Habilitat(12,"Tècniques de mediació",6));
            habs.add(new Habilitat(13,"SCRUM",1));
            habs.add(new Habilitat(14,"InDesign",2));
            habs.add(new Habilitat(15,"Sentit comú avançat",3));
            habs.add(new Habilitat(16,"Blender",4));
            habs.add(new Habilitat(17,"Adobe Illustrator",5));
            habs.add(new Habilitat(18,"Tècniques de guió",6));
            // Hauriem de seguir omplint la llista
            
            this.setLlista(habs);
      }
      
      public LlistaHabilitats(List<Habilitat> llista) {
            this.llista = llista;
      }

      public List<Habilitat> getLlista() {
            return llista;
      }

      public void setLlista(List<Habilitat> llista) {
            this.llista = llista;
      }
         
}