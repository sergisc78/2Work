package towork.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import towork.domain.Candidatura;
import towork.domain.Empresa;
import towork.domain.Formacio;
import towork.domain.Habilitat;
import towork.formularis.LlistaCandidatures;
import towork.formularis.LlistaHabilitats;
import towork.domain.Oferta;
import towork.formularis.LlistaFormacions;
import towork.formularis.LlistaOcupacions;
import towork.service.EmpresaService;

@Controller
public class EspaiController {
      
      @Autowired
      EmpresaService empresaService;
      
      // Opcions reutilitzables per la barra de navegació
      HashMap<String, String> op_entrar_candidat = new HashMap<>();
      HashMap<String, String> op_entrar_empresa = new HashMap<>();
      HashMap<String, String> op_entrar_admin = new HashMap<>();
      HashMap<String, String> op_logout = new HashMap<>();
      HashMap<String, String> op_inici = new HashMap<>();
      HashMap<String, String> op_candidats = new HashMap<>();
      HashMap<String, String> op_candidatures = new HashMap<>(); // candidatures
      HashMap<String, String> op_empreses = new HashMap<>();
      HashMap<String, String> op_ofertes = new HashMap<>();
      HashMap<String, String> op_ofertesEmpresa = new HashMap<>();
      HashMap<String, String> op_ofertesAdmin = new HashMap<>();
      
      // Missatges de feedback reutilitzables
      final String msgErrorBBDD = "Hi ha hagut algun problema rebent les dades de la base de dades.";

    
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
      public ArrayList<Candidat> candidats = new ArrayList();
      List <String> llistaTipusContracte = new ArrayList();
      
      
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
            op_entrar_candidat.put("paraula","Candidat");
            op_entrar_candidat.put("url","/espaiCandidat");
            op_entrar_empresa.put("paraula","Empresa");
            op_entrar_empresa.put("url","/espaiEmpresa");
            op_entrar_admin.put("paraula","Administrador");
            op_entrar_admin.put("url","/espaiAdmin");
            op_logout.put("paraula","Logout");  
            op_logout.put("url","/j_spring_security_logout");
            op_inici.put("paraula","Inici");  
            op_inici.put("url","/");
            op_candidats.put("paraula","Candidats");  
            op_candidats.put("url","/candidats");
            op_candidatures.put("paraula","Candidatures");  
            op_candidatures.put("url","/candidatures");
            op_empreses.put("paraula","Empreses");  
            op_empreses.put("url","/empreses");
            op_ofertes.put("paraula","Ofertes");  
            op_ofertes.put("url","/espaiCandidat");
            op_ofertesEmpresa.put("paraula","Ofertes");
            op_ofertesEmpresa.put("url","/ofertesEmpresa"); // Fins que no la canviem aquesta és la url que porta a la vista on mostrem totes les ofertes
            op_ofertesAdmin.put("paraula","Ofertes");
            op_ofertesAdmin.put("url","ofertesAdmin");
            
        
            //// Dono valors als objectes de prova creats 
            
            // Habilitats
            h1.setNomHab("habilitat1");
            h2.setNomHab("habilitat2");
            h3.setNomHab("habilitat3");
            
            h1.setCodiHab(1);
            h2.setCodiHab(2);
            h3.setCodiHab(3);
            
            // Candidats
            c1.setNom("Andreu"); c1.setCognoms("Albets Albareda"); c1.setCodi(1);
            c2.setNom("Blanca"); c2.setCognoms("Blancafort Bonet"); c2.setCodi(2);
            c3.setNom("Carla"); c3.setCognoms("Calvet Canudas"); c3.setCodi(4);
            c4.setNom("Dídac"); c4.setCognoms("Dentera Deltell"); c4.setCodi(4);
            c5.setNom("Elisabet"); c5.setCognoms("Elias Entrena"); c5.setCodi(5);
            c6.setNom("Francina"); c6.setCognoms("Franch Floridablanca"); c6.setCodi(6);
            c7.setNom("Guerau"); c7.setCognoms("Guiu Gallifa"); c7.setCodi(7);
            c8.setNom("Helena"); c8.setCognoms("Humet Herrera"); c8.setCodi(8);
            candidats.add(c1);
            candidats.add(c2);
            candidats.add(c3);
            candidats.add(c4);
            candidats.add(c5);
            candidats.add(c6);
            candidats.add(c7);
            candidats.add(c8);
               
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
            of.setHorari("De 9 a 15h"); // Això crec està pendent d'acabar de definir bé al domini.
            of.setPoblacio("Cardona");
            of.setNifEmpresa("22333444K"); // Aquesta dada haurà de servir de PK per extreure el nom de l'empresa de la bbdd? Mostrarem el nom i no el 
            of.setSou(25000d);
            of.setTipusContracte("Indefinit");
            of.setTitolOferta("Títol de l'oferta");
            
            // Candidatures
            candidatures = getListOfCandidatures();
            llistaCandidatures.setLlista(candidatures);
            
            // Fins aqui els objectes de prova
        
