package towork.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import towork.domain.Candidat;
import towork.formularis.CandidatFormulari;
import towork.domain.Candidatura;
import towork.domain.Empresa;
import towork.domain.Experiencia;
import towork.domain.Formacio;
import towork.domain.Habilitat;
import towork.formularis.LlistaCandidatures;
import towork.formularis.LlistaHabilitats;
import towork.domain.Oferta;
import towork.formularis.LlistaEstatsOfertes;
import towork.formularis.LlistaFormacions;
import towork.formularis.LlistaOcupacions;
import towork.formularis.LlistaSectors;
import towork.service.CandidatService;
import towork.service.EmpresaService;
import towork.service.OfertaService;

@Controller
public class EspaiController {

    @Autowired
    EmpresaService empresaService;
    @Autowired
    CandidatService candidatService;
    @Autowired
    OfertaService ofertaService;

    // Opcions reutilitzables per la barra de navegació
    HashMap<String, String> op_entrarCandidat = new HashMap<>();
    HashMap<String, String> op_entrarEmpresa = new HashMap<>();
    HashMap<String, String> op_logout = new HashMap<>();
    HashMap<String, String> op_inici = new HashMap<>();
    HashMap<String, String> op_iniciAdmin = new HashMap<>();
    HashMap<String, String> op_candidats = new HashMap<>();
    HashMap<String, String> op_candidatures = new HashMap<>();
    HashMap<String, String> op_empreses = new HashMap<>();
    HashMap<String, String> op_ofertesCandidat = new HashMap<>();
    HashMap<String, String> op_ofertesEmpresa = new HashMap<>();
    HashMap<String, String> op_ofertesAdmin = new HashMap<>();
    HashMap<String, String> op_perfilEmpresa = new HashMap<>();
    HashMap<String, String> op_baixaEmpresa = new HashMap<>();
    HashMap<String, String> op_perfilCandidat = new HashMap<>();
    HashMap<String, String> op_baixaCandidat = new HashMap<>();
    HashMap<String, String> op_altaOferta = new HashMap<>();

    // Missatges reutilitzables
    final String msgErrorBBDD = "Hi ha hagut algun problema rebent les dades de la base de dades.";
    final String baseline = "La teva web de cerca de feina";
    final String classeOK = "alert-warning";
    final String classeKO = "alert-danger";

    // Missatges de feedback reutilitzables a les vistes
    HashMap<String, String> fb_TEST = new HashMap<>();
    HashMap<String, String> fb_ofertaDesadaOK = new HashMap<>();
    HashMap<String, String> fb_ofertaNoDesada = new HashMap<>();
    HashMap<String, String> fb_canvisOK = new HashMap<>();
    HashMap<String, String> fb_canvisKO = new HashMap<>();
    HashMap<String, String> fb_problemaBBDD = new HashMap<>();
    HashMap<String, String> fb_inscripcioOfertaOK = new HashMap<>();
    HashMap<String, String> fb_inscripcioOfertaKO = new HashMap<>();
    HashMap<String, String> fb_cancelacioCandidaturaOK = new HashMap<>();
    HashMap<String, String> fb_cancelacioCandidaturaKO = new HashMap<>();
    HashMap<String, String> fb_baixaEmpresaOK = new HashMap<>();
    HashMap<String, String> fb_baixaEmpresaKO = new HashMap<>();
    HashMap<String, String> fb_baixaCandidatOK = new HashMap<>();
    HashMap<String, String> fb_baixaCandidatKO = new HashMap<>();
    HashMap<String, String> fb_baixaOfertaOK = new HashMap<>();
    HashMap<String, String> fb_baixaOfertaKO = new HashMap<>();
    HashMap<String, String> fb_baixaCandidatCorreuNoValid = new HashMap<>();
    HashMap<String, String> fb_baixaEmpresaCorreuNoValid = new HashMap<>();
    HashMap<String, String> fb_logoutOK = new HashMap<>();

    ///// GENEREM OBJECTES DE PROVA QUE HAUREM D'ESBORRAR QUAN TINGUEM CREATS ELS MÈTODES QUE ELS
    //// AGAFARAN DE LA BASE DE DADES
    public Habilitat h1 = new Habilitat();
    public Habilitat h2 = new Habilitat();
    public Habilitat h3 = new Habilitat();
    public Candidat c1 = new Candidat();
    public Candidat c2 = new Candidat();
    public Candidat c3 = new Candidat();
    public Candidat c4 = new Candidat();
    public Candidat c5 = new Candidat();
    public Candidat c6 = new Candidat();
    public Candidat c7 = new Candidat();
    public Candidat c8 = new Candidat();
    public ArrayList<Integer> habs = new ArrayList();
    public Oferta of = new Oferta();
    public List<Candidatura> candidatures;
    public LlistaCandidatures llistaCandidatures = new LlistaCandidatures();
    // public ArrayList<Candidat> candidats = new ArrayList();
    List<String> llistaTipusContracte = new ArrayList();

    // Hashmap que emmagatzema els POSSIBLES ESTATS QUE PODEN  TENIR LES CANDIDATURES
    // Aquesta part potser es pot deixar definida aqui. Valorar si és un bon lloc.
    public HashMap<Integer, String> estatsPossibles = new HashMap<>();

    /**
     *
     * Constructor del controlador, sense paràmetres
     *
     * @author Daniel Sevilla i Junyent
     *
     */
    public EspaiController() {

        // Opcions reutilitzables per la barra de navegació
        op_entrarCandidat.put("paraula", "Candidat");
        op_entrarCandidat.put("url", "/espaiCandidat");
        op_entrarEmpresa.put("paraula", "Empresa");
        op_entrarEmpresa.put("url", "/espaiEmpresa");
        op_logout.put("paraula", "Logout");
        op_logout.put("url", "/j_spring_security_logout");
        op_inici.put("paraula", "Inici");
        op_inici.put("url", "/");
        op_iniciAdmin.put("paraula", "Inici");
        op_iniciAdmin.put("url", "/admin");
        op_candidats.put("paraula", "Candidats");
        op_candidats.put("url", "/candidats");
        op_candidatures.put("paraula", "Candidatures");
        op_candidatures.put("url", "/candidatures");
        op_empreses.put("paraula", "Empreses");
        op_empreses.put("url", "/empreses");
        op_ofertesCandidat.put("paraula", "Ofertes");
        op_ofertesCandidat.put("url", "/espaiCandidat");
        op_ofertesEmpresa.put("paraula", "Ofertes");
        op_ofertesEmpresa.put("url", "/ofertesEmpresa"); // Fins que no la canviem aquesta és la url que porta a la vista on mostrem totes les ofertes
        op_ofertesAdmin.put("paraula", "Ofertes");
        op_ofertesAdmin.put("url", "/ofertesAdmin");
        op_perfilEmpresa.put("paraula", "Perfil");
        op_perfilEmpresa.put("url", "/perfilEmpresa");
        op_perfilCandidat.put("paraula", "Perfil");
        op_perfilCandidat.put("url", "/perfilCandidat");
        op_baixaEmpresa.put("paraula", "Baixa");
        op_baixaEmpresa.put("url", "/eliminaEmpresa");
        op_baixaCandidat.put("paraula", "Baixa");
        op_baixaCandidat.put("url", "/eliminaCandidat");
        op_altaOferta.put("paraula", "Crear oferta");
        op_altaOferta.put("url", "/altaOferta");

        // Missatges de feedback reutilitzables a les vistes
        fb_TEST.put("missatge", "Aquest és un missatge de test.");
        fb_TEST.put("classe", classeOK);
        fb_ofertaDesadaOK.put("missatge", "L'oferta s'ha desat correctament.");
        fb_ofertaDesadaOK.put("classe", classeOK);
        fb_ofertaNoDesada.put("missatge", "L'oferta no ha pogut ésser desada correctament.");
        fb_ofertaNoDesada.put("classe", classeKO);
        fb_canvisOK.put("missatge", "Els canvis s'han desat correctament.");
        fb_canvisOK.put("classe", classeOK);
        fb_canvisKO.put("missatge", "Els canvis no s'han pogut desar correctament.");
        fb_canvisKO.put("classe", classeKO);
        fb_problemaBBDD.put("missatge", msgErrorBBDD);
        fb_problemaBBDD.put("classe", classeKO);
        fb_inscripcioOfertaOK.put("missatge", "Inscripció a l'oferta realitzada correctament.");
        fb_inscripcioOfertaOK.put("classe", classeOK);
        fb_inscripcioOfertaOK.put("missatge", "Inscripció a l'oferta NO realitzada.");
        fb_inscripcioOfertaOK.put("classe", classeKO);
        fb_cancelacioCandidaturaOK.put("missatge", "Cancel·lació de la candidatura realitzada correctament");
        fb_cancelacioCandidaturaOK.put("classe", classeOK);
        fb_cancelacioCandidaturaKO.put("missatge", "Cancel·lació de la candidatura NO realitzada.");
        fb_cancelacioCandidaturaKO.put("classe", classeKO);
        fb_baixaEmpresaOK.put("missatge", "Empresa eliminada correctament.");
        fb_baixaEmpresaOK.put("classe", classeOK);
        fb_baixaEmpresaKO.put("missatge", "L'empresa NO ha pogut ésser eliminada. Contacta amb l'administrador.");
        fb_baixaEmpresaKO.put("classe", classeKO);
        fb_baixaCandidatOK.put("missatge", "La baixa de l'usuari s'ha realitzat correctament.");
        fb_baixaCandidatOK.put("classe", classeOK);
        fb_baixaCandidatKO.put("missatge", "La baixa de l'usuari no s'ha pogut realitzar. Contacta amb l'administrador.");
        fb_baixaCandidatKO.put("classe", classeKO);
        fb_baixaOfertaOK.put("missatge", "L'oferta ha estat eliminada de l'aplicació.");
        fb_baixaOfertaOK.put("classe", classeOK);
        fb_baixaOfertaKO.put("missatge", "L'oferta no ha pogut ésser eliminada de l'aplicació.");
        fb_baixaOfertaKO.put("classe", classeKO);
        fb_baixaCandidatCorreuNoValid.put("missatge", "La baixa del perfil no s'ha realitzat. El correu introduït no correspon amb el del perfil.");
        fb_baixaCandidatCorreuNoValid.put("classe", classeKO);
        fb_baixaEmpresaCorreuNoValid.put("missatge", "La baixa del perfil no s'ha realitzat. El correu introduït no correspon amb el del perfil.");
        fb_baixaEmpresaCorreuNoValid.put("classe", classeKO);
        fb_logoutOK.put("missatge", "Has sortit de l'aplicació. Fins aviat!");
        fb_logoutOK.put("classe", classeOK);

        //// Dono valors als objectes de prova creats 
        // Habilitats
        h1.setNomHab("habilitat1");
        h2.setNomHab("habilitat2");
        h3.setNomHab("habilitat3");

        h1.setCodiHab(1);
        h2.setCodiHab(2);
        h3.setCodiHab(3);

        // Candidats
        c1.setNom("Andreu");
        c1.setCognoms("Albets Albareda");
        c1.setCodi(1);
        c2.setNom("Blanca");
        c2.setCognoms("Blancafort Bonet");
        c2.setCodi(2);
        c3.setNom("Carla");
        c3.setCognoms("Calvet Canudas");
        c3.setCodi(4);
        c4.setNom("Dídac");
        c4.setCognoms("Dentera Deltell");
        c4.setCodi(4);
        c5.setNom("Elisabet");
        c5.setCognoms("Elias Entrena");
        c5.setCodi(5);
        c6.setNom("Francina");
        c6.setCognoms("Franch Floridablanca");
        c6.setCodi(6);
        c7.setNom("Guerau");
        c7.setCognoms("Guiu Gallifa");
        c7.setCodi(7);
        c8.setNom("Helena");
        c8.setCognoms("Humet Herrera");
        c8.setCodi(8);
        // candidats.add(c1);
        // candidats.add(c2);
        // candidats.add(c3);
        // candidats.add(c4);
        // candidats.add(c5);
        // candidats.add(c6);
        // candidats.add(c7);
        // candidats.add(c8);

        // Omplo un arrayList amb les habilitats de prova per afegir-lo a l'objecte de prova
        habs.add(h1.getCodiHab());
        habs.add(h2.getCodiHab());
        habs.add(h3.getCodiHab());

        // Oferta
        of.setCodiOferta(666);
        of.setDescripcio("Aquesta és la descripció de l'oferta blablabla. Aquest text en principi ha podria ser una mica llarg. És l'únic camp que permet explicar lliurement segons quins detalls de l'oferta. Com, per exemple, que pretenen explotar el treballador o bé pagar-li amb hortalisses o objectes d'escriptori usats.");
        of.setEstat("Pendent");
        of.setFormacio(1);
        of.setHabilitats(habs);
        of.setHorari("De 9 a 15h");
        of.setPoblacio("Cardona");
        of.setCodiEmpresa(1);
        of.setSou(25000d);
        of.setTipusContracte("Indefinit");
        of.setTitolOferta("Títol de l'oferta");

        // Candidatures
        candidatures = getListOfCandidatures();
        llistaCandidatures.setLlista(candidatures);

        // Fins aqui els objectes de prova
        // Possibles estats de les candidatures (aquesta part potser es pot deixar definida aqui. Probablement no és el millor lloc)
        estatsPossibles.put(0, "Pendent revisar");
        estatsPossibles.put(1, "Denegada");
        estatsPossibles.put(2, "Aprovada");

        // Possibles valors de l'atribut 'Tipus de contracte'          
        llistaTipusContracte.add("Obra i servei");
        llistaTipusContracte.add("Temporal");
        llistaTipusContracte.add("Indefinit");
        llistaTipusContracte.add("Eventual");
        llistaTipusContracte.add("Formació i aprenentatge");
        llistaTipusContracte.add("Substitució");
        llistaTipusContracte.add("Relleu");
        llistaTipusContracte.add("Pràctiques");

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * Mètode provisional que afegeig una llista de candidatures
     *
     * @author Daniel Sevilla i Junyent
     * @return Una llista d'objectes candidatura que farem servir de prova
     */
    private List<Candidatura> getListOfCandidatures() {

        List<Candidatura> cands = new ArrayList<>();
        // Ordre dels paràmetres al constructor: codiCandidatura, codiCandidat, codiOferta, estat
        cands.add(new Candidatura(1, 1, 1, 2));
        cands.add(new Candidatura(2, 2, 2, 2));
        cands.add(new Candidatura(3, 3, 3, 1));
        cands.add(new Candidatura(4, 4, 4, 0));
        return cands;
    }

    //
    // Controllers dels espais dls 3 usuaris del sistema,candidat, empresa i administrador
    //
    /**
     *
     * Prepara i retorna la vista que mostra les ofertes disponibles per
     * l'usuari candidat que està loguejat
     *
     * @author Daniel Sevilla i Junyent
     * @param request La petició http
     * @param response La resposta http
     * @return Objecte modelandview que representa la vista que mostra les
     * ofertes adients per un candidat en concret
     * @throws ServletException Indica que hi ha alguna errada general al
     * servlet
     * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
     */
    @RequestMapping(value = "/espaiCandidat", method = RequestMethod.GET)
    public ModelAndView espaiCandidat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView modelview = new ModelAndView("espaiCandidat");
        List<Oferta> ofertes = new ArrayList<Oferta>();

        // PER ACABAR QUAN TINGUEM EL GETCODIBY EMAIL DE L'EMPRESA OK
        try {
            ofertes = ofertaService.getAllOfertes();
        } catch (Exception e) {
            System.out.println("--- Error en rebre les ofertes");
        }

        // Desem a la variable 'username' el nom de l'usuari que s'ha acreditat
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // A partir del nom d'usuari/email hem d'agafar el codi d'usuari de la bbdd, mitjançant el mètode del servei corresponent, per passar-lo com a paràmetre a les opcions que ho requereixin
        Integer codiCandidat;

        try {
            // Provem de recuperar el codi del candidat loguejat per afegir-lo a les urls del menú de navegació
            System.out.println("--- Correu de l'usuari loguejat: " + username);
            codiCandidat = candidatService.getCodiByEmail(username);
            System.out.println("--- Codi de l'usuari loguejat: " + codiCandidat);
        } catch (Exception e) {
            modelview.setViewName("home");
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_logout};
            // Llista que conté els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();
            feedback.add(fb_problemaBBDD);

            modelview.getModelMap().addAttribute("ubicacio", baseline);
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
        }

