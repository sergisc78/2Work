package towork.formularis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import towork.domain.Ocupacio;

public final class LlistaOcupacions implements Serializable {

    private List<Ocupacio> llista;

    public LlistaOcupacions() {
        List<Ocupacio> ocupacions = new ArrayList<>();
        ocupacions.add(new Ocupacio(1, "Acoblador elements prefabricats edificis"));
        ocupacions.add(new Ocupacio(2, "Acompanyant o persona de companyia"));
        ocupacions.add(new Ocupacio(3, "Actor/actriu"));
        ocupacions.add(new Ocupacio(4, "Adober de cuirs i pells"));
        ocupacions.add(new Ocupacio(5, "Adornista de pedra o marbre"));
        ocupacions.add(new Ocupacio(6, "Advocat"));
        ocupacions.add(new Ocupacio(7, "Agent assegurances"));
        ocupacions.add(new Ocupacio(8, "Agent assegurances per telèfon"));
        ocupacions.add(new Ocupacio(9, "Agent de la propietat immobliliaria"));
        ocupacions.add(new Ocupacio(10, "Agent de turisme en viatges"));
        ocupacions.add(new Ocupacio(11, "Ajudant esteticista"));
        ocupacions.add(new Ocupacio(12, "Ajudant odontologia"));
        ocupacions.add(new Ocupacio(13, "Ajudant de perruqueria"));
        ocupacions.add(new Ocupacio(14, "Ajustador mecànic de fabricació eines"));
        ocupacions.add(new Ocupacio(15, "Analista de sistemes informàtics"));
        ocupacions.add(new Ocupacio(16, "Artesà"));
        ocupacions.add(new Ocupacio(17, "Auxiliar ajuda a domicili"));
        ocupacions.add(new Ocupacio(18, "Auxiliar de laboratori anàlisis clíniques"));
        ocupacions.add(new Ocupacio(19, "Agent de turisme en viatges"));
        ocupacions.add(new Ocupacio(19, "Biòleg"));
        ocupacions.add(new Ocupacio(20, "Bugader a mà en bugaderia"));
        ocupacions.add(new Ocupacio(21, "caixer autoservei"));
        ocupacions.add(new Ocupacio(22, "Calefactor lampista"));
        ocupacions.add(new Ocupacio(23, "Cambrer de bar"));
        ocupacions.add(new Ocupacio(24, "Mainadera"));
        ocupacions.add(new Ocupacio(25, "Cap de cuina"));
        ocupacions.add(new Ocupacio(26, "Cap equip ajustadors equips elèctrics"));
        ocupacions.add(new Ocupacio(27, "Comercial"));
        ocupacions.add(new Ocupacio(28, "Comptable"));
        ocupacions.add(new Ocupacio(29, "Conductor d´ambulàncies"));
        ocupacions.add(new Ocupacio(30, "Conductor"));
        ocupacions.add(new Ocupacio(31, "Cuiner"));
        ocupacions.add(new Ocupacio(32, "Cultivador"));
        ocupacions.add(new Ocupacio(33, "Dependent"));
        ocupacions.add(new Ocupacio(34, "Director general empreses privades"));
        ocupacions.add(new Ocupacio(35, "Dissenyador"));
        ocupacions.add(new Ocupacio(36, "Dona o mosso de neteja oficines"));
        ocupacions.add(new Ocupacio(37, "Economista"));
        ocupacions.add(new Ocupacio(38, "Educador social"));
        ocupacions.add(new Ocupacio(39, "Electricista"));
        ocupacions.add(new Ocupacio(40, "Enginyer en electrònica"));
        ocupacions.add(new Ocupacio(41, "Enginyer en logística"));
        ocupacions.add(new Ocupacio(42, "Enginyer tècnic en comunicacions"));
        ocupacions.add(new Ocupacio(43, "Esportista professional"));
        ocupacions.add(new Ocupacio(44, "Farmacèutic"));
        ocupacions.add(new Ocupacio(45, "Filòleg"));
        ocupacions.add(new Ocupacio(46, "Fuster taller tancaments metall"));
        ocupacions.add(new Ocupacio(47, "Gerent bar"));
        ocupacions.add(new Ocupacio(48, "Gerent empresa"));
        ocupacions.add(new Ocupacio(49, "Guia"));
        ocupacions.add(new Ocupacio(50, "Historiador"));
        ocupacions.add(new Ocupacio(51, "Hostessa i auxiliar de vol"));
        ocupacions.add(new Ocupacio(52, "Infermer"));
        ocupacions.add(new Ocupacio(53, "Conduccions ventilació"));
        ocupacions.add(new Ocupacio(54, "Instrumentista"));
        ocupacions.add(new Ocupacio(55, "Joier"));
        ocupacions.add(new Ocupacio(56, "Lampista"));
        ocupacions.add(new Ocupacio(57, "Locutor de ràdio o televisió"));
        ocupacions.add(new Ocupacio(58, "Logopeda"));
        ocupacions.add(new Ocupacio(59, "Llegidor de comptadors"));
        ocupacions.add(new Ocupacio(60, "Llevadora"));
        ocupacions.add(new Ocupacio(61, "Manyà"));
        ocupacions.add(new Ocupacio(62, "Maquinista"));
        ocupacions.add(new Ocupacio(63, "Matemàtic"));
        ocupacions.add(new Ocupacio(64, "Mecànic automòbils turismes i furgonetes"));
        ocupacions.add(new Ocupacio(65, "Mestre ensenyament"));
        ocupacions.add(new Ocupacio(66, "Metge"));
        ocupacions.add(new Ocupacio(67, "Model"));
        ocupacions.add(new Ocupacio(68, "Muntador"));
        ocupacions.add(new Ocupacio(69, "Músic de sala de festes"));
        ocupacions.add(new Ocupacio(70, "Naturòpata"));
        ocupacions.add(new Ocupacio(71, "Netejador"));
        ocupacions.add(new Ocupacio(72, "Odontòleg"));
        ocupacions.add(new Ocupacio(73, "Operador"));
        ocupacions.add(new Ocupacio(74, "Paleta"));
        ocupacions.add(new Ocupacio(75, "Pastisser"));
        ocupacions.add(new Ocupacio(76, "Peó"));
        ocupacions.add(new Ocupacio(77, "Pintor automòbils"));
        ocupacions.add(new Ocupacio(78, "Pintor"));
        ocupacions.add(new Ocupacio(79, "Professor classes particulars"));
        ocupacions.add(new Ocupacio(80, "Programador informàtic aplicacions de gestió"));
        ocupacions.add(new Ocupacio(81, "Programador informàtic"));
        ocupacions.add(new Ocupacio(82, "Químic"));
        ocupacions.add(new Ocupacio(83, "Recepcionista"));
        ocupacions.add(new Ocupacio(84, "Rellotger"));
        ocupacions.add(new Ocupacio(85, "Restaurador obres art"));
        ocupacions.add(new Ocupacio(86, "Sabater"));
        ocupacions.add(new Ocupacio(87, "Socorrista"));
        ocupacions.add(new Ocupacio(88, "Soldador"));
        ocupacions.add(new Ocupacio(89, "Tallador"));
        ocupacions.add(new Ocupacio(90, "Tastador"));
        ocupacions.add(new Ocupacio(91, "Traductor"));
        ocupacions.add(new Ocupacio(92, "Treballador social"));
        ocupacions.add(new Ocupacio(93, "Venedor a domicili"));
        ocupacions.add(new Ocupacio(94, "Veterinari"));
        ocupacions.add(new Ocupacio(95, "Vidrier"));
        ocupacions.add(new Ocupacio(96, "Xarcuter"));
        ocupacions.add(new Ocupacio(97, "Zelador hospitalari"));
        ocupacions.add(new Ocupacio(98, "Altres"));

        // Afegim a la llista les ocupacions creades
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