            // Possibles estats de les candidatures (aquesta part potser es pot deixar definida aqui. Probablement no és el millor lloc)
            estatsPossibles.put(0,"Pendent revisar");
            estatsPossibles.put(1,"Denegada"); 
            estatsPossibles.put(2,"Aprovada");
            
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
       * Prepara i retorna la vista que mostra les ofertes disponibles per l'usuari candidat que està loguejat
       * 
       * @author Daniel Sevilla i Junyent
       * @param request La petició http
       * @param response La resposta http
       * @return Objecte modelandview que representa la vista que mostra les ofertes adients per un candidat en concret
       * @throws ServletException Indica que hi ha alguna errada general al servlet
       * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
       */
      @RequestMapping(value = "/espaiCandidat", method = RequestMethod.GET)
      public ModelAndView EspaiCandidatRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
            // Desem a la variable 'username' el nom de l'usuari que s'ha acreditat
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
                    
            // Crec que a partir del nom d'usuari/email hem d'agafar el codi d'usuari de la bbdd, mitjançant el mètode del servei corresponent, per passar-lo com a paràmetre a les opcions que ho requereixin
            
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil/"+username);
        
            // Opció candidatures a la barra de navegació
            HashMap<String, String> candidatures_usuari = new HashMap<>();
            candidatures_usuari.put("paraula","Candidatures");  
            candidatures_usuari.put("url","/candidatures/"+username); // ÉS UNA PROVA, LI HAURIEM DE PASSAR EL CODI
                  
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,candidatures_usuari,op_logout};  
          
            ModelAndView modelview = new ModelAndView("espaiCandidat");
            modelview.getModelMap().addAttribute("ubicacio", "Ofertes escaients per les teves dades");
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
    
      
      /**
      * 
      * @author Daniel Sevilla i Junyent
      * @param request La petició http
      * @param response La resposta http
      * @return Objecte modelandview que representa el model i la vista
      * @throws ServletException Indica que hi ha alguna errada general al servlet
      * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
      */
      @RequestMapping(value = "/espaiEmpresa", method = RequestMethod.GET)
      public ModelAndView EspaiEmpresaRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ModelAndView modelview = new ModelAndView("espaiEmpresa");
            