        op_perfilCandidat.put("usuari", "/" + codiCandidat);
        op_candidatures.put("usuari", "/" + codiCandidat);
        op_baixaCandidat.put("usuari", "/" + codiCandidat + "/");

        // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
        HashMap[] opcions = new HashMap[]{op_perfilCandidat, op_candidatures, op_baixaCandidat, op_logout};

        modelview.getModelMap().addAttribute("ubicacio", "Ofertes escaients per les teves dades");
        modelview.getModelMap().addAttribute("ofertes", ofertes);
        modelview.getModelMap().addAttribute("opcions", opcions);

        return modelview;
    }

    /**
     *
     * @author Daniel Sevilla i Junyent
     * @param request La petició http
     * @param response La resposta http
     * @return Objecte modelandview que representa el model i la vista
     * @throws ServletException Indica que hi ha alguna errada general al
     * servlet
     * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
     */
    @RequestMapping(value = "/espaiEmpresa", method = RequestMethod.GET)
    public ModelAndView espaiEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("espaiEmpresa");

        // Obtenim el nom (e-mail) de l'usuari loguejat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nom = auth.getName();

        Integer codiEmpresa;

        try {
            codiEmpresa = empresaService.getCodiByEmail(nom);
        } catch (Exception e) {
            // Si hi ha hagut problema rebent el codi de la base de dades
            modelview.setViewName("home");
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_logout};
            // Llista que conté els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();
            feedback.add(fb_problemaBBDD);

            modelview.getModelMap().addAttribute("ubicacio", baseline);
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
        }

        // EL CORREU QUE HI HA A LES DADES DE L'EMPRESA HA DE SER EL MATEIX QUE FA SERVIR PEL LOGIN
        // Afegim l'usuari als maps de les opcions amb el parell amb clau "usuari", que completarà la url dels enllaços
        op_altaOferta.put("usuari", "/" + codiEmpresa + "/");
        op_ofertesEmpresa.put("usuari", "/" + codiEmpresa);
        op_perfilEmpresa.put("usuari", "/" + codiEmpresa);
        op_baixaEmpresa.put("usuari", "/" + codiEmpresa + "/");

        // Hashmap amb les opcions que hi haurà a la barra de navegació
        HashMap[] opcions = new HashMap[]{op_altaOferta, op_ofertesEmpresa, op_perfilEmpresa, op_baixaEmpresa, op_logout};

        modelview.getModelMap().addAttribute("opcions", opcions);

        return modelview;
    }

    /**
     *
     * @author Daniel Sevilla i Junyent
     * @param request La petició http
     * @param response La resposta http
     * @return Objecte modelandview que representa el model i la vista
     * @throws ServletException Indica que hi ha alguna errada general al
     * servlet
     * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView espaiAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("admin");

        try {
            Integer numCandidats = candidatService.getAllCandidats().size();// PENDENT DEL MÈTODE DEL REPOSITORI
            Integer numEmpreses = empresaService.getAllEmpreses().size();
            Integer numOfertes = ofertaService.getAllOfertes().size();
            modelview.getModelMap().addAttribute("numCandidats", numCandidats);
            modelview.getModelMap().addAttribute("numEmpreses", numEmpreses);
            modelview.getModelMap().addAttribute("numOfertes", numOfertes);
        } catch (Exception e) {
            // Llista que conté els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();
            feedback.add(fb_problemaBBDD);
            modelview.getModelMap().addAttribute("feedback", feedback);
        }

        // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
        HashMap[] opcions = new HashMap[]{op_iniciAdmin, op_candidats, op_empreses, op_ofertesAdmin, op_logout};
        modelview.getModelMap().addAttribute("opcions", opcions);

        return modelview;
    }

    /**
     *
     * Prepara i retorna la vista que mostra els detalls d'una oferta concreta,
     * el codi del qual rebem a la url com a PathVariable
     *
     * @author Daniel Sevilla i Junyent
     * @param codiOferta El codi de l'oferta el detall de la qual mostrarà la
     * vista
     * @param request La petició http
     * @param response La resposta http
     * @return Objecte modelabdview que representa el model i la vista que
     * mostrarà a l'usuari el detall d'una oferta concreta
     * @throws ServletException Indica que hi ha alguna errada general al
     * servlet
     * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
     */
    @RequestMapping(value = "/oferta/{codiOferta}", method = RequestMethod.GET)
    public ModelAndView ofertaPerCodi(@PathVariable("codiOferta") Integer codiOferta, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtenim el rol de l'usuari loguejat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().toString(); // El primer element dela collection auth.getAuthorities. Assumim que només conté un element.
        // Obtenim el nom de l'usuari loguejat
        String nom = auth.getName();

        ModelAndView modelview = new ModelAndView("oferta");

        // Llista que contindrà les opcions que hi haurà a la barra de navegació
        List<Map<String, String>> opcions = new ArrayList<>();

        // Llista que contindrà els missatges de feedback que passarem a la vista
        List<Map<String, String>> feedback = new ArrayList<>();

        Oferta oferta = new Oferta();
        System.out.println("Codi d'una oferta acabada de crear, sense contingut: " + oferta.getCodiOferta());

        // PER ACABAR
        try {
            oferta = ofertaService.getOfertaByCodi(codiOferta);
        } catch (Exception e) {
            feedback.add(fb_problemaBBDD);
        }

        // Segons el rol de l'usuari loguejat...
        switch (role) {
            case "ROLE_USER":
                // HI ha un usuari candidat loguejat
                try {
                    // Integer codiCandidat = candidatService.getCodiByEmail(nom);
                    Integer codiCandidat = 1; // DE PROVA
                    op_candidatures.put("usuari", "/" + codiCandidat);
                    op_ofertesCandidat.put("usuari", "/" + codiCandidat);
                    op_perfilCandidat.put("usuari", "/" + codiCandidat);
                    op_baixaCandidat.put("usuari", "/" + codiCandidat);
                    opcions.add(op_inici);
                    opcions.add(op_candidatures);
                    opcions.add(op_ofertesCandidat);
                    opcions.add(op_perfilCandidat);
                    opcions.add(op_baixaCandidat);
                    opcions.add(op_logout);
                    modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
                    modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
                    modelview.getModelMap().addAttribute("opcions", opcions);

                } catch (Exception e) {
                    // No hem pogut obtenir el codi de l'usuari
                    modelview.setViewName("home");
                    opcions.add(op_inici);
                    opcions.add(op_logout);

                    feedback.add(fb_problemaBBDD);

                    modelview.getModelMap().addAttribute("ubicacio", baseline);
                    modelview.getModelMap().addAttribute("feedback", feedback);
                    modelview.getModelMap().addAttribute("opcions", opcions);
                    modelview.getModelMap().addAttribute("ubicacio", baseline);
                    return modelview;
                }
                break;

            case "ROLE_EMPRESA":
                // Hi ha un usuari empresa empresa loguejat
                try {
                    Integer codiEmpresa = empresaService.getCodiByEmail(nom);
                    op_altaOferta.put("usuari", "/" + codiEmpresa + "/");
                    op_ofertesEmpresa.put("usuari", "/" + codiEmpresa);
                    op_perfilEmpresa.put("usuari", "/" + codiEmpresa);
                    op_baixaEmpresa.put("usuari", "/" + codiEmpresa + "/");
                    opcions.add(op_altaOferta);
                    opcions.add(op_ofertesEmpresa);
                    opcions.add(op_perfilEmpresa);
                    opcions.add(op_baixaEmpresa);
                    opcions.add(op_logout);
                    modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
                    modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
                    modelview.getModelMap().addAttribute("opcions", opcions);

                } catch (Exception e) {
                    // No hem pogut obtenir el codi de l'empresa
                    modelview.setViewName("home");
                    opcions.add(op_inici);
                    opcions.add(op_logout);

                    feedback.add(fb_problemaBBDD);

                    modelview.getModelMap().addAttribute("ubicacio", baseline);
                    modelview.getModelMap().addAttribute("feedback", feedback);
                    modelview.getModelMap().addAttribute("opcions", opcions);
                    modelview.getModelMap().addAttribute("ubicacio", baseline);
                    return modelview;
                }
                break;

        } // del switch

        // Afegeixo els atributs per passar a la vista
        modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
        modelview.getModelMap().addAttribute("feedback", feedback);
        modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
        modelview.getModelMap().addAttribute("opcions", opcions);

        return modelview;
    }

    /**
     *
     * Prepara i retorna la vista que mostra els detalls d'una oferta concreta a
     * administrador, el codi de la qual rebem a la url com a PathVariable
     * Reutilitza la vista oferta
     *
     * @author Daniel Sevilla i Junyent
     * @param codiOferta El codi de l'oferta els detalls de la qual mostrarà la
     * vista
     * @param request La petició http
     * @param response La resposta http
     * @return Objecte modelabdview que representa el model i la vista que
     * mostrarà a l'usuari el detall d'una oferta concreta
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/ofertaAdmin/{codiOferta}", method = RequestMethod.GET)
    public ModelAndView ofertaAdminPerCodi(@PathVariable("codiOferta") Integer codiOferta, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView modelview = new ModelAndView("oferta");

        // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
        HashMap[] opcions = new HashMap[]{op_iniciAdmin, op_candidats, op_empreses, op_ofertesAdmin, op_logout};

        // Llista que conté els missatges de feedback que passarem a la vista
        List<Map<String, String>> feedback = new ArrayList<>();

        // Llista dels possibles estats de les ofertes
        LlistaEstatsOfertes estatsPossiblesOfertes = new LlistaEstatsOfertes();

        Oferta oferta = new Oferta();

        try {
            oferta = ofertaService.getOfertaByCodi(codiOferta);
            System.out.println("--- Codi de l'empresa que ha creat l'oferta de l'objecte que li passem a la vista: " + oferta.getCodiEmpresa());
            System.out.println("--- Títol de l'oferta de l'objecte que li passem a la vista: " + oferta.getTitolOferta());
            System.out.println("----------------- ");
        } catch (Exception e) {
            feedback.add(fb_problemaBBDD);
            modelview.getModelMap().addAttribute("feedback", feedback);
        }

        modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
        modelview.getModelMap().addAttribute("candidatures", llistaCandidatures);
        modelview.getModelMap().addAttribute("oferta", oferta);
        modelview.getModelMap().addAttribute("estatsPossiblesOfertes", estatsPossiblesOfertes);
        modelview.getModelMap().addAttribute("opcions", opcions);

        return modelview;
    }

    /**
     *
     * Gestiona l'acció que executa el botó de desar nou estat de l'oferta Només
     * pot canviar l'estat de l'oferta l'admin
     *
     * @param codiOferta El codi de l'oferta
     * @param formOferta L'objecte de tipus Oferta que rebem del formulari de la
     * vista
     * @param request La petició http
     * @param response La resposta http
     * @return Un objecte ModelAndView que fa referència a la següent vista que
     * mostrarem a l'usuari
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/ofertaAdmin/{codiOferta}/desaEstat", method = RequestMethod.POST)
    public ModelAndView desaEstatOferta(@PathVariable("codiOferta") Integer codiOferta, @ModelAttribute("oferta") Oferta formOferta, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");

        // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
        HashMap[] opcions = new HashMap[]{op_iniciAdmin, op_candidats, op_empreses, op_logout};

        // Llista que conté els missatges de feedback que passarem a la vista
        List<Map<String, String>> feedback = new ArrayList<>();

        // Llista d'ofertes
        List<Oferta> ofertes = new ArrayList<>();

        // NO ENS ESTÀ ARRIBANT BÉ L'OBJECTE DEL FORMULARI DE LA VISTA
        System.out.println("--- Codi de l'oferta: " + formOferta.getCodiOferta());
        System.out.println("--- Estat de l'oferta: " + formOferta.getEstat());
        System.out.println("--- Codi de l'empresa que ha creat l'oferta: " + formOferta.getCodiEmpresa());
        System.out.println("--- Títol de l'oferta: " + formOferta.getTitolOferta());
        System.out.println("--- Descripció de l'oferta: " + formOferta.getDescripcio());

        try {
            // Provem d'actualitzar la base de dades amb l'objecte Oferta rebut del formulari
            // Estaria bé poder saber si el canvi s'ha executat correctament, però ara per ara el mètode retorna void
            ofertaService.updateOferta(formOferta);
            feedback.add(fb_canvisOK);
        } catch (Exception e) {
            // Si no ha estat possible
            feedback.add(fb_canvisKO);
        }

        try {
            // Provem de recuperar de la bbdd el llistat de totes les ofertes
            ofertes = ofertaService.getAllOfertes();
        } catch (Exception e) {
            feedback.add(fb_problemaBBDD);
        }

        modelview.getModelMap().addAttribute("ubicacio", "Ofertes donades d'alta a l'aplicació");
        modelview.getModelMap().addAttribute("feedback", feedback);
        modelview.getModelMap().addAttribute("ofertes", ofertes);
        modelview.getModelMap().addAttribute("opcions", opcions);

        return modelview;
    }

    /**
     *
     * Prepara i retorna la vista que mostra els detalls d'una oferta concreta,
     * el codi del qual rebem a la url com a PathVariable En aquest cas passarem
     * a la vista el referer perque pugui incloure un botó 'tornar' funcional
     *
     * @author Daniel Sevilla i Junyent
     * @param codiOferta El codi de l'oferta lea dades de la qual mostrarem
     * @param request La petició http
     * @param response La resposta http
     * @return objecte modelandview que representa el model i la vista oferta
     * només amb el botó 'Tornar' al cos
     * @throws ServletException Indica que hi ha alguna errada general al
     * servlet
     * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
     */
    @RequestMapping(value = "/ofertaTornar/{codiOferta}", method = RequestMethod.GET)
    public ModelAndView ofertaPerCodiTornar(@PathVariable("codiOferta") Integer codiOferta, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Controlador que invoca la vista perque aquesta mostri només el botó 'Tornar'
        // Pensat per quan el candidat vulgui consultar l'oferta des del llistat de candidatures
        // Passem el referer a la vista com a atribut
        // Obtenim el rol de l'usuari loguejat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().toString(); // El primer element dela collection auth.getAuthorities. Assumim que només conté un element.
        // Obtenim el nom de l'usuari loguejat
        String nom = auth.getName();

        ModelAndView modelview = new ModelAndView("oferta");
        // Oferta of = toWorkService.getOfertaByRef(ref); // En aquesta linia invocarem el mètode del servei per recuperar l'objecte oferta que després passarem a la vista

        // Llista que contindrà les opcions que hi haurà a la barra de navegació
        List<Map<String, String>> opcions = new ArrayList<>();

        switch (role) {
            // L'usuari loguejat és un candidat
            case "ROLE_USER": {
                // provem de recuperar el codi del candidat
                try {
                    // codiCandidat = candidatService.getCodiByEmail(nom);
                    Integer codiCandidat = 1; // Per poder provar, mentre no tinguem el mètode corresponent
                    // Afegim el codi a les opcions que el necessiten
                    op_perfilCandidat.put("usuari", "/" + codiCandidat);
                    op_ofertesCandidat.put("usuari", "/" + codiCandidat);
                    op_candidatures.put("usuari", "/" + codiCandidat);
                    op_perfilCandidat.put("usuari", "/" + codiCandidat);
                    op_baixaCandidat.put("usuari", "/" + codiCandidat);
                    // Afegim les opcions a la llista d'opcions per la barra de navegació
                    opcions.add(op_inici);
                    opcions.add(op_ofertesCandidat);
                    opcions.add(op_candidatures);
                    opcions.add(op_perfilCandidat);
                    opcions.add(op_baixaCandidat);
                    opcions.add(op_logout);
                } catch (Exception e) {
                    // SI no hem pogut recuperar el codi
                    modelview.setViewName("home");
                    opcions.add(op_logout);

                    // Llista que conté els missatges de feedback que passarem a la vista
                    List<Map<String, String>> feedback = new ArrayList<>();
                    feedback.add(fb_problemaBBDD);

                    modelview.getModelMap().addAttribute("ubicacio", baseline);
                    modelview.getModelMap().addAttribute("feedback", feedback);
                    modelview.getModelMap().addAttribute("opcions", opcions);
                    return modelview;
                }
                break;
            }
            // L'usuari loguejat és una empresa
            case "ROLE_EMPRESA": {
                // provem de recuperar el codi de l'empresa
                try {
                    Integer codiEmpresa = empresaService.getCodiByEmail(nom);
                    // Afegim el codi a les opcions que el necessiten
                    op_ofertesEmpresa.put("usuari", "/" + codiEmpresa);
                    op_perfilEmpresa.put("usuari", "/" + codiEmpresa);
                    op_baixaEmpresa.put("usuari", "/" + codiEmpresa + "/");
                    // Afegim les opcions a la llista d'opcions per la barra de navegació
                    opcions.add(op_inici);
                    opcions.add(op_ofertesEmpresa);
                    opcions.add(op_perfilEmpresa);
                    opcions.add(op_baixaEmpresa);
                    opcions.add(op_logout);
                } catch (Exception e) {
                    // SI no hem pogut recuperar el codi
                    modelview.setViewName("home");
                    opcions.add(op_logout);

                    // Llista que conté els missatges de feedback que passarem a la vista
                    List<Map<String, String>> feedback = new ArrayList<>();
                    feedback.add(fb_problemaBBDD);

                    modelview.getModelMap().addAttribute("ubicacio", baseline);
                    modelview.getModelMap().addAttribute("feedback", feedback);
                    modelview.getModelMap().addAttribute("opcions", opcions);
                    return modelview;
                }
                break;
            }
            case "ROLE_ADMIN": {
                opcions.add(op_iniciAdmin);
                opcions.add(op_candidats);
                opcions.add(op_empreses);
                opcions.add(op_ofertesAdmin);
                opcions.add(op_logout);
                break;
            }
        }

        // Afegeixo els atributs per passar a la vista
        modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
        modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
        modelview.getModelMap().addAttribute("opcions", opcions);
        modelview.getModelMap().addAttribute("referer", request.getHeader("Referer"));

        return modelview;
    }

    /**
     *
     * Prepara i retorna la vista que mostra els detalls d'una oferta a l'usuari
     * empresa (propietari) de l'oferta (comparteix vista amb altres tipus
     * d'usuari però en aquest cas mostra més contingut Només hi accedeixen
     * usuaris amb rol empresa
     *
     * @author Daniel Sevilla i junyent
     * @param codiOferta El codi de l'oferta (un Integer) les dades de la qual
     * mostrarem al propietari d'aquesta
     * @param request La petició http
     * @param response La resposta http
     * @return Un objecte modelandview que representa el model i la vista que
     * mostrarem a l'usuari
     * @throws ServletException Indica que hi ha alguna errada general al
     * servlet
     * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
     */
    @RequestMapping(value = "/ofertaPropietari/{codiOferta}", method = RequestMethod.GET)
    public ModelAndView ofertaPropietariPerCodi(@PathVariable("codiOferta") Integer codiOferta, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtenim el nom de l'usuari loguejat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nom = auth.getName();

        ModelAndView modelview = new ModelAndView("oferta");
        // Oferta of = toWorkService.getOfertaByRef(ref); // En aquesta linia invocarem el mètode del servei per recuperar l'objecte oferta que després passarem a la vista

        // Llista que contindrà les opcions que hi haurà a la barra de navegació
        List<Map<String, String>> opcions = new ArrayList<>();

        // Llista que conté els missatges de feedback que passarem a la vista
        List<Map<String, String>> feedback = new ArrayList<>();

        Oferta oferta = new Oferta();
        Integer codiEmpresa;

        // Tractem d'obtenir de la base de dades l'oferta 
        try {
            oferta = ofertaService.getOfertaByCodi(codiOferta);
        } catch (Exception e) {
            feedback.add(fb_problemaBBDD);
        }

        if (oferta.getCodiOferta() != null) {
            // Hem obtingut l'oferta
            // Tractem d'obtenir el codi de l'empresa loguejada per afegir-lo a les urls de les opcions de la barra de navegació

            try {
                codiEmpresa = empresaService.getCodiByEmail(nom);
            } catch (Exception e) {
                // Si no hem pogut obtenir el codi de l'empresa
                modelview.setViewName("home");
                opcions.add(op_inici);
                opcions.add(op_logout);

                feedback.add(fb_problemaBBDD);

                modelview.getModelMap().addAttribute("ubicacio", baseline);
                modelview.getModelMap().addAttribute("feedback", feedback);
                modelview.getModelMap().addAttribute("opcions", opcions);
                return modelview;
            }
        } else {
            // No hem obtingut l'oferta
            modelview.setViewName("home");
            opcions.add(op_inici);
            opcions.add(op_logout);
            feedback.add(fb_problemaBBDD);
            modelview.getModelMap().addAttribute("ubicacio", baseline);
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
        }

        // Arribem a aquest punt si tenim l'oferta i el codi de l'empresa
        // Afegim el codi de l'empresa a les urls de les opcions a la barra de navegació
        op_altaOferta.put("usuari", "/" + codiEmpresa + "/");
        op_ofertesEmpresa.put("usuari", "/" + codiEmpresa);
        op_perfilEmpresa.put("usuari", "/" + codiEmpresa);
        // Omplir l'array d'opcions per la barra de navegació
        opcions.add(op_altaOferta);
        opcions.add(op_ofertesEmpresa);
        opcions.add(op_perfilEmpresa);
        opcions.add(op_logout);

        // LES CANDIDATURES ENCARA SON DE PROVA FINS QUE ESTIGUI IMPLEMENTADA AQUESTA PART DEL REPOSITORI/SERVEI
        Candidatura formCandidatura = new Candidatura();

        // Afegeixo els atributs per passar a la vista
        modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
        modelview.getModelMap().addAttribute("oferta", oferta);
        modelview.getModelMap().addAttribute("opcions", opcions);

        modelview.getModelMap().addAttribute("estatsPossiblesCandidatura", estatsPossibles);
        modelview.getModelMap().addAttribute("candidatures", llistaCandidatures);
        modelview.getModelMap().addAttribute("formCandidatura", formCandidatura);

        modelview.getModelMap().addAttribute("propietari", true); // Indica a la vista que hem de mostrar la informació que només és pel propietari de l'oferta

        return modelview;

    }

    /**
     *
     * Prepara i retorna la vista d'ofertes que veu l'administrador
     *
     * @author Daniel Sevilla i Junyent
     * @param request La petició http
     * @param response La resposta http
     * @return Un objecte modelandview que representa el model i la vista que
     * mostrarem a l'usuari
     * @throws ServletException Indica que hi ha alguna errada general al
     * servlet
     * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
     */
    @RequestMapping(value = "/ofertesAdmin", method = RequestMethod.GET)
    public ModelAndView ofertesAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");

        List<Oferta> ofertes = new ArrayList<Oferta>();

        // Llista que conté els missatges de feedback que passarem a la vista
        List<Map<String, String>> feedback = new ArrayList<>();

        try {
            ofertes = ofertaService.getAllOfertes();
        } catch (Exception e) {
            // No hem pogut recuperar totes les ofertes generades per l'empresa de la bbdd
            feedback.add(fb_problemaBBDD);
        }

        // Llista que contindrà les opcions que hi haurà a la barra de navegació
        List<Map<String, String>> opcions = new ArrayList<>();

        opcions.add(op_iniciAdmin);
        opcions.add(op_candidats);
        opcions.add(op_empreses);
        opcions.add(op_logout);

        modelview.getModelMap().addAttribute("ubicacio", "Ofertes donades d'alta a l'aplicació");
        modelview.getModelMap().addAttribute("feedback", feedback);
        modelview.getModelMap().addAttribute("ofertes", ofertes);
        modelview.getModelMap().addAttribute("opcions", opcions);

        return modelview;
    }

    /**
     *
     * Prepara i retorna la vista d'ofertes, filtrades per l'empresa que les ha
     * creat, a l'administrador
     *
     * @author Daniel Sevilla i Junyent
     * @param codiEmpresa Un integer que representa el codi de l'empresa les
     * ofertes creades per la qual mostrarem a l'administrador
     * @param request La petició http
     * @param response La respsta http
     * @return Un objecte modelandview que representa el model i la vista que
     * mostrarem a l'usuari
     * @throws ServletException Indica que hi ha alguna errada general al
     * servlet
     * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
     */
    @RequestMapping(value = "/ofertesAdmin/{codiEmpresa}", method = RequestMethod.GET)
    public ModelAndView ofertesAdminPerEmpresa(@PathVariable("codiEmpresa") Integer codiEmpresa, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");

        // Llista que contindrà les opcions que hi haurà a la barra de navegació
        List<Map<String, String>> opcions = new ArrayList<>();
        opcions.add(op_iniciAdmin);
        opcions.add(op_candidats);
        opcions.add(op_empreses);
        opcions.add(op_ofertesAdmin);
        opcions.add(op_logout);

        // Llista que contindrà els missatges de feedback que passarem a la vista
        List<Map<String, String>> feedback = new ArrayList<>();

        List<Oferta> ofertes = new ArrayList<Oferta>();

        try {
            ofertes = ofertaService.getOfertaByCodiEmpresa(codiEmpresa);
        } catch (Exception e) {
            modelview.setViewName("home");
            feedback.add(fb_problemaBBDD);
            modelview.getModelMap().addAttribute("ubicacio", baseline);
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
        }

        modelview.getModelMap().addAttribute("ubicacio", "Vista d'ofertes per empresa");
        modelview.getModelMap().addAttribute("ofertes", ofertes);
        modelview.getModelMap().addAttribute("feedback", feedback);
        modelview.getModelMap().addAttribute("opcions", opcions);

        return modelview;
    }

    /**
     *
     * Prepara i retorna la vista d'actualització d'empresa
     *
     * @author Daniel Sevilla i Junyent
     * @param codiEmpresa
     * @param request La petició httlp
     * @param response La resposta http
     * @return Un objecte modelandview que representa el model i la vista que
     * mostrarem a l'usuari, amb el formulari d'actualització d'empresa
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/perfilEmpresa/{codiEmpresa}", method = RequestMethod.GET)
    public ModelAndView modifEmpresa(@PathVariable("codiEmpresa") Integer codiEmpresa, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView modelview = new ModelAndView("perfilEmpresa");
        Empresa empresa;

        try {
            empresa = empresaService.getEmpresaByCodi(codiEmpresa);
        } catch (Exception e) {
            empresa = null;
        }

        if (empresa == null) {
            // Si no hem pogut obtenir l'empresa de la bbdd
            modelview.setViewName("home");
            List<Map<String, String>> opcions = new ArrayList<>();

            // Afegim l'usuari als maps de les opcions amb el parell amb clau "usuari", que completarà la url dels enllaços
            op_perfilEmpresa.put("usuari", "/" + codiEmpresa);
            op_ofertesEmpresa.put("usuari", "/" + codiEmpresa);
            op_baixaEmpresa.put("usuari", "/" + codiEmpresa + "/");

            opcions.add(op_ofertesEmpresa);
            opcions.add(op_perfilEmpresa);
            opcions.add(op_baixaEmpresa);
            opcions.add(op_logout);

            // Llista que conté els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();
            feedback.add(fb_problemaBBDD);

            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        // Si hem pogut obtenir correctament l'empresa de la base de dades continuem aqui
        // Formulari d'alta empresa
        Empresa formPerfilEmpresa = new Empresa();
        // Llistat de sectors
        LlistaSectors llistaSectors = new LlistaSectors();

        // Afegim l'usuari al map amb el parell amb clau "usuari", que completarà la url de l'enllaç
        op_ofertesEmpresa.put("usuari", "/" + codiEmpresa);
        op_baixaEmpresa.put("usuari", "/" + codiEmpresa + "/");

        // Llista que contindrà les opcions que hi haurà a la barra de navegació
        HashMap[] opcions = new HashMap[]{op_inici, op_ofertesEmpresa, op_baixaEmpresa, op_logout};

        modelview.getModelMap().addAttribute("formEmpresa", formPerfilEmpresa);
        modelview.getModelMap().addAttribute("ubicacio", "Editar dades associades al perfil");
        modelview.getModelMap().addAttribute("empresa", empresa);
        modelview.getModelMap().addAttribute("llistaSectors", llistaSectors);
        modelview.getModelMap().addAttribute("opcions", opcions);

        return modelview;
    }

    /**
     *
     * Mètode que invoca l'enviament del formulari d'actualització d'empresa
     * Prova d'executar el mètode que actualitza l'empresa a la base de dades
     * Retorna la vista home amb feedback sobre el resultat
     *
     * @author Daniel Sevilla i Junyent
     * @param formEmpresa L'objecte de tipus Empresa que el mètode rep del
     * formulari
     * @return Un objecte modelandview que representa el model i la vista que
     * mostrarem a l'usuari un cop provada d'executar l'actualització a la base
     * de dades
     */
    @RequestMapping(value = "/perfilEmpresa/actualitza", method = RequestMethod.POST)
    public ModelAndView executaModifEmpresa(@ModelAttribute("formEmpresa") Empresa formEmpresa) {

        // Obtenim el nom (e-mail) de l'usuari loguejat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nom = auth.getName();

        ModelAndView modelview = new ModelAndView("home");

        // Llista que conté els missatges de feedback que passarem a la vista
        List<Map<String, String>> feedback = new ArrayList<>();

        try {
            // Tractem de fer l'actualització a la base de dades
            empresaService.updateEmpresa(formEmpresa);
            // Si funciona informem a l'usuari
            feedback.add(fb_canvisOK);
        } catch (Exception e) {
            feedback.add(fb_canvisKO);
        }

        // Hem de recuperar el codi de l'empresa perque amb l'actualització aquest codi ha variat
        Integer codiEmpresa;
        try {
            // Tractem de recuperar el codi de l'empresa a partir del nom de l'usuari loguejat
            codiEmpresa = empresaService.getCodiByEmail(nom);
            op_altaOferta.put("usuari", "/" + codiEmpresa + "/");
            op_ofertesEmpresa.put("usuari", "/" + codiEmpresa);
            op_perfilEmpresa.put("usuari", "/" + codiEmpresa);
        } catch (Exception e) {
            // Si falla la recuperació del nom a la base de dades
            // Retornarem la mateixa vista però sense les opcions que necessiten que la url inclogui el codi de l'empresa
            HashMap[] opcions = new HashMap[]{op_inici, op_logout};
            feedback.add(fb_problemaBBDD);
            modelview.getModelMap().addAttribute("ubicacio", baseline);
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
        }

        HashMap[] opcions = new HashMap[]{op_altaOferta, op_ofertesEmpresa, op_perfilEmpresa, op_logout};
        modelview.getModelMap().addAttribute("ubicacio", baseline);
        modelview.getModelMap().addAttribute("feedback", feedback);
        modelview.getModelMap().addAttribute("opcions", opcions);

        return modelview;
    }

    /**
     *
     *
     * @author Daniel Sevilla i Junyent
     * @param codiCandidat El codi del candidat que rebem com a PathVariable a
     * la url
     * @param request La petició http
     * @param response La resposta http
     * @return Un objecte ModelAndView amb la vista, que contindrà el formulari
     * per modificar el candidat
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/perfilCandidat/{codiCandidat}", method = RequestMethod.GET)
    public ModelAndView modifCandidat(@PathVariable("codiCandidat") Integer codiCandidat, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView modelview = new ModelAndView("perfilCandidat");
        Candidat candidat;

        try {
            candidat = candidatService.getCandidatByCodi(codiCandidat);
        } catch (Exception e) {
            candidat = null;
        }
        if (candidat == null) {
            // Si no hem pogut obtenir el candidat de la bbdd
            modelview.setViewName("home");
            List<Map<String, String>> opcions = new ArrayList<>();

            op_perfilCandidat.put("usuari", "/" + codiCandidat);
            op_ofertesCandidat.put("usuari", "/" + codiCandidat);
            op_baixaCandidat.put("usuari", "/" + codiCandidat);

            // Llista que conté els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();
            feedback.add(fb_problemaBBDD);

            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }
        
         // Formulari d'alta empresa
            Candidat formPerfilCandidat = new Candidat();
            // Llistat de sectors
            LlistaSectors llistaSectors = new LlistaSectors();
            
            // Afegim l'usuari al map amb el parell amb clau "usuari", que completarà la url de l'enllaç
            op_ofertesCandidat.put("usuari","/"+codiCandidat);
            op_baixaCandidat.put("usuari","/"+codiCandidat+"/");
            
            // Llista que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici, op_ofertesCandidat, op_baixaCandidat, op_logout};
            
            modelview.getModelMap().addAttribute("formCandidat", formPerfilCandidat);
            modelview.getModelMap().addAttribute("ubicacio", "Editar dades associades al perfil");
            modelview.getModelMap().addAttribute("candidat", candidat);
            modelview.getModelMap().addAttribute("llistaSectors", llistaSectors);
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
    

        // Creo un candidat DE PROVA per passar a la vista.
        // En realitat l'haurem de treure de la bbdd
        /* LocalDate data_naix = LocalDate.now();
            CandidatFormulari candidat = new CandidatFormulari (1,"Ramonet","Rocaguinarda", "39363936M", data_naix, "Carrer del bar, 23", "Gironella", "Barcelona","938698122", "ramon@emiliu.cat", "Aquestes son les observacions", "contrassenya", "contrassenya",2,3);
            System.out.println("--- Data de naixement amb que inicialitzem el candidat: "+data_naix);
            ArrayList<Integer> habilitatsProva = new ArrayList();
            habilitatsProva.add(3); // Ha de correspondre amb l'ocupació que li hem posat al candidat de prova
            habilitatsProva.add(15); // Ha de correspondre amb l'ocupació que li hem posat al candidat de prova
            candidat.setHabilitats(habilitatsProva);
            
            Experiencia ex1 = new Experiencia(1,5, "Empresa Inc.", 1, "Vaig treballar molt");
            Experiencia ex2 = new Experiencia(2,3, "Empresot Corp.", 1, "No vaig treballar gaire");
            List<Integer> experiencies = new ArrayList();
            experiencies.add(ex1.getCodiExperiencia());
            experiencies.add(ex2.getCodiExperiencia());
            // No podem passar a la banda del client els objectes
            // Li passarem a la vista una llista de codis i allà recuperarem les dades mitjançant AJAX per omplir el formulari amb les dades 
            
            
            // Formulari de modificació de candidat
            CandidatFormulari formCandidat = new CandidatFormulari();
            // Llistat d'ocupacions
            LlistaOcupacions llistaOcupacions = new LlistaOcupacions();
            // Llistat de formacions
            LlistaFormacions llistaFormacions = new LlistaFormacions();
            // Llistat d'habilitats
            LlistaHabilitats llistaHabilitats = new LlistaHabilitats();
            
            op_ofertesCandidat.put("usuari","/"+codiCandidat);
            op_candidatures.put("usuari","/"+codiCandidat);
            
            // Genero les opcions de la barra de navegació per passar a la vista
            HashMap[] opcions = new HashMap[]{op_inici, op_ofertesCandidat, op_candidatures, op_logout};
            
            
            
            modelview.getModelMap().addAttribute("formCandidat", formCandidat);
            modelview.getModelMap().addAttribute("ubicacio", "Editar dades associades al perfil");
            modelview.getModelMap().addAttribute("candidat", candidat);
            modelview.getModelMap().addAttribute("experiencies", experiencies);
            modelview.getModelMap().addAttribute("llistaOcupacions", llistaOcupacions);
            modelview.getModelMap().addAttribute("llistaFormacions", llistaFormacions);
            modelview.getModelMap().addAttribute("llistaHabilitats", llistaHabilitats);
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
         */
        /**
         *
         * S'executa en fer submit del formulari de modificar dades del candidat
         * Ha de rebre un objecte de tipus Candidat i invocar el mètode del
         * servei per executar l'update Ha de retornar feedback a l'usuari del
         * resultat Utilitza la classe CandidatFormulari, que és com Candidat
         * però adaptat a les necessitats del formulari
         *
         * @author Daniel Sevilla i Junyent
         * @param formCandidat L'objecte de tipus Candidat que rebem del
         * formulari per POST
         * @return Un objecte modelandview que representa el model i la vista
         * que mostrarem a l'usuari un cop provada d'executar l'actualització a
         * la base de dades
         */
        @RequestMapping(value = "/perfilCandidat/{codiCandidat}/actualitza", method = RequestMethod.POST)
        public ModelAndView executaModifCandidat
        (@PathVariable("codiCandidat")
        int codiCandidat, 
        @ModelAttribute("formCandidat") CandidatFormulari formCandidat
        
            ) {

        // proves que podrem carregar-nos...
        //
        System.out.println("--- Dades rebudes del formulari ---");
            System.out.println("--- Nom complet: " + formCandidat.getNom() + " " + formCandidat.getCognoms());
            System.out.println("--- Data de naixement: " + formCandidat.getDataNaix());
            System.out.println("--- Habilitats: " + formCandidat.getHabilitats());
            for ( Experiencia item : formCandidat.getExperiencies()) {
                System.out.println("--- Experiencia  ---");
                System.out.println("--- Anys: " + item.getAnys());
                System.out.println("--- Empresa: " + item.getNomEmpresa());
                System.out.println("--- Descripció: " + item.getDescripcio());
            }
            //
            // ...fins aqui.

            // Obtenim el nom (e-mail) de l'usuari loguejat
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String nom = auth.getName();

            // Llista que conté els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            // Llista que conté les opcions que passarem a la vista per la barra de navegació
            List<Map<String, String>> opcions = new ArrayList<>();

            try {
                // Tractem de recuperar el codi del candidat a partir del nom de l'usuari loguejat

                // Afegim el codi d'usuari a les urls de les opcions
                op_ofertesCandidat.put("usuari", "/" + codiCandidat);
                op_candidatures.put("usuari", "/" + codiCandidat);
                op_perfilCandidat.put("usuari", "/" + codiCandidat);
                op_baixaCandidat.put("usuari", "/" + codiCandidat);
                opcions.add(op_ofertesCandidat);
                opcions.add(op_candidatures);
                opcions.add(op_perfilCandidat);
                opcions.add(op_baixaCandidat);
                opcions.add(op_logout);
            } catch (Exception e) {
                // Si no hem pogut recuperar el codi de l'usuari no podem muntar les urls de les opcions de la barra de navegació
                opcions.add(op_logout);
            }

            ModelAndView modelview = new ModelAndView("home");

            try {
                // PENDENT DE PROVAR
                // candidatService.updateCandidat(aCandidat(formCandidat)); // Abans de passar-lo el converteixo a objecte de la classe Candidat
                candidatService.updateCandidat(codiCandidat, aCandidat(formCandidat));
                feedback.add(fb_canvisOK);
            } catch (Exception e) {
                // No s'ha pogut fer l'update
                feedback.add(fb_canvisKO);
            }

            modelview.getModelMap().addAttribute("ubicacio", baseline);
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        /**
         *
         * Prepara i retorna la vista d'ofertes a l'empresa que les ha creat
         *
         * @author Daniel Sevilla i Junyent
         * @param codiEmpresa El codi de l'empresa les ofertes creades per la
         * qual mostrarem a l'usuari empresa
         * @param request La petició http
         * @param response La resposta http
         * @return OUn objecte modelandview que representa el model i la vista
         * que mostrarem a l'usuari
         * @throws ServletException Indica que hi ha alguna errada general al
         * servlet
         * @throws IOException Indica que s'ha produït algun error
         * d'entrada/sortida
         */
        @RequestMapping(value = "/ofertesEmpresa/{codiEmpresa}", method = RequestMethod.GET)
        public ModelAndView ofertesEmpresa
        (@PathVariable("codiEmpresa")
        int codiEmpresa, HttpServletRequest request
        , HttpServletResponse response) throws ServletException, IOException {

            ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");

            // Llista que contindrà les opcions que hi haurà a la barra de navegació
            List<Map<String, String>> opcions = new ArrayList<>();

            // Llista que conté els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            List<Oferta> ofertes = new ArrayList<>();

            try {
                // Provem de recuperar de la base de dades les ofertes generades per l'empresa
                ofertes = ofertaService.getOfertaByCodiEmpresa(codiEmpresa);
            } catch (Exception e) {
                // Si no hem pogut recuperar les dades
                feedback.add(fb_problemaBBDD);
            }

            // Afegim l'usuari als maps de les opcions amb el parell amb clau "usuari", que completarà la url dels enllaços
            op_altaOferta.put("usuari", "/" + codiEmpresa + "/");
            op_perfilEmpresa.put("usuari", "/" + codiEmpresa);

            opcions.add(op_inici);
            opcions.add(op_altaOferta);
            opcions.add(op_perfilEmpresa);
            opcions.add(op_logout);

            modelview.getModelMap().addAttribute("ubicacio", "Ofertes generades");
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("ofertes", ofertes);
            modelview.getModelMap().addAttribute("codiEmpresa", codiEmpresa);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        /**
         *
         * Prepara i retorna la vista que mostra les dades del candidat que es
         * passa com a PathVariable
         *
         * @author Daniel Sevilla i Junyent
         * @param codiCandidat El codi del candidat les dades del qual mostrarà
         * la vista
         * @param request La petició http
         * @param response La resposta http
         * @return L'objecte modelandview que representa el model i la vista que
         * mostrarem a l'usuari
         * @throws ServletException Indica que hi ha alguna errada general al
         * servlet
         * @throws IOException Indica que s'ha produït algun error
         * d'entrada/sortida
         */
        @RequestMapping(value = "/candidat/{codiCandidat}", method = RequestMethod.GET)
        public ModelAndView candidatPerCodi
        (@PathVariable("codiCandidat")
        Integer codiCandidat, HttpServletRequest request
        , HttpServletResponse response) throws ServletException, IOException {

            // Passem a la vista les opcions de la barra de navegacó PER USUARI EMPRESA
            // Aquesta mateixa vista l'hauria de poder veure com a mínim l'admin, probablement amb altres opcions a la barra de navegació
            // Queda pendent veure en quin moment podem passar unes o altres opcions segons el tipus d'usuari, i implementar-ho
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula", "Perfil");
            perfil.put("url", "/perfil");

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil, op_ofertesEmpresa, op_logout};

            ///// Inici dels objectes de prova que genero per poder muntar la vista ////////////////////////////////////////////////////////////////////////////////////////////
            // Genero objecte Formacio de prova
            Formacio formacio = new Formacio();
            formacio.setNomFormacio("Nom de la formació1");
            formacio.setCodiFormacio(1);

            // Genero un objecte Candidat DE PROVA mentre no fem el getCandidatPerCodi, o com sigui
            // Només hi poso les dades que han de sortir a la vista
            Candidat cand = new Candidat();
            cand.setCodi(1);
            cand.setFormacio(1);
            cand.setEmail("e@mail.cat");
            cand.setTelefon("969996633");
            cand.setHabilitats(habs);

            ModelAndView modelview = new ModelAndView("candidat");
            modelview.getModelMap().addAttribute("ubicacio", "Detall del candidat");
            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("candidat", cand);
            modelview.getModelMap().addAttribute("referer", request.getHeader("Referer"));

            return modelview;
        }

        /**
         *
         * Prepara i retorna la vista que mostra totes les candidatures del
         * candidat el codi del qual passem com a PathVariable
         *
         * @author Daniel Sevilla i Junyent
         * @param codiCandidat El codi del qual la vista mostrarà les
         * candidatures
         * @param request La petició http
         * @param response La resposta http
         * @return Un objecte modelandview que representa el model i la vista
         * que mostrarem a l'usuari
         * @throws ServletException Indica que hi ha alguna errada general al
         * servlet
         * @throws IOException Indica que s'ha produït algun error
         * d'entrada/sortida
         */
        @RequestMapping(value = "/candidatures/{codiCandidat}", method = RequestMethod.GET)
        public ModelAndView candidaturesPerCandidat
        (@PathVariable("codiCandidat")
        Integer codiCandidat, HttpServletRequest request
        , HttpServletResponse response) throws ServletException, IOException {

            // Passem a la vista les opcions de la barra de navegacó PER USUARI EMPRESA
            // Aquesta mateixa vista l'hauria de poder veure com a mínim l'admin, probablement amb altres opcions a la barra de navegació
            // Queda pendent veure en quin moment podem passar unes o altres opcions segons el tipus d'usuari, i implementar-ho
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula", "Perfil");
            perfil.put("url", "/perfil");

            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertesCandidat = new HashMap<>();
            ofertesCandidat.put("paraula", "Ofertes");
            ofertesCandidat.put("url", "/espaiCandidat"); // AQUI LI HAUREM DE PASSAR A LA URL EL CODI DE CANDIDAT PERQUE ENS MOSTRI LA INFO ESCAIENTS

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil, ofertesCandidat, op_logout};

            // També li haurem de passar la LLISTA AMB LES CANDIDATURES, amb l'estat en que estiguin, si no passen integrades dins l'objecte Oferta
            // Omplo un arrayList amb les candidatures de prova per poder enviar-les al controlador
            // AL TANTO, HAUREM DE FER SERVIR UN MÈTODE QUE ENS PASSI EL TÍTOL DE L'OFERTA PASSANT-LI EL CODI
            // PQ A LA TAULA HAURIA DE SORTIR EL TÍTOL DE L'OFERTA I A L'OBJECTE CANDIDATURA HI HA EL CODI
            llistaCandidatures.setLlista(candidatures);

            ModelAndView modelview = new ModelAndView("candidatures");
            modelview.getModelMap().addAttribute("ubicacio", "Les meves candidatures");
            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("candidatures", candidatures);

            return modelview;
        }

        /**
         *
         * Prepara i retorna la vista que es mostrarà a l'usuari quan s'hagi
         * executat el logout
         *
         * @author Daniel Sevilla i Junyent
         * @param request La petició http
         * @return Un objecte modelandview que representa la vista que es
         * mostrarà a l'usuari
         */
        @RequestMapping(value = "/logout", method = RequestMethod.GET)
        public ModelAndView logout
        (HttpServletRequest request
        
            ) {

        // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
        HashMap[] opcions = new HashMap[]{op_entrarCandidat, op_entrarEmpresa};

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            feedback.add(fb_logoutOK);

            ModelAndView modelview = new ModelAndView("home");
            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("ubicacio", baseline);
            modelview.getModelMap().addAttribute("feedback", feedback);

            return modelview;
        }

        /**
         *
         * El mètode que s'invocarà quan el candidat premi l'opció 'inscriure'm
         * a l'oferta' Ha de cridar el mètode del servei que executi la
         * inscripció a la bbdd Retorna la vista d'ofertes seleccionades per
         * l'usuari, a la qual li passarem la nova llista d'ofertes que ja no
         * han d'incloure l'oferta a la qual l'usuari s'acaba d'inscriure.
         *
         * @author Daniel Sevilla i junyent
         * @param dades Un seguit de maps (String, Integer). Ens interessen els
         * parells amb claus 'oferta' i 'candidat' per executar la inscripció
         * @return Un objecte modelandview que representa el model i la vista
         * que es mostrarà a l'usuari.
         */
        @RequestMapping(value = "/inscripcioOferta/{dadesInscripcio}", method = RequestMethod.GET)
        public ModelAndView inscripcioOferta
        (@MatrixVariable(pathVar = "dadesInscripcio")
        Map<String, Integer> dades
        
            ) {

        Boolean inscripcioOK = false;

            // Les tres línies de sota es podran eliminar. La seva funció és de comprovació del codi.
            System.out.println("--- dades per fer la inscripció a l'oferta: " + dades);
            System.out.println("--- Codi de l'oferta: " + dades.get("oferta"));
            System.out.println("--- Codi del candidat: " + dades.get("candidat"));

            //
            // AQUI HAUREM DE GESTIONAR LA INSCRIPCIÓ MITJANÇANT EL MÈTODE CORRESPONENT
            // HAUREM DE REBRE UN VALOR BOOLEÀ QUE UTILITZAREM PER PASSAR FEEDBACK A L'USUARI
            //
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula", "Perfil");
            perfil.put("url", "/perfil");

            // Opció candidatures a la barra de navegació
            HashMap<String, String> candidatures = new HashMap<>();
            candidatures.put("paraula", "Candidatures");
            candidatures.put("url", "/candidatures/0"); // LI HEM DE PODER PASSAR LA REFERÈNCIA A L'USUARI 

            //
            // PENDENT AFEGIR EL CODI D'USUARI A LA URL A LES OPCIONS DE LA BARRA DE NAVEGACIÓ
            //
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_perfilCandidat, op_candidatures, op_logout};

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            if (inscripcioOK) {
                feedback.add(fb_inscripcioOfertaOK);
            } else {
                feedback.add(fb_inscripcioOfertaKO);
            }

            // També li haurem de passar la llista d'ofertes per l'usuari
            ModelAndView modelview = new ModelAndView("espaiCandidat");
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("ubicacio", "Ofertes escaients per les teves dades");
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        /**
         *
         * El mètode que s'invocarà quan el candidat premi l'opció
         * 'cancel·lar/eliminar candidatura' Ha de cridar el mètode o mètodes
         * del servei que executi/n l'operació a la bbdd
         *
         * @author Daniel Sevilla i Junyent
         * @param codiCandidatura Codi (Integer) de la candidatura.
         * @return Un objecte modelandview que representa el model i la vista
         * que es mostrarà a l'usuari.
         */
        @RequestMapping(value = "/cancelarCandidatura/{codiCandidatura}", method = RequestMethod.GET)
        public ModelAndView cancelarCandidatura
        (@PathVariable("codiCandidatura")
        Integer codiCandidatura
        
            ) {

        Boolean cancelacioOK = false;

            //
            // AQUI HAUREM DE GESTIONAR LA CANCEL·LACIÓ MITJANÇANT EL MÈTODE CORRESPONENT
            // HAUREM DE REBRE UN VALOR BOOLEÀ QUE UTILITZAREM PER PASSAR FEEDBACK A L'USUARI
            //
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula", "Perfil");
            perfil.put("url", "/perfil");

            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula", "Ofertes");
            ofertes.put("url", "/espaiCandidat"); // AQUI LI HAUREM DE PASSAR A LA URL EL CODI DE CANDIDAT PERQUE ENS MOSTRI LA INFO ESCAIENTS

            //
            // Pendent afegir el codi d'usuari a les urls de les opcions de la barra de navegació
            //
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_perfilCandidat, op_ofertesCandidat, op_logout};

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            // També li haurem de passar la LLISTA AMB LES CANDIDATURES, amb l'estat en que estiguin, si no passen integrades dins l'objecte Oferta
            // Omplo un arrayList amb les candidatures de prova per poder enviar-les al controlador
            // AL TANTO, HAUREM DE FER SERVIR UN MÈTODE QUE ENS PASSI EL TÍTOL DE L'OFERTA PASSANT-LI EL CODI
            // PQ A LA TAULA HAURIA DE SORTIR EL TÍTOL DE L'OFERTA I A L'OBJECTE CANDIDATURA HI HA EL CODI
            if (cancelacioOK) {
                feedback.add(fb_cancelacioCandidaturaOK);
            } else {
                feedback.add(fb_cancelacioCandidaturaKO);
            }

            // LI HAUREM DE PASSAR AL MODEL EL CODI DEL CANDIDAT
            ModelAndView modelview = new ModelAndView("candidatures");
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("ubicacio", "Les meves candidatures");
            modelview.getModelMap().addAttribute("candidatures", candidatures);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        /**
         *
         * Mètode que invoca la funció jQuery/Ajax que ha d'omplir l'element
         * 'select' dels formularis corresponent a les habilitats
         *
         * @author Daniel Sevilla i Junyent
         * @param idOcupacio La id de l'ocupació de la qual hem de retornar la
         * llista d'habilitats relacionades
         * @param request La petició http
         * @param response La resposta http
         * @return Una llista d'objectes de tipus Habilitat
         */
        @RequestMapping(value = "/getHabilitats/{idOcupacio}", method = RequestMethod.GET)
        public @ResponseBody
        List<Habilitat> getHabilitatsPerOcupacio
        (@PathVariable("idOcupacio")
        Integer idOcupacio, HttpServletRequest request
        , HttpServletResponse response
        
            ) {
        // Li retornem al select d'habilitats habilitats que hi han d'anar SEGONS EL CODI D'OCUPACIÓ QUE LI PASSEM
        // Retornem una llista d'objectes Habilitat

        /////////////////////// 
        /////////////////////// Les següents línies son de prova, hem de rebre el llistat de la base de dades
        /////////////////////// 
        LlistaHabilitats llistaHabilitats = new LlistaHabilitats(true); // genero una llista plena (veure constructors de la classe LlistaHabilitats 

            // Genero una llista buida amb els elements de la llista  plena amb ocupació coincident
            List<Habilitat> llistaIntermitja = new ArrayList<>();

            for (Habilitat hab : llistaHabilitats.getLlista()) {
                if (hab.getOcupacio() == idOcupacio) {
                    llistaIntermitja.add(hab);
                }
            }

            return llistaIntermitja; // Passem això com a prova

            /////////////////////// 
            /////////////////////// 
            /////////////////////// 
            // exemple de la crida al mètode del servei (que al seu torn cridaria al del repositori, si ho implementem així)
            // return categoryService.getAllSubcategories(categoryId);
        }

        /**
         *
         * Ha de retornar els objectes de tipus Experiència de la bbdd associats
         * a un candidat
         *
         * @author Daniel Sevilla i Junyent
         * @param idCandidat El codi del candidat els objectes Experiència
         * associats al qual ha de retornar el mètode
         * @param request La petició http
         * @param response La resposta http
         * @return Una llista d'objectes de tipus Experiència
         */
        @RequestMapping(value = "/getExperiencies/{idCandidat}", method = RequestMethod.GET)
        public @ResponseBody
        List<Experiencia> getExperienciesPerCandidat
        (@PathVariable("idCandidat")
        Integer idCandidat, HttpServletRequest request
        , HttpServletResponse response
        
            ) {

        //
        //
        // Mentre no tinguem el mètode de la capa servei desenvolupat retornarem una llista d'objectes experiència de prova
        //
        //
        Experiencia ex1 = new Experiencia(1, 5, "Empresa Inc.", 1, "Vaig treballar molt");
            Experiencia ex2 = new Experiencia(2, 3, "Empresot Corp.", 2, "No vaig treballar gaire");
            List<Experiencia> experiencies = new ArrayList();
            experiencies.add(ex1);
            experiencies.add(ex2);

            return experiencies;

        }

        /**
         *
         * Prepara i retorna la vista que mostra el formulari mitjançant el qual
         * una empresa crea una oferta nova
         *
         * @author Daniel Sevilla i Junyent
         * @param codiEmp El codi de l'empresa que fa l'alta de l'oferta
         * @param request La petició http
         * @param response La resposta http
         * @return Un objecte modelandview que representa el model i la vista
         * que es mostrarà a l'usuari.
         * @throws ServletException Indica que hi ha alguna errada general al
         * servlet
         * @throws IOException Indica que s'ha produït algun error
         * d'entrada/sortida
         */
        @RequestMapping(value = "/altaOferta/{codiEmpresa}", method = RequestMethod.GET)
        public ModelAndView altaOferta
        (@PathVariable("codiEmpresa")
        Integer codiEmp, HttpServletRequest request
        , HttpServletResponse response) throws ServletException, IOException {
            // Controlador que munta la vista d'alta d'oferta
            ModelAndView modelview = new ModelAndView("altaOferta");

            // Objecte buit de tipus Oferta que li passarem al formulari
            Oferta formOferta = new Oferta();

            // Opció perfil a la barra de navegació
            HashMap<String, String> op_perfil = new HashMap<>();
            op_perfil.put("paraula", "Perfil");
            op_perfil.put("url", "/perfil");

            // AQUI LI HEM DE PASSAR ELS LLISTATS PER OMPLIR ELS SELECTS QUE VINDRAN DE LA BBDD
            // PER ARA ESTÀ SIMULAT AIXÍ
            LlistaFormacions llistaFormacions = new LlistaFormacions();
            LlistaOcupacions llistaOcupacions = new LlistaOcupacions();

            op_perfilEmpresa.put("usuari", "/" + codiEmp);
            op_ofertesEmpresa.put("usuari", "/" + codiEmp);
            op_baixaEmpresa.put("usuari", "/" + codiEmp + "/");

            // Hashmap que conté les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici, op_ofertesEmpresa, op_perfilEmpresa, op_baixaEmpresa, op_logout};

            modelview.getModelMap().addAttribute("formOferta", formOferta);
            modelview.getModelMap().addAttribute("ubicacio", "Alta d'oferta");
            modelview.getModelMap().addAttribute("llistaFormacions", llistaFormacions);
            modelview.getModelMap().addAttribute("llistaOcupacions", llistaOcupacions);
            modelview.getModelMap().addAttribute("llistaTipusContracte", llistaTipusContracte);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        /**
         *
         * Rep l'objecte de tipus Oferta de la vista d'alta d'oferta Executa
         * l'alta invocant el mètode necessari del servei Passar feedback a
         * l'usuari
         *
         * @author Daniel Sevilla i Junyent
         * @param codiEmpresa El codi de l'empresa, que rebem com a PathVariable
         * @param formOferta L'objecte de tipus Oferta que rebem del formulari
         * @return Un objecte String que conrindrà la redirecció a la home
         */
        @RequestMapping(value = "/altaOferta/{codiEmpresa}/executa", method = RequestMethod.POST)
        public ModelAndView executaAltaOferta
        (@PathVariable("codiEmpresa")
        Integer codiEmpresa, 
        @ModelAttribute("formOferta") Oferta formOferta
        
            ) {

        ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");

            List<Oferta> ofertes = new ArrayList<Oferta>();

            // Afegim a l'oferta els atributs que no venen del mètode
            formOferta.setCodiEmpresa(codiEmpresa);
            formOferta.setEstat("Publicada"); // Estat que donem a les ofertes quan s'acaben de donar d'alta

            op_altaOferta.put("usuari", "/" + codiEmpresa + "/");
            op_ofertesEmpresa.put("usuari", "/" + codiEmpresa);
            op_perfilEmpresa.put("usuari", "/" + codiEmpresa);
            op_baixaEmpresa.put("usuari", "/" + codiEmpresa + "/");
            // Hashmap que conté les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici, op_altaOferta, op_ofertesEmpresa, op_perfilEmpresa, op_baixaEmpresa, op_logout};

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            try {
                // Provem de donar d'alta l'oferta a la bbdd
                ofertaService.addOferta(formOferta);
                feedback.add(fb_ofertaDesadaOK);
            } catch ( Exception e) {
                // L'alta de l'oferta no ha tingut èxit
                feedback.add(fb_ofertaNoDesada);
            }

            try {
                // Provem de recuperar totes les ofertes generades per l'empresa de la bbdd per passar-les a la vista
                ofertes = ofertaService.getOfertaByCodiEmpresa(codiEmpresa);

            } catch (Exception e) {
                // No hem pogut recuperar totes les ofertes generades per l'empresa de la bbdd
                feedback.add(fb_problemaBBDD);
            }

            modelview.getModelMap().addAttribute("ubicacio", "Ofertes generades");
            modelview.getModelMap().addAttribute("ofertes", ofertes);
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
        }

        /**
         *
         * Mostra la 'fitxa' d'informació d'una empresa, el codi de la qual
         * rebem a la url com a pathVariable
         *
         * @author Daniel Sevilla i Junyent
         * @param codiEmp El codi de l'empresa les dades de la qual mostrarem a
         * la vista
         * @param request La petició http
         * @param response La resposta http
         * @return Un objecte modelandview que representa el model i la vista
         * que es mostrarà a l'usuari.
         */
        @RequestMapping(value = "/empresa/{codiEmpresa}", method = RequestMethod.GET)
        public ModelAndView mostraEmpresa
        (@PathVariable("codiEmpresa")
        Integer codiEmp, HttpServletRequest request
        , HttpServletResponse response
        
            ) {

        ModelAndView modelview = new ModelAndView("empresa");

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            Empresa empresa;

            try {
                // Provem de recuperar l'empresa de la bbdd
                empresa = empresaService.getEmpresaByCodi(codiEmp);
            } catch (Exception e) {
                // No l'hem pogut recuperar
                modelview.setViewName("home");
                HashMap[] opcions = new HashMap[]{op_candidats, op_ofertesCandidat, op_empreses, op_logout};
                feedback.add(fb_problemaBBDD);
                modelview.getModelMap().addAttribute("ubicacio", baseline);
                modelview.getModelMap().addAttribute("feedback", feedback);
                modelview.getModelMap().addAttribute("opcions", opcions);
                return modelview;
            }

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici, op_candidats, op_ofertesCandidat, op_empreses, op_logout};

            modelview.getModelMap().addAttribute("ubicacio", "Dades de l'empresa");
            modelview.getModelMap().addAttribute("empresa", empresa);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        /**
         *
         * Prepara i retorna la vista que mostra totes les empreses donades
         * d'alta a l'aplicació
         *
         * @author Daniel Sevilla i Junyent
         * @param request La petició http
         * @return Un objecte modelandview que representa el model i la vista
         * que es mostrarà a l'usuari.
         */
        @RequestMapping(value = "/empreses", method = RequestMethod.GET)
        public ModelAndView mostraEmpreses
        (HttpServletRequest request
        
            ) {

        ModelAndView modelview = new ModelAndView("empreses");
            List<Empresa> empreses;

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            try {
                empreses = empresaService.getAllEmpreses();
            } catch (Exception e) {
                // Si la petició a la base de dades no té èxit
                modelview.setViewName("home");
                // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
                HashMap[] opcions = new HashMap[]{op_iniciAdmin, op_candidats, op_empreses, op_ofertesCandidat, op_logout};
                modelview.getModelMap().addAttribute("missatgeFeedback", msgErrorBBDD);
                feedback.add(fb_problemaBBDD);
                modelview.getModelMap().addAttribute("feedback", feedback);
                modelview.getModelMap().addAttribute("ubicacio", baseline);
                modelview.getModelMap().addAttribute("opcions", opcions);
                return modelview;
            }

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_iniciAdmin, op_ofertesAdmin, op_candidats, op_logout};

            modelview.getModelMap().addAttribute("ubicacio", "Empreses donades d'alta a l'aplicació");
            modelview.getModelMap().addAttribute("empreses", empreses);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        /**
         *
         * Prepara i retorna la vista que mostra tots els candidats donats
         * d'alta a l'aplicació
         *
         * @author Daniel Sevilla i Junyent
         * @param request La petició hhtp
         * @return Un objecte modelandview que representa el model i la vista
         * que es mostrarà a l'usuari.
         */
        @RequestMapping(value = "/candidats", method = RequestMethod.GET)
        public ModelAndView mostraCandidats
        (HttpServletRequest request
        
            ) {

        // Correspon a la vista que mostra el llistat de tots els candidats a l'admin
        ModelAndView modelview = new ModelAndView("candidats");

            // Li passarem a la vista una llista de candidats que en realitat haurem de rebre de la bbd mitjançant el corresponent mètode del servei
            // Aquesta llista està iniciliatitzada a l'inici, i al constructor, de la classe
            List<Candidat> candidats = candidatService.getAllCandidats();

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici, op_empreses, op_ofertesAdmin, op_logout};

            modelview.getModelMap().addAttribute("ubicacio", "Llistat de tots els candidats");
            modelview.getModelMap().addAttribute("candidats", candidats);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        /**
         *
         * Gestiona la petició d'eliminació d'un candidat concret, el codi de la
         * qual rebem a la url com a PathVariable Retorna un objecte modelanview
         * amb referència a la vista candidats, que mostrarà el llistat de
         * candidats actualitzat
         *
         * @author Daniel Sevilla i Junyent
         * @param codiCandidat El codi del candidat que s'ha d'esborrar
         * @param request La petició hhtp
         * @param response La resposta http
         * @return Un objecte modelandview que representa el model i la vista
         * que es mostrarà a l'usuari.
         */
        @RequestMapping(value = "/eliminaCandidat/{codiCandidat}", method = RequestMethod.GET)
        public ModelAndView eliminaCandidat
        (@PathVariable("codiCandidat")
        Integer codiCandidat, HttpServletRequest request
        , HttpServletResponse response
        
            ) {

        ModelAndView modelview = new ModelAndView("eliminaCandidat");

            // Opcions a la barra de navegació
            op_ofertesCandidat.put("usuari", "/" + codiCandidat);
            op_candidatures.put("usuari", "/" + codiCandidat);
            op_perfilCandidat.put("usuari", "/" + codiCandidat);
            op_candidatures.put("usuari", "/" + codiCandidat);

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici, op_ofertesCandidat, op_candidatures, op_perfilCandidat, op_logout};

            // Objecte CandidatFormulari per passar al formulari
            CandidatFormulari cand = new CandidatFormulari();

            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("ubicacio", "Baixa del perfil de candidat");
            modelview.getModelMap().addAttribute("formCandidat", cand);

            return modelview;
        }

        /**
         *
         * @author Daniel Sevilla i Junyent
         * @param formCandidat L'objecte de tipus Candidat que passem al
         * formulari
         * @param codiCandidat El codi del candidat
         * @param request La petició http
         * @param response La resposta http
         * @return Un objecte modelandview que representa el model i la vista
         * que es mostrarà a l'usuari.
         */
        @RequestMapping(value = "/eliminaCandidat/{codiCandidat}/executa", method = RequestMethod.POST)
        public ModelAndView executaEliminaCandidat
        (@ModelAttribute("formCandidat")
        Candidat formCandidat, 
        @PathVariable("codiCandidat") Integer codiCandidat, HttpServletRequest request
        , HttpServletResponse response
        
            ) {

        // Obtenim el rol de l'usuari loguejat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String role = auth.getAuthorities().iterator().next().toString(); // El primer element de la collection auth.getAuthorities. Assumim que només conté un element.
            String nom = auth.getName(); // email de l'usuari loguejat

            ModelAndView modelview = new ModelAndView("home");

            // Creem les opcions que aniràn a la barra de navegació
            List<Map<String, String>> opcions = new ArrayList<>();

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            if (nom.equals(formCandidat.getEmail())) {
                // Si el correu de l'usuari loguejat coincideix amb el que ha entrat l'usuari manualment al formulari
                // Provem d'executar la baixa

                try {
                    // Tractem d'esborrar el candidat de la bbdd
                    candidatService.deleteByCodi(codiCandidat);
                    // Fem el logout
                    HttpSession session = request.getSession(false);
                    SecurityContextHolder.clearContext();
                    if (session != null) {
                        session.invalidate();
                    }
                    // Posem les opcions i els missatges per la vista
                    opcions.add(op_entrarCandidat);
                    opcions.add(op_entrarEmpresa);
                    feedback.add(fb_baixaCandidatOK);

                } catch ( Exception e) {
                    // si no s'ha pogut executar la baixa del candidat
                    op_ofertesCandidat.put("usuari", "/" + codiCandidat);
                    op_candidatures.put("usuari", "/" + codiCandidat);
                    op_perfilCandidat.put("usuari", "/" + codiCandidat);
                    op_baixaCandidat.put("usuari", "/" + codiCandidat);
                    opcions.add(op_ofertesCandidat);
                    opcions.add(op_candidatures);
                    opcions.add(op_perfilCandidat);
                    opcions.add(op_baixaCandidat);
                    opcions.add(op_logout);
                    feedback.add(fb_baixaCandidatKO);

                } // del try-catch

            } else {

                // No coincideixen els correus (el de l'usuari loguejat i el que ha entrat al formulari per confirmar)
                modelview.setViewName("eliminaCandidat");
                feedback.add(fb_baixaCandidatCorreuNoValid);
                op_ofertesCandidat.put("usuari", "/" + codiCandidat);
                op_candidatures.put("usuari", "/" + codiCandidat);
                op_perfilCandidat.put("usuari", "/" + codiCandidat);
                opcions.add(op_inici);
                opcions.add(op_ofertesCandidat);
                opcions.add(op_candidatures);
                opcions.add(op_perfilCandidat);
                opcions.add(op_logout);
                modelview.getModelMap().addAttribute("feedback", feedback);
                modelview.getModelMap().addAttribute("ubicacio", "Baixa del perfil de candidat");
                modelview.getModelMap().addAttribute("opcions", opcions);
                return modelview;
            }

            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("ubicacio", baseline);
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;

        }

        /**
         *
         * Gestiona la baixa d'un candidat executada per l'administrador
         *
         * @param codiCandidat El codi del candidat que rebem a la url
         * @param request La petició http
         * @param response La resposta http
         * @return L'objecte ModelAndView amb la vista que mostrarem a l'usuari
         */
        @RequestMapping(value = "/eliminaCandidatAdmin/{codiCandidat}", method = RequestMethod.GET)
        public ModelAndView eliminaCandidatAdmin
        (@PathVariable("codiCandidat")
        Integer codiCandidat, HttpServletRequest request
        , HttpServletResponse response
        
            ) {

        ModelAndView modelview = new ModelAndView("candidats");

            // Creem les opcions que aniràn a la barra de navegació
            List<Map<String, String>> opcions = new ArrayList<>();

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            try {
                // Provem d'eliminar el candidat
                // També caldria verificar el resultat de l'operació. Ara per ara el mètode retorna void.
                candidatService.deleteByCodi(codiCandidat);
                feedback.add(fb_baixaCandidatOK);
            } catch (Exception e) {
                // Si no hem pogut
                feedback.add(fb_baixaCandidatKO);
            }

            try {
                // Provem de recuperar la llista de candidats
                // 
                // ESTEM A L'ÉSPERA DE DISPOSAR DEL MÈTODE getAllCandidats()
                //
                //
            } catch (Exception e) {
                feedback.add(fb_problemaBBDD);
            }

            opcions.add(op_iniciAdmin);
            opcions.add(op_empreses);
            opcions.add(op_ofertesAdmin);
            opcions.add(op_logout);

            // Li passarem a la vista una llista de candidats que hem de rebre de la bbd mitjançant el corresponent mètode del servei
            // Aquesta llista està iniciliatitzada a l'inici, i al constructor, de la classe
            modelview.getModelMap().addAttribute("feedback", feedback);
            // modelview.getModelMap().addAttribute("candidats", cands); TAMBÉ LI HEM DE PASSAR LA LLISTA DE CANDIDATS. ESTEM PENDENTS DEL MÈTODE.
            modelview.getModelMap().addAttribute("ubicacio", baseline);
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
        }

        /**
         *
         * Gestiona la petició d'eliminació d'una empresa, el codi de la qual
         * rebem a la url com a PathVariable
         *
         * @author Daniel Sevilla i Junyent
         * @param codiEmpresa El codi de l'empresa
         * @param request La petició http
         * @param response La resposta http
         * @return Un objecte de tipus ModelAndView
         */
        @RequestMapping(value = "/eliminaEmpresa/{codiEmpresa}", method = RequestMethod.GET)
        public ModelAndView eliminaEmpresa
        (@PathVariable("codiEmpresa")
        Integer codiEmpresa, HttpServletRequest request
        , HttpServletResponse response
        
            ) {

        ModelAndView modelview = new ModelAndView("eliminaEmpresa");

            // Opcions a la barra de navegació
            op_ofertesEmpresa.put("usuari", "/" + codiEmpresa);
            op_perfilEmpresa.put("usuari", "/" + codiEmpresa);

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici, op_ofertesEmpresa, op_perfilEmpresa, op_logout};

            // Objecte CandidatFormulari per passar al formulari
            Empresa formEmpresa = new Empresa();

            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("ubicacio", "Baixa del perfil d'empresa");
            modelview.getModelMap().addAttribute("formEmpresa", formEmpresa);

            return modelview;
        }

        /**
         *
         * Executa la baixa d'una empresa
         *
         * @author Daniel Sevilla i Junyent
         * @param formEmpresa El formulari que rebem de la vista anterior
         * (confirmació de la baixa)
         * @param codiEmpresa El codi de l'empresa
         * @param request La petició http
         * @param response La resposta http
         * @return
         */
        @RequestMapping(value = "/eliminaEmpresa/{codiEmpresa}/executa", method = RequestMethod.POST)
        public ModelAndView executaEliminaEmpresa
        (@ModelAttribute("formEmpresa")
        Empresa formEmpresa, 
        @PathVariable("codiEmpresa") Integer codiEmpresa, HttpServletRequest request
        , HttpServletResponse response
        
            ) {

        // Obtenim el rol de l'usuari loguejat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String role = auth.getAuthorities().iterator().next().toString(); // El primer element de la collection auth.getAuthorities. Assumim que només conté un element.
            String nom = auth.getName(); // email de l'usuari loguejat

            ModelAndView modelview = new ModelAndView("home");

            // Creem les opcions que aniràn a la barra de navegació
            List<Map<String, String>> opcions = new ArrayList<>();

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            if (nom.equals(formEmpresa.getEmail())) {
                // Si el correu de l'usuari loguejat coincideix amb el que ha entrat l'usuari manualment al formulari
                // Provem d'executar la baixa

                try {
                    // Tractem d'esborrar l'empresa de la bbdd
                    empresaService.esborrarEmpresaBoolean(codiEmpresa);
                    // Fem el logout
                    HttpSession session = request.getSession(false);
                    SecurityContextHolder.clearContext();
                    if (session != null) {
                        session.invalidate();
                    }
                    // Posem les opcions i els missatges per la vista
                    opcions.add(op_entrarCandidat);
                    opcions.add(op_entrarEmpresa);
                    feedback.add(fb_baixaEmpresaOK);

                } catch ( Exception e) {
                    // si no s'ha pogut executar la baixa del candidat
                    op_altaOferta.put("usuari", "/" + codiEmpresa + "/");
                    op_ofertesEmpresa.put("usuari", "/" + codiEmpresa);
                    op_perfilEmpresa.put("usuari", "/" + codiEmpresa);
                    op_baixaEmpresa.put("usuari", "/" + codiEmpresa);
                    opcions.add(op_altaOferta);
                    opcions.add(op_ofertesEmpresa);
                    opcions.add(op_perfilEmpresa);
                    opcions.add(op_baixaEmpresa);
                    opcions.add(op_logout);
                    feedback.add(fb_baixaEmpresaKO);

                } // del try-catch

            } else {

                // No coincideixen els correus (el de l'usuari loguejat i el que ha entrat al formulari per confirmar)
                modelview.setViewName("eliminaEmpresa");
                feedback.add(fb_baixaEmpresaCorreuNoValid);
                op_altaOferta.put("usuari", "/" + codiEmpresa + "/");
                op_ofertesEmpresa.put("usuari", "/" + codiEmpresa);
                op_perfilEmpresa.put("usuari", "/" + codiEmpresa);
                op_baixaEmpresa.put("usuari", "/" + codiEmpresa);
                opcions.add(op_inici);
                opcions.add(op_altaOferta);
                opcions.add(op_ofertesEmpresa);
                opcions.add(op_perfilEmpresa);
                opcions.add(op_baixaEmpresa);
                opcions.add(op_logout);
                modelview.getModelMap().addAttribute("feedback", feedback);
                modelview.getModelMap().addAttribute("ubicacio", "Baixa del perfil d'empresa");
                modelview.getModelMap().addAttribute("opcions", opcions);
                return modelview;
            }

            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("ubicacio", baseline);
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;

        }

        /**
         *
         * Gestiona la baixa d'una empresa executada per l'administrador
         *
         * @param codiEmpresa El codi de l'empresa que rebem a la url
         * @param request La petició http
         * @param response La resposta http
         * @return L'objecte ModelAndView amb la vista que retornarem a l'usuari
         */
        @RequestMapping(value = "/eliminaEmpresaAdmin/{codiEmpresa}", method = RequestMethod.GET)
        public ModelAndView eliminaEmpresaAdmin
        (@PathVariable("codiEmpresa")
        Integer codiEmpresa, HttpServletRequest request
        , HttpServletResponse response
        
            ) {

        ModelAndView modelview = new ModelAndView("empreses");

            List<Empresa> empreses = new ArrayList<>();

            // Creem les opcions que aniràn a la barra de navegació
            List<Map<String, String>> opcions = new ArrayList<>();

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            try {
                empresaService.esborrarEmpresaBoolean(codiEmpresa);
                feedback.add(fb_baixaEmpresaOK);
            } catch (Exception e) {
                feedback.add(fb_baixaEmpresaKO);
            }

            try {
                // Provem de recuperar la llista de les empreses tal com està en aquest punt
                empreses = empresaService.getAllEmpreses();
            } catch (Exception e) {
                feedback.add(fb_problemaBBDD);

            }

            opcions.add(op_iniciAdmin);
            opcions.add(op_candidats);
            opcions.add(op_empreses);
            opcions.add(op_ofertesAdmin);
            opcions.add(op_logout);

            modelview.getModelMap().addAttribute("ubicacio", "Empreses donades d'alta a l'aplicació");
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("empreses", empreses);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        /**
         *
         * Mètode que gestiona la baixa d'una oferta per part de l'administrador
         *
         * @author Daniel Sevilla i Junyent
         * @param codiOferta El codi de l'oferta que s'ha d'eliminar
         * @param request La petició http
         * @param response La resposta http
         * @return Un objecte ModelAndView que conté la referència a la vista
         * que mostrarem a l'usuari
         */
        @RequestMapping(value = "/eliminaOfertaAdmin/{codiOferta}", method = RequestMethod.GET)
        public ModelAndView eliminaOfertaAdmin
        (@PathVariable("codiOferta")
        Integer codiOferta, HttpServletRequest request
        , HttpServletResponse response
        
            ) {

        ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");

            List<Oferta> ofertes = new ArrayList<>();

            // Creem les opcions que aniràn a la barra de navegació
            List<Map<String, String>> opcions = new ArrayList<>();

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            ofertaService.esborrarOferta(codiOferta);

            try {
                // Provem d'eliminar l'oferta de la base de dades
                if (ofertaService.esborrarOferta(codiOferta)) {
                    feedback.add(fb_baixaOfertaOK);
                } else {
                    feedback.add(fb_baixaOfertaKO);
                }

            } catch (Exception e) {
                // Si no s'ha pogut fer
                feedback.add(fb_baixaOfertaKO);
            }

            try {
                // Provem de recuperar la llista d'ofertes
                ofertes = ofertaService.getAllOfertes();
            } catch (Exception e) {
                // Si no s'ha pogut fer
                feedback.add(fb_problemaBBDD);
            }

            opcions.add(op_iniciAdmin);
            opcions.add(op_candidats);
            opcions.add(op_empreses);
            opcions.add(op_logout);

            modelview.getModelMap().addAttribute("ubicacio", "Ofertes donades d'alta a l'aplicació");
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("ofertes", ofertes);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        /**
         *
         * Mètode que gestiona la baixa d'una oferta per part de l'empresa que
         * l'ha creat
         *
         * @author Daniel Sevilla i Junyent
         * @param codiEmpresa El codi de l'empresa que ha generat l'oferta i que
         * l'esborra
         * @param codiOferta El codi de l'oferta
         * @param request La petició http
         * @param response La resposta http
         * @return Un objecte ModelAndView que conté la referència a la vista
         * que es mostrarà a l'usuari
         */
        @RequestMapping(value = "/eliminaOferta/{codiEmpresa}/{codiOferta}", method = RequestMethod.GET)
        public ModelAndView eliminaOfertaAdmin
        (@PathVariable("codiEmpresa")
        Integer codiEmpresa, 
        @PathVariable("codiOferta") Integer codiOferta, HttpServletRequest request
        , HttpServletResponse response
        
            ) {

        ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");

            List<Oferta> ofertes = new ArrayList<>();

            // Creem les opcions que aniràn a la barra de navegació
            List<Map<String, String>> opcions = new ArrayList<>();

            // Llista que contindrà els missatges de feedback que passarem a la vista
            List<Map<String, String>> feedback = new ArrayList<>();

            try {
                // Provem d'eliminar l'oferta de la base de dades
                ofertaService.esborrarOferta(codiOferta);
                feedback.add(fb_baixaOfertaOK);
            } catch ( Exception e) {
                // Si no s'ha pogut fer
                feedback.add(fb_baixaOfertaKO);
            }

            try {
                // Provem de recuperar la llista d'ofertes
                ofertes = ofertaService.getOfertaByCodiEmpresa(codiEmpresa);
            } catch (Exception e) {
                // Si no s'ha pogut fer
                feedback.add(fb_problemaBBDD);
            }

            op_altaOferta.put("usuari", "/" + codiEmpresa + "/");
            op_perfilEmpresa.put("usuari", "/" + codiEmpresa);
            op_baixaEmpresa.put("usuari", "/" + codiEmpresa + "/");

            opcions.add(op_altaOferta);
            opcions.add(op_perfilEmpresa);
            opcions.add(op_baixaEmpresa);
            opcions.add(op_logout);

            modelview.getModelMap().addAttribute("ubicacio", "Ofertes generades");
            modelview.getModelMap().addAttribute("feedback", feedback);
            modelview.getModelMap().addAttribute("ofertes", ofertes);
            modelview.getModelMap().addAttribute("opcions", opcions);

            return modelview;
        }

        /**
         *
         * Gestiona la petició dels botons 'Tornar', que retorna una redirecció
         * al referer (url de la qual veniem)
         *
         * @author Daniel Sevilla i Junyent
         * @param request La petició http
         * @return Una cadena que conté una redirecció a la url anterior
         */
        @RequestMapping(value = "/enrera", method = RequestMethod.GET)
        public String anarEnrera
        (HttpServletRequest request
        
            ) {
        // Controlador que utilitzem per tornar a la pàgina anterior 
        String referer = request.getHeader("Referer");

            return "redirect:" + referer;
        }
        /**
         *
         * Converteix un item de candidat, de tipus Candidat (el que desem a la
         * bbdd) a tipus CandidatFormulari, apte per utilitzar amb els
         * formularis Diferències implementades: La data de naixement, a la
         * classe CandidatFormulari, és de tipus LocalDate / per desar a la bbdd
         * ha de ser de tipus java.sql.Date
         *
         * @author Daniel Sevilla i Junyent
         * @param c El candidat, de tipus CandidatFormulari, que li passem
         * @return L'objecte convertit a objecte de la classe CandidatFormulari
         */
    public CandidatFormulari aCandidatFormulari(Candidat c) {

        CandidatFormulari retornar = new CandidatFormulari();

        retornar.setCodi(c.getCodi());
        retornar.setNom(c.getNom());
        retornar.setCognoms(c.getCognoms());
        retornar.setDniNif(c.getDniNif());
        retornar.setTelefon(c.getTelefon());
        retornar.setPoblacio(c.getPoblacio());
        retornar.setProvincia(c.getProvincia());
        retornar.setDataNaix(c.getDataNaix().toLocalDate()); // La tarnsformem a tipus LocalDate
        retornar.setEmail(c.getEmail());
        retornar.setPass(c.getPass());
        retornar.setcPass(c.getcPass());
        retornar.setAdreca(c.getAdreca());
        retornar.setFormacio(c.getFormacio());
        retornar.setOcupacio(c.getOcupacio());
        retornar.setObservacions(c.getObservacions());
        retornar.setHabilitats(c.getHabilitats());
        retornar.setExperiencies(c.getExperiencies());

        return retornar;
    }

    /**
     *
     * Converteix un item de candidat, de tipus CandidatFormulari (el que fa
     * servir el formulari) a tipus Candidat, apte per desar a la base de dades
     * Diferències implementades: La data de naixement, a la classe
     * CandidatFormulari, és de tipus LocalDate / per desar a la bbdd ha de ser
     * de tipus java.sql.Date
     *
     * @author Daniel Sevilla i Junyent
     * @param c El candidat, de tipus CandidatFormulari, que li passem
     * @return L'objecte convertit a objecte de la classe Candidat
     */
    public Candidat aCandidat(CandidatFormulari c) {

        Candidat retornar = new Candidat();

        retornar.setCodi(c.getCodi());
        retornar.setNom(c.getNom());
        retornar.setCognoms(c.getCognoms());
        retornar.setDniNif(c.getDniNif());
        retornar.setTelefon(c.getTelefon());
        retornar.setPoblacio(c.getPoblacio());
        retornar.setProvincia(c.getProvincia());
        retornar.setDataNaix(Date.valueOf(c.getDataNaix())); // La tarnsformem a tipus java.sql.Date
        retornar.setEmail(c.getEmail());
        retornar.setPass(c.getPass());
        retornar.setcPass(c.getcPass());
        retornar.setAdreca(c.getAdreca());
        retornar.setFormacio(c.getFormacio());
        retornar.setOcupacio(c.getOcupacio());
        retornar.setObservacions(c.getObservacions());
        retornar.setHabilitats(c.getHabilitats());
        retornar.setExperiencies(c.getExperiencies());

        return retornar;
    }

}
