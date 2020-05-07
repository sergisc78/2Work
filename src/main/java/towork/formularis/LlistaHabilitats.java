package towork.formularis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import towork.domain.Habilitat;

public final class LlistaHabilitats implements Serializable {

    private List<Habilitat> llista;

    public LlistaHabilitats() {
    }

    public LlistaHabilitats(boolean plena) {
        List<Habilitat> habs = new ArrayList<>();
        habs.add(new Habilitat(1, "Costura", 1));
        habs.add(new Habilitat(2, "Python", 81));
        habs.add(new Habilitat(3, "Pastisseria moderna", 3));
        habs.add(new Habilitat(4, "Escriptura creativa", 4));
        habs.add(new Habilitat(5, "Lluita lliure africana", 5));
        habs.add(new Habilitat(6, "Gestió de conflictes", 6));
        habs.add(new Habilitat(7, "Assembler", 1));
        habs.add(new Habilitat(8, "Cures intensives", 2));
        habs.add(new Habilitat(9, "Dramatúrgia", 3));
        habs.add(new Habilitat(10, "Disseny d'interiors", 4));
        habs.add(new Habilitat(11, "Modelat 3D", 5));
        habs.add(new Habilitat(12, "Tècniques de mediació", 6));
        habs.add(new Habilitat(13, "SCRUM", 1));
        habs.add(new Habilitat(14, "InDesign", 2));
        habs.add(new Habilitat(15, "Sentit comú avançat", 3));
        habs.add(new Habilitat(16, "Blender", 4));
        habs.add(new Habilitat(17, "Adobe Illustrator", 5));
        habs.add(new Habilitat(18, "Dots comunicatives", 6));
        habs.add(new Habilitat(19, "Javascript", 81));
        habs.add(new Habilitat(20, "Java", 81));
        habs.add(new Habilitat(21, "SQL", 81));
        habs.add(new Habilitat(22, "Bootstrap", 81));
        habs.add(new Habilitat(23, "NoSQL", 81));
        habs.add(new Habilitat(24, "PHP", 81));
        habs.add(new Habilitat(25, "Node.js", 81));
        habs.add(new Habilitat(26, "Angular.js", 81));
        habs.add(new Habilitat(27, "React.js", 81));
        habs.add(new Habilitat(28, "Vue.js", 81));
        habs.add(new Habilitat(29, "Sympfony", 81));
        habs.add(new Habilitat(30, "Laravel", 81));
        habs.add(new Habilitat(31, "Git", 81));
        habs.add(new Habilitat(32, "Django", 81));
        habs.add(new Habilitat(33, "Pedagogia activa", 65));
        habs.add(new Habilitat(34, "Habilitats socials", 65));
        habs.add(new Habilitat(35, "Tecnologia educativa", 65));
        habs.add(new Habilitat(36, "Pedagogia social", 65));
        habs.add(new Habilitat(37, "Gestió de conflictes", 65));

        // Afegim a la llista les habilitats creades
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