;        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_logout};  
            
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }

      
      /**
       * 
       * @author Daniel Sevilla i Junyent
       * @param request La petició http
       * @param response La resposta http
       * @return Objecte modelandview que representa el model i la vista 
       * @throws ServletException Indica que hi ha alguna errada general al servlet
       * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
       */
      @RequestMapping(value = "/espaiAdmin", method = RequestMethod.GET)
      public ModelAndView EspaiAdministradorRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ModelAndView modelview = new ModelAndView("espaiAdmin");
                   
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici, op_candidats, op_empreses, op_ofertesAdmin, op_logout};  
            
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }

      
      /**
      *
      * Prepara i retorna la vista que mostra els detalls d'una oferta concreta, el codi del qual rebem a la url com a PathVariable
      * 
      * @author Daniel Sevilla i Junyent
      * @param codiOferta El codi de l'oferta el detall de la qual mostrarà la vista
      * @param request La petició http
      * @param response La resposta http
      * @return Objecte modelabdview que representa el model i la vista que mostrarà a l'usuari el detall d'una oferta concreta
      * @throws ServletException Indica que hi ha alguna errada general al servlet
      * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
      */
      @RequestMapping(value = "/oferta/{codiOferta}", method = RequestMethod.GET)
      public ModelAndView OfertaPerCodi(@PathVariable("codiOferta") Integer codiOferta, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
            // Obtenim el rol de l'usuari loguejat
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String role = auth.getAuthorities().iterator().next().toString(); // El primer element dela collection auth.getAuthorities. Assumim que només conté un element.
            
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");
        
            // Opció ofertes a la barra de navegació
            HashMap<String, String> _ofertes = new HashMap<>();
            _ofertes.put("paraula","Ofertes");            
            if (role.equals("ROLE_USER")) {
                  _ofertes.put("url","/espaiCandidat"); // Fins que no la canviem aquesta és la url que porta a la vista on mostrem totes les ofertes pels candidats
            } else {
                  _ofertes.put("url","/ofertesAdminEmpresa");
            }
                                                                                    
            // Opció candidatures a la barra de navegació
            HashMap<String, String> _cands = new HashMap<>();
            _cands.put("paraula","Candidatures");  
            _cands.put("url","/candidatures/3"); // LI HEM DE PODER PASSAR LA REFERÈNCIA A L'USUARI 
                    
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,_ofertes,_cands,op_logout};  
        
            ModelAndView modelview = new ModelAndView("oferta");
            // Oferta of = toWorkService.getOfertaByRef(ref); // En aquesta linia invocarem el mètode del servei per recuperar l'objecte oferta que després passarem a la vista
           
            // Afegeixo els atributs per passar a la vista
            modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
            modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
            modelview.getModelMap().addAttribute("opcions", opcions);
                    
            return modelview;
      }
      
      /**
       * 
       * Prepara i retorna la vista que mostra els detalls d'una oferta concreta a administrador, el codi de la qual rebem a la url com a PathVariable
       * Reutilitza la vista oferta
       * 
       * @author Daniel Sevilla i Junyent
       * @param codiOferta El codi de l'oferta els detalls de la qual mostrarà la vista
       * @param request La petició http
       * @param response La resposta http
       * @return Objecte modelabdview que representa el model i la vista que mostrarà a l'usuari el detall d'una oferta concreta
       * @throws ServletException
       * @throws IOException 
       */
      @RequestMapping(value = "/ofertaAdmin/{codiOferta}", method = RequestMethod.GET)
      public ModelAndView OfertaAdminPerCodi(@PathVariable("codiOferta") Integer codiOferta, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
            ModelAndView modelview = new ModelAndView("oferta");
                                  
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici, op_candidats, op_empreses, op_ofertesAdmin, op_logout};  
            
            modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
            modelview.getModelMap().addAttribute("candidatures", llistaCandidatures);
            modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
      
      
      /**
      *
      * Prepara i retorna la vista que mostra els detalls d'una oferta concreta, el codi del qual rebem a la url com a PathVariable
      * En aquest cas passarem a la vista el referer perque pugui incloure un botó 'tornar' funcional
      * 
      * @author Daniel Sevilla i Junyent
      * @param codi El codi de l'oferta lea dades de la qual mostrarem
      * @param request La petició http
      * @param response La resposta http
      * @return objecte modelandview que representa el model i la vista oferta només amb el botó 'Tornar' al cos
      * @throws ServletException Indica que hi ha alguna errada general al servlet
      * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
      */
      @RequestMapping(value = "/ofertaTornar/{codi}", method = RequestMethod.GET)
      public ModelAndView OfertaPerRefTornar(@PathVariable("codi") Integer codi, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
            // Controlador que invoca la vista perque aquesta mostri només el botó 'Tornar'
            // Pensat per quan el candidat vulgui consultar l'oferta des del llistat de candidatures
            // Passem el referer a la vista com a atribut
          
            // Obtenim el rol de l'usuari loguejat
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String role = auth.getAuthorities().iterator().next().toString(); // El primer element dela collection auth.getAuthorities. Assumim que només conté un element.
            
            
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");
        
            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");
            
            switch(role){
                  case "ROLE_USER": { 
                        ofertes.put("url","/espaiCandidat"); // Fins que no la canviem aquesta és la url que porta a la vista on mostrem totes les ofertes pels candidats
                        break;
                  }
                  case "ROLE_EMPRESA": {
                        ofertes.put("url","/ofertesEmpresa");
                        break;
                  }
                  case "ROLE_ADMIN": {
                        ofertes.put("url","/ofertesAdmin");
                        break;
                  }
            }
        
            // Opció candidatures a la barra de navegació
            HashMap<String, String> _cands = new HashMap<>();
            _cands.put("paraula","Candidatures");  
            _cands.put("url","/candidatures?candidat='0'"); // LI HEM DE PODER PASSAR LA REFERÈNCIA A L'USUARI 
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,ofertes,_cands,op_logout};  
        
            ModelAndView modelview = new ModelAndView("oferta");
            // Oferta of = toWorkService.getOfertaByRef(ref); // En aquesta linia invocarem el mètode del servei per recuperar l'objecte oferta que després passarem a la vista
            
            // Afegeixo els atributs per passar a la vista
            modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
            modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("referer", request.getHeader("Referer"));
                    
            return modelview;
      }

      
      /**
       * 
       * Prepara i retorna la vista que mostra els detalls d'una oferta a l'usuari empresa (propietari) de l'oferta
       * (comparteix vista amb altres tipus d'usuari però en aquest cas mostra més contingut
       * 
       * @author Daniel Sevilla i junyent
       * @param codiOferta El codi de l'oferta (un Integer) les dades de la qual mostrarem al propietari d'aquesta
       * @param request La petició http
       * @param response La resposta http
       * @return Un objecte modelandview que representa el model i la vista que mostrarem a l'usuari
       * @throws ServletException Indica que hi ha alguna errada general al servlet
       * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
       */
      @RequestMapping(value = "/ofertaPropietari/{codiOferta}", method = RequestMethod.GET)
      public ModelAndView OfertaPropietariPerRef(@PathVariable("codiOferta") Integer codiOferta, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            // Mostrarà l'oferta al seu propietari (de manera diferent als no propietaris, però compartirem vista)      

            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil, op_ofertesEmpresa, op_logout};  

            ModelAndView modelview = new ModelAndView("oferta");
            // Oferta of = toWorkService.getOfertaByRef(ref); // En aquesta linia invocarem el mètode del servei per recuperar l'objecte oferta que després passarem a la vista

            Candidatura formCandidatura = new Candidatura();

            ///// Final dels objectes de prova que genero per poder muntar la vista ////////////////////////////////////////////////////////////////////////////////////////////


            // Afegeixo els atributs per passar a la vista
            modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
            modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
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
       * @return Un objecte modelandview que representa el model i la vista que mostrarem a l'usuari
       * @throws ServletException Indica que hi ha alguna errada general al servlet
       * @throws IOException Indica que s'ha produït algun error d'entrada/sortida 
       */
      @RequestMapping(value = "/ofertesAdmin", method = RequestMethod.GET)
      public ModelAndView OfertesAdminRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");
            
            // Aquesta vista en principi haurà de mostrar totes les ofertes
            // Les hem d'obtenir de la bbdd mitjançant el mètode de la capa servei

            // Llista que contindrà les opcions que hi haurà a la barra de navegació
            List<Map<String , String>> opcions  = new ArrayList<>();
            
            opcions.add(op_inici);
            opcions.add(op_candidats);
            opcions.add(op_empreses);
            opcions.add(op_logout);
        
            modelview.getModelMap().addAttribute("ubicacio", "Vista general de les ofertes");
            modelview.getModelMap().addAttribute("opcions", opcions);
        
            return modelview;
      }    

      
      /**
       * 
       * Prepara i retorna la vista d'ofertes, filtrades per l'empresa que les ha creat, a l'administrador
       * 
       * @author Daniel Sevilla i Junyent
       * @param codiEmpresa Un integer que representa el codi de l'empresa les ofertes creades per la qual mostrarem a l'administrador
       * @param request La petició http
       * @param response La respsta http
       * @return Un objecte modelandview que representa el model i la vista que mostrarem a l'usuari
       * @throws ServletException Indica que hi ha alguna errada general al servlet
       * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
       */
      @RequestMapping(value = "/ofertesAdmin/{codiEmpresa}", method = RequestMethod.GET)
      public ModelAndView OfertesAdminPerEmpresaRequest(@PathVariable("codiEmpresa") int codiEmpresa, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Mostrarà a l'administrador les ofertes d'una empresa concreta, el codi de la qual li passem per PathVariable
            // Caldrà invocar el mètode que retornarà ofertes per codi d'empresa
            
            
            // 
            //
            //
            // Pendent rebre i passar les ofertes per codi d'empresa
            //
            //
            
            ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");
            
            // Llista que contindrà les opcions que hi haurà a la barra de navegació
            List<Map<String , String>> opcions  = new ArrayList<>();
            
            opcions.add(op_inici);
            opcions.add(op_candidats);
            opcions.add(op_empreses);
            opcions.add(op_ofertesAdmin);
            opcions.add(op_logout);
            
            modelview.getModelMap().addAttribute("ubicacio", "Vista d'ofertes per empresa");
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
      
      
      /**
       * 
       * Prepara i retorna la vista d'ofertes a l'empresa que les ha creat
       * 
       * @author Daniel Sevilla i Junyent
       * @param codiEmpresa El codi de l'empresa les ofertes creades per la qual mostrarem a l'usuari empresa
       * @param request La petició http
       * @param response La resposta http
       * @return OUn objecte modelandview que representa el model i la vista que mostrarem a l'usuari
       * @throws ServletException Indica que hi ha alguna errada general al servlet
       * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
       */
      @RequestMapping(value = "/ofertesEmpresa/{codiEmpresa}", method = RequestMethod.GET)
      public ModelAndView OfertesEmpresaRequest(@PathVariable("codiEmpresa") int codiEmpresa, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");
            
            // En aquest cas haurem de mostrar les ofertes que pertanyen a l'empresa el codi de la qual passem com a PathVariable
            // Les hem d'obtenir de la bbdd mitjançant el mètode de la capa servei
            
            // Llista que contindrà les opcions que hi haurà a la barra de navegació
            List<Map<String , String>> opcions  = new ArrayList<>();
            
            // Opció perfil a la barra de navegació
            HashMap<String, String> op_perfil = new HashMap<>();
            op_inici.put("paraula","Perfil");
            op_inici.put("url","/perfil");
            
            opcions.add(op_inici);
            opcions.add(op_perfil);
            opcions.add(op_logout);
            
            modelview.getModelMap().addAttribute("ubicacio", "Ofertes generades");
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }

      
      /**
       * 
       * Prepara i retorna la vista que mostra les dades del candidat que es passa com a PathVariable
       * 
       * @author Daniel Sevilla i Junyent
       * @param codiCandidat El codi del candidat les dades del qual mostrarà la vista
       * @param request La petició http
       * @param response La resposta http
       * @return L'objecte modelandview que representa el model i la vista que mostrarem a l'usuari
       * @throws ServletException Indica que hi ha alguna errada general al servlet
       * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
       */
      @RequestMapping(value = "/candidat/{codiCandidat}", method = RequestMethod.GET)
      public ModelAndView CandidatPerCodi(@PathVariable("codiCandidat") Integer codiCandidat, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
            // Passem a la vista les opcions de la barra de navegacó PER USUARI EMPRESA
            // Aquesta mateixa vista l'hauria de poder veure com a mínim l'admin, probablement amb altres opcions a la barra de navegació
            // Queda pendent veure en quin moment podem passar unes o altres opcions segons el tipus d'usuari, i implementar-ho

            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,op_ofertesEmpresa,op_logout};  


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
       * Prepara i retorna la vista que mostra totes les candidatures del candidat el codi del qual passem com a PathVariable
       * 
       * @author Daniel Sevilla i Junyent
       * @param codiCandidat El codi del qual la vista mostrarà les candidatures
       * @param request La petició http
       * @param response La resposta http
       * @return Un objecte modelandview que representa el model i la vista que mostrarem a l'usuari
       * @throws ServletException Indica que hi ha alguna errada general al servlet
       * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
       */
      @RequestMapping(value = "/candidatures/{codiCandidat}", method = RequestMethod.GET)
      public ModelAndView CandidaturesPerCandidat(@PathVariable("codiCandidat") Integer codiCandidat, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
            // Passem a la vista les opcions de la barra de navegacó PER USUARI EMPRESA
            // Aquesta mateixa vista l'hauria de poder veure com a mínim l'admin, probablement amb altres opcions a la barra de navegació
            // Queda pendent veure en quin moment podem passar unes o altres opcions segons el tipus d'usuari, i implementar-ho

            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");

            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertesCandidat = new HashMap<>();
            ofertesCandidat.put("paraula","Ofertes");
            ofertesCandidat.put("url","/espaiCandidat"); // AQUI LI HAUREM DE PASSAR A LA URL EL CODI DE CANDIDAT PERQUE ENS MOSTRI LA INFO ESCAIENTS

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,ofertesCandidat,op_logout};
            
            // També li haurem de passar la LLISTA AMB LES CANDIDATURES, amb l'estat en que estiguin, si no passen integrades dins l'objecte Oferta
            // Omplo un arrayList amb les candidatures de prova per poder enviar-les al controlador
            // AL TANTO, HAUREM DE FER SERVIR UN MÈTODE QUE ENS PASSI EL TÍTOL DE L'OFERTA PASSANT-LI EL CODI
            // PQ A LA TAULA HAURIA DE SORTIR EL TÍTOL DE L'OFERTA I A L'OBJECTE CANDIDATURA HI HA EL CODI
            List<Candidatura> candidatures = getListOfCandidatures();
            LlistaCandidatures llistaCandidatures = new LlistaCandidatures();
            llistaCandidatures.setLlista(candidatures);

            
            ModelAndView modelview = new ModelAndView("candidatures");
            modelview.getModelMap().addAttribute("ubicacio", "Les meves candidatures");
            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("candidatures", candidatures);

            return modelview;
      }

    
      /**
       * 
       * Prepara i retorna la vista que es mostrarà a l'usuari quan s'hagi executat el logout
       * 
       * @author Daniel Sevilla i Junyent
       * @param request La petició http
       * @return Un objecte modelandview que representa la vista que es mostrarà a l'usuari
       */
      @RequestMapping(value = "/logout", method = RequestMethod.GET)
      public ModelAndView logout(HttpServletRequest request) {
           
            // Creem les opcions que aniràn a la barra de navegació
            
            // Opció Administrador a la barra de navegació
            HashMap<String, String> admin = new HashMap<>();
            admin.put("paraula","Administrador");
            admin.put("url","/espaiAdmin");
        
            // Opció Candidat a la barra de navegació
            HashMap<String, String> candidat = new HashMap<>();
            candidat.put("paraula","Candidat");
            candidat.put("url","/espaiCandidat");
        
            // Opció Empresa a la barra de navegació
            HashMap<String, String> empresa = new HashMap<>();
            empresa.put("paraula","Empresa");
            empresa.put("url","/espaiEmpresa");
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{admin,candidat,empresa};  
          
            ModelAndView modelview = new ModelAndView("home");
            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("ubicacio", "La teva web de cerca de feina");
            modelview.getModelMap().addAttribute("missatgeFeedback", "Has sortit de l'aplicació. Fins aviat!");
            modelview.getModelMap().addAttribute("classeFeedback", "alert-warning");
            
            return modelview;
      }
      
      
      /**
       * 
       * El mètode que s'invocarà quan el candidat premi l'opció 'inscriure'm a l'oferta'
       * Ha de cridar el mètode del servei que executi la inscripció a la bbdd
       * Retorna la vista d'ofertes seleccionades per l'usuari, a la qual li passarem la nova llista d'ofertes que ja no han d'incloure l'oferta a la qual l'usuari s'acaba d'inscriure.
       * 
       * @author Daniel Sevilla i junyent
       * @param dades Un seguit de maps (String, Integer). Ens interessen els parells amb claus 'oferta' i 'candidat' per executar la inscripció
       * @return Un objecte modelandview que representa el model i la vista que es mostrarà a l'usuari.
       */
      @RequestMapping(value = "/inscripcioOferta/{dadesInscripcio}", method = RequestMethod.GET)
      public ModelAndView inscripcioOferta(@MatrixVariable(pathVar="dadesInscripcio") Map<String, Integer> dades){
           
           Boolean inscripcioOK=false;
           
           // Les tres línies de sota es podran eliminar. La seva funció és de comprovació del codi.
           System.out.println("--- dades per fer la inscripció a l'oferta: "+dades);
           System.out.println("--- Codi de l'oferta: "+dades.get("oferta"));
           System.out.println("--- Codi del candidat: "+dades.get("candidat"));
           
           
           //
           // AQUI HAUREM DE GESTIONAR LA INSCRIPCIÓ MITJANÇANT EL MÈTODE CORRESPONENT
           // HAUREM DE REBRE UN VALOR BOOLEÀ QUE UTILITZAREM PER PASSAR FEEDBACK A L'USUARI
           //
           
           
           // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");
        
            // Opció candidatures a la barra de navegació
            HashMap<String, String> candidatures = new HashMap<>();
            candidatures.put("paraula","Candidatures");  
            candidatures.put("url","/candidatures?candidat='0'"); // LI HEM DE PODER PASSAR LA REFERÈNCIA A L'USUARI 
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,candidatures,op_logout};  
            
            // missatge i classe del missatge de feedback que rebrà l'usuari
            String missatgeFeedback =""; // missatge de feedback que rebrà l'usuari 
            String classeFeedback = "";  // classe CSS que s'aplicarà al contenidor del missatge de feedback
            
            if (inscripcioOK) {
                  missatgeFeedback = "Inscripció a l'oferta realitzada correctament";
                  classeFeedback = "alert-warning";
            } else {
                  missatgeFeedback = "Inscripció a l'oferta NO realitzada.";
                  classeFeedback = "alert-danger";
            }

            // També li haurem de passar la llista d'ofertes per l'usuari
            ModelAndView modelview = new ModelAndView("espaiCandidat");
            modelview.getModelMap().addAttribute("missatgeFeedback", missatgeFeedback);
            modelview.getModelMap().addAttribute("classeFeedback", classeFeedback);
            modelview.getModelMap().addAttribute("ubicacio", "Ofertes escaients per les teves dades");
            modelview.getModelMap().addAttribute("opcions", opcions);
           
           return modelview;
     }
     
     /**
      * 
      * El mètode que s'invocarà quan el candidat premi l'opció 'cancel·lar/eliminar candidatura'
      * Ha de cridar el mètode o mètodes del servei que executi/n l'operació a la bbdd
      * 
      * @author Daniel Sevilla i Junyent
      * @param codiCandidatura Codi (Integer) de la candidatura.
      * @return Un objecte modelandview que representa el model i la vista que es mostrarà a l'usuari.
      */
     @RequestMapping(value = "/cancelarCandidatura/{codiCandidatura}", method = RequestMethod.GET)
     public ModelAndView cancelacioCandidatura(@PathVariable("codiCandidatura") Integer codiCandidatura) {
           
           Boolean cancelacioOK=false;
           
           //
           // AQUI HAUREM DE GESTIONAR LA CANCEL·LACIÓ MITJANÇANT EL MÈTODE CORRESPONENT
           // HAUREM DE REBRE UN VALOR BOOLEÀ QUE UTILITZAREM PER PASSAR FEEDBACK A L'USUARI
           //
           
           // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");

            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");
            ofertes.put("url","/espaiCandidat"); // AQUI LI HAUREM DE PASSAR A LA URL EL CODI DE CANDIDAT PERQUE ENS MOSTRI LA INFO ESCAIENTS

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,ofertes,op_logout};
            
            // També li haurem de passar la LLISTA AMB LES CANDIDATURES, amb l'estat en que estiguin, si no passen integrades dins l'objecte Oferta
            // Omplo un arrayList amb les candidatures de prova per poder enviar-les al controlador
            // AL TANTO, HAUREM DE FER SERVIR UN MÈTODE QUE ENS PASSI EL TÍTOL DE L'OFERTA PASSANT-LI EL CODI
            // PQ A LA TAULA HAURIA DE SORTIR EL TÍTOL DE L'OFERTA I A L'OBJECTE CANDIDATURA HI HA EL CODI
            
           // missatge i classe del missatge de feedback que rebrà l'usuari
            String missatgeFeedback =""; // missatge de feedback que rebrà l'usuari 
            String classeFeedback = "";  // classe CSS que s'aplicarà al contenidor del missatge de feedback
           
           if (cancelacioOK) {
                  missatgeFeedback = "Cancel·lació de la candidatura realitzada correctament";
                  classeFeedback = "alert-warning";
            } else {
                  missatgeFeedback = "Cancel·lació de la candidatura NO realitzada.";
                  classeFeedback = "alert-danger";
            }
            
            // LI HAUREM DE PASSAR AL MODEL EL CODI DEL CANDIDAT
            ModelAndView modelview = new ModelAndView("candidatures");
            modelview.getModelMap().addAttribute("missatgeFeedback", missatgeFeedback);
            modelview.getModelMap().addAttribute("classeFeedback", classeFeedback);
            modelview.getModelMap().addAttribute("ubicacio", "Les meves candidatures");
            modelview.getModelMap().addAttribute("candidatures", candidatures);
            modelview.getModelMap().addAttribute("opcions", opcions);
           
           return modelview;       
     }
     
     
      /**
       * 
       * Mètode que invoca la funció jQuery/Ajax que ha d'omplir l'element 'select' dels formularis corresponent a les habilitats 
       * 
       * @author Daniel Sevilla i Junyent
       * @param idOcupacio La id de l'ocupació de la qual hem de retornar la llista d'habilitats relacionades
       * @param request La petició http
       * @param response La resposta http
       * @return Una llista d'objectes de tipus Habilitat
       */
      @RequestMapping(value = "/getHabilitats/{idOcupacio}", method = RequestMethod.GET)
      public @ResponseBody  List<Habilitat> getHabilitatsPerOcupacio(@PathVariable("idOcupacio") Integer idOcupacio,HttpServletRequest request, HttpServletResponse response) {
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
       * Prepara i retorna la vista que mostra el formulari mitjançant el qual una empresa crea una oferta nova
       * 
       * @author Daniel Sevilla i Junyent
       * @param request La petició http
       * @param response La resposta http
       * @return Un objecte modelandview que representa el model i la vista que es mostrarà a l'usuari.
       * @throws ServletException Indica que hi ha alguna errada general al servlet
       * @throws IOException Indica que s'ha produït algun error d'entrada/sortida
       */
     @RequestMapping(value = "/altaOferta", method = RequestMethod.GET)
      public ModelAndView addOfertaRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Controlador que munta la vista d'alta d'oferta
            ModelAndView modelview = new ModelAndView("altaOferta");
            
            // Objecte buit de tipus Oferta que li passarem al formulari
            Oferta formOferta = new Oferta();
            
            // Opció perfil a la barra de navegació
            HashMap<String, String> op_perfil = new HashMap<>();
            op_perfil.put("paraula","Perfil");
            op_perfil.put("url","/perfil");


            // AQUI LI HEM DE PASSAR ELS LLISTATS PER OMPLIR ELS SELECTS QUE VINDRAN DE LA BBDD
            // PER ARA ESTÀ SIMULAT AIXÍ
            LlistaFormacions llistaFormacions = new LlistaFormacions();
            LlistaOcupacions llistaOcupacions = new LlistaOcupacions();
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici,op_perfil,op_ofertesEmpresa,op_logout};
            
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
       * Rep l'objecte de tipus Oferta de la vista d'alta d'oferta
       * Executa l'alta invocant el/s mètode/s necessari/s del servei
       * Ha de passar feedback a l'usuari
       *
       * @author Daniel Sevilla i Junyent
       * @param formOferta L'objecte de tipus Oferta que rebem del formulari
       * @return Un objecte String que conrindrà la redirecció a la home
       */
      @RequestMapping(value = "/executaAltaOferta", method = RequestMethod.POST)
      public String executaAltaOferta(@ModelAttribute("formOferta") Oferta  formOferta) {
            
            Boolean altaOfertaOK=false;
            System.out.println("--- Ja tenim la oferta a l'objecte formOferta. Fem amb ell el que faci falta.");
            
            // Invocarem els mètodes corresponents un cop fets els filtres que calguin
            
            
            // segons el resultat de l'execució del mètode...
            // ... haurem de redirigir a la vista que vulguem passant feedback a l'usuari (alta feta/alta no feta)
            return "redirect:/";
      }
      
      
      /**
       * 
       * Mostra la 'fitxa' d'informació d'una empresa, el codi de la qual rebem a la url com a pathVariable 
       * 
       * @author Daniel Sevilla i Junyent
       * @param codiEmp El codi de l'empresa les dades de la qual mostrarem a la vista
       * @param request La petició http
       * @param response La resposta http
       * @return Un objecte modelandview que representa el model i la vista que es mostrarà a l'usuari.
       */
      @RequestMapping(value = "/empresa/{codiEmpresa}", method = RequestMethod.GET)
      public ModelAndView mostraEmpresa(@PathVariable("codiEmpresa") Integer codiEmp, HttpServletRequest request, HttpServletResponse response) {
            
            ModelAndView modelview = new ModelAndView("empresa");
            
            Empresa empresa = null;
            
            if (codiEmp != null) {
                  empresa = empresaService.getEmpresaByCodi(codiEmp);
            }
            
            if ( codiEmp == null || empresa == null) {
                  modelview.setViewName("home");
                  
                  HashMap[] opcions = new HashMap[]{op_candidats,op_ofertes,op_empreses,op_logout};
                  modelview.getModelMap().addAttribute("ubicacio", "La teva web de cerca de feina");
                  modelview.getModelMap().addAttribute("missatgeFeedback", msgErrorBBDD);
                  modelview.getModelMap().addAttribute("classeFeedback", "alert-danger");
                  modelview.getModelMap().addAttribute("opcions", opcions);
                  return modelview;
            }
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici, op_candidats, op_ofertes, op_empreses, op_logout};
            
            modelview.getModelMap().addAttribute("ubicacio", "Dades de l'empresa");
            modelview.getModelMap().addAttribute("empresa", empresa);
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
      
      
      /**
       * 
       * Prepara i retorna la vista que mostra totes les empreses donades d'alta a l'aplicació
       * 
       * @author Daniel Sevilla i Junyent
       * @param request La petició http
       * @return Un objecte modelandview que representa el model i la vista que es mostrarà a l'usuari.
       */
      @RequestMapping(value = "/empreses", method = RequestMethod.GET)
      public ModelAndView mostraEmpreses(HttpServletRequest request) {
            
            ModelAndView modelview = new ModelAndView("empreses");
            List<Empresa> empreses;
            
            try {
                  empreses = empresaService.getAllEmpreses();
            } catch (Exception e) {
                  // Si la petició a la base de dades no té èxit
                  modelview.setViewName("home");
                  // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
                  HashMap[] opcions = new HashMap[]{op_logout};
                  modelview.getModelMap().addAttribute("missatgeFeedback", msgErrorBBDD);
                  modelview.getModelMap().addAttribute("classeFeedback", "alert-danger");
                  modelview.getModelMap().addAttribute("ubicacio", "La teva web de cerca de feina");
                  modelview.getModelMap().addAttribute("opcions", opcions);
                  return modelview;
            }
            
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici,op_ofertesAdmin,op_candidats,op_logout};
            
            modelview.getModelMap().addAttribute("ubicacio", "Empreses donades d'alta");
            modelview.getModelMap().addAttribute("empreses", empreses);
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
      
      
      /**
       * 
       * Prepara i retorna la vista que mostra tots els candidats donats d'alta a l'aplicació
       * 
       * @author Daniel Sevilla i Junyent
       * @param request La petició hhtp
       * @return Un objecte modelandview que representa el model i la vista que es mostrarà a l'usuari.
       */
      @RequestMapping(value = "/candidats", method = RequestMethod.GET)
      public ModelAndView mostraCandidats(HttpServletRequest request) {
            
            // Correspon a la vista que mostra el llistat de tots els candidats a l'admin
            ModelAndView modelview = new ModelAndView("candidats");
                        
            // Li passarem a la vista una llista de candidats que en realitat haurem de rebre de la bbd mitjançant el corresponent mètode del servei
            // Aquesta llista està iniciliatitzada a l'inici, i al constructor, de la classe
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici,op_empreses,op_ofertesAdmin,op_logout};
            
            modelview.getModelMap().addAttribute("ubicacio", "Llistat de tots els candidats");
            modelview.getModelMap().addAttribute("candidats", candidats);
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
      
      
      /**
       * 
       * Gestiona la petició d'eliminació d'un candidat concret, el codi de la qual rebem a la url com a PathVariable
       * Retorna un objecte modelanview amb referència a la vista candidats, que mostrarà el llistat de candidats actualitzat
       * 
       * @author Daniel Sevilla i Junyent
       * @param codiCand El codi del candidat que s'ha d'esborrar
       * @param request La petició hhtp
       * @param response La resposta http
       * @return Un objecte modelandview que representa el model i la vista que es mostrarà a l'usuari.
       */
      @RequestMapping(value = "/esborraCandidat/{codiCandidat}", method = RequestMethod.GET)
      public ModelAndView esborraCandidat(@PathVariable("codiCandidat") Integer codiCand,HttpServletRequest request, HttpServletResponse response) {
            // Gestiona l'eliminació d'un candidat
            // Aqui hem de cridar el mètode del servei que esborra el candidat i tornar true si s'ha fet l'operació o false si no s'ha completat.
            // Passarem la llista de candidats actualitzada a la vista.
            
            Boolean eliminacioOK=false;
            
            ModelAndView modelview = new ModelAndView("candidats");
            
            // Li passarem a la vista una llista de candidats que en realitat haurem de rebre de la bbd mitjançant el corresponent mètode del servei
            // Aquesta llista està iniciliatitzada a l'inici, i al constructor, de la classe
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici, op_empreses, op_ofertesAdmin, op_logout};
            
            // missatge i classe del missatge de feedback que rebrà l'usuari
            String missatgeFeedback =""; // missatge de feedback que rebrà l'usuari 
            String classeFeedback = "";  // classe CSS que s'aplicarà al contenidor del missatge de feedback
           
            if (eliminacioOK) {
                  missatgeFeedback = "Candidat eliminat correctament.";
                  classeFeedback = "alert-warning";
            } else {
                  missatgeFeedback = "Degut a un error el candidat no ha estat eliminat.";
                  classeFeedback = "alert-danger";
            }
            
            modelview.getModelMap().addAttribute("missatgeFeedback", missatgeFeedback);
            modelview.getModelMap().addAttribute("classeFeedback", classeFeedback);
            modelview.getModelMap().addAttribute("ubicacio", "Llistat de tots els candidats");
            modelview.getModelMap().addAttribute("candidats", candidats);
            modelview.getModelMap().addAttribute("opcions", opcions);
           
            return modelview;
      }
      
      /**
      * 
      * Gestiona la petició dels botons 'Tornar', que retorna una redirecció al referer (url de la qual veniem)
      * 
      * @author Daniel Sevilla i Junyent
      * @param request La petició http
      * @return Una cadena que conté una redirecció a la url anterior
      */
      @RequestMapping(value = "/enrera", method = RequestMethod.GET)
      public String anarEnrera(HttpServletRequest request) {
            // Controlador que utilitzem per tornar a la pàgina anterior 
            String referer = request.getHeader("Referer");
            
            return "redirect:"+ referer;
      }
      
      
}