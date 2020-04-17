package towork.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
public class EspaiController {
    
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
      
      
      public EspaiController() {
            // Constructor de la classe
        
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

      // Mètode provisional que afegeig una llista de candidatures
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
      
      
      @RequestMapping(value = "/espaiCandidat", method = RequestMethod.GET)
      public ModelAndView EspaiCandidatRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
            // Desem a la variable 'username' el nom de l'usuari que s'ha acreditat
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            if (authentication.getAuthorities().contains("ROLE_ADMIN")) {
            }
                    
            // Crec que a partir del nom d'usuari/email hem d'agafar el codi d'usuari de la bbdd, mitjançant el mètode del servei corresponent, per passar-lo com a paràmetre a les opcions que ho requereixin
            
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil/"+username);
        
            // Opció candidatures a la barra de navegació
            HashMap<String, String> cands = new HashMap<>();
            cands.put("paraula","Candidatures");  
            cands.put("url","/candidatures/"+username); // ÉS UNA PROVA, LI HAURIEM DE PASSAR EL CODI
            
            // Opció candidatures a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,cands,logout};  
          
            ModelAndView modelview = new ModelAndView("espaiCandidat");
            modelview.getModelMap().addAttribute("ubicacio", "Ofertes escaients per les teves dades");
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
    
      
      @RequestMapping(value = "/espaiEmpresa", method = RequestMethod.GET)
      public ModelAndView EspaiEmpresaRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ModelAndView modelview = new ModelAndView("espaiEmpresa");
            modelview.getModelMap().addAttribute("banner", "2work");
            modelview.getModelMap().addAttribute("tagline", "La teva web de cerca de feina");
            modelview.getModelMap().addAttribute("footer", "2Work Copyright 2020");
            
            return modelview;
      }

      
      @RequestMapping(value = "/espaiAdmin", method = RequestMethod.GET)
      public ModelAndView EspaiAdministradorRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ModelAndView modelview = new ModelAndView("espaiAdmin");
            modelview.getModelMap().addAttribute("banner", "2work");
            modelview.getModelMap().addAttribute("tagline", "La teva web de cerca de feina");
            modelview.getModelMap().addAttribute("footer", "2Work Copyright 2020");
            
            return modelview;
      }

      
      @RequestMapping(value = "/oferta/{codiOferta}", method = RequestMethod.GET)
      public ModelAndView OfertaPerRef(@PathVariable("codiOferta") Integer codiOferta, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");
        
            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");            
            if (authentication.getAuthorities().contains("ROLE_USER")) {
                  ofertes.put("url","/espaiCandidat"); // Fins que no la canviem aquesta és la url que porta a la vista on mostrem totes les ofertes pels candidats
            } else {
                  ofertes.put("url","/ofertesAdminEmpresa");
            }
                                                                                    
            // Opció candidatures a la barra de navegació
            HashMap<String, String> cands = new HashMap<>();
            cands.put("paraula","Candidatures");  
            cands.put("url","/candidatures?candidat='0'"); // LI HEM DE PODER PASSAR LA REFERÈNCIA A L'USUARI 
        
            // Opció candidatures a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");
            logout.put("url","/j_spring_security_logout");
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,ofertes,cands,logout};  
        
            ModelAndView modelview = new ModelAndView("oferta");
            // Oferta of = toWorkService.getOfertaByRef(ref); // En aquesta linia invocarem el mètode del servei per recuperar l'objecte oferta que després passarem a la vista
           
            // Afegeixo els atributs per passar a la vista
            modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
            modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
            modelview.getModelMap().addAttribute("opcions", opcions);
                    
            return modelview;
      }
      
      
      @RequestMapping(value = "/ofertaTornar", method = RequestMethod.GET)
      public ModelAndView OfertaPerRefTornar(@RequestParam("ref") String ref, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
            // Controlador que invoca la vista perque aquesta mostri només el botó 'Tornar'
            // Pensat per quan el candidat vulgui consultar l'oferta des del llistat de candidatures
            // Passem el referer a la vista com a atribut
          
            // Obtenim el rol de l'usuari loguejat
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String role = auth.getAuthorities().toString();
            
            System.out.println("--- Rol de l'usuari loguejat: ");
            
            
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
            }
        
            // Opció candidatures a la barra de navegació
            HashMap<String, String> candidatures = new HashMap<>();
            candidatures.put("paraula","Candidatures");  
            candidatures.put("url","/candidatures?candidat='0'"); // LI HEM DE PODER PASSAR LA REFERÈNCIA A L'USUARI 
        
            // Opció candidatures a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");
            logout.put("url","/j_spring_security_logout");
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,ofertes,candidatures,logout};  
        
            ModelAndView modelview = new ModelAndView("oferta");
            // Oferta of = toWorkService.getOfertaByRef(ref); // En aquesta linia invocarem el mètode del servei per recuperar l'objecte oferta que després passarem a la vista
            
            // Afegeixo els atributs per passar a la vista
            modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
            modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("referer", request.getHeader("Referer"));
                    
            return modelview;
      }

      
      @RequestMapping(value = "/ofertaPropietari/{codiOferta}", method = RequestMethod.GET)
      public ModelAndView OfertaPropietariPerRef(@PathVariable("codiOferta") Integer codiOferta, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            // Mostrarà l'oferta al seu propietari (de manera diferent als no propietaris, però compartirem vista)      

            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");

            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");
            ofertes.put("url","/ofertesEmpresa"); // Fins que no la canviem aquesta és la url que porta a la vista on mostrem totes les ofertes

            // Opció candidatures a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");
            logout.put("url","/j_spring_security_logout");

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,ofertes,logout};  

            ModelAndView modelview = new ModelAndView("oferta");
            // Oferta of = toWorkService.getOfertaByRef(ref); // En aquesta linia invocarem el mètode del servei per recuperar l'objecte oferta que després passarem a la vista

            // Omplo un arrayList amb les candidatures de prova per poder enviar-les al controlador
            List<Candidatura> candidatures = getListOfCandidatures();
            LlistaCandidatures llistaCandidatures = new LlistaCandidatures();
            llistaCandidatures.setLlista(candidatures);

            Candidatura formCandidatura = new Candidatura();

            ///// Final dels objectes de prova que genero per poder muntar la vista ////////////////////////////////////////////////////////////////////////////////////////////


            // Afegeixo els atributs per passar a la vista
            modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
            modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
            modelview.getModelMap().addAttribute("opcions", opcions);

            modelview.getModelMap().addAttribute("estatsPossiblesCandidatura", estatsPossibles);
            modelview.getModelMap().addAttribute("Candidatures", llistaCandidatures);
            modelview.getModelMap().addAttribute("formCandidatura", formCandidatura);

            modelview.getModelMap().addAttribute("propietari", true); // Indica a la vista que hem de mostrar la informació que només és pel propietari de l'oferta

            return modelview;
        
      }
    
        
      @RequestMapping(value = "/ofertesAdmin", method = RequestMethod.GET)
      public ModelAndView OfertesAdminRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");
            
            // Aquesta vista en principi haurà de mostrar totes les ofertes
            // Les hem d'obtenir de la bbdd mitjançant el mètode de la capa servei
            
            // Opció inici a la barra de navegació
            HashMap<String, String> inici = new HashMap<>();
            inici.put("paraula","Inici");
            inici.put("url","/");
            
            // Opció candidats a la barra de navegació
            HashMap<String, String> candidats = new HashMap<>();
            candidats.put("paraula","Candidats");
            candidats.put("url","candidats/");
            
            // Opció empreses a la barra de navegació
            HashMap<String, String> empreses = new HashMap<>();
            empreses.put("paraula","Empreses");
            empreses.put("url","empreses/");
        
            // Opció candidatures a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");
        
            // Llista que contindrà les opcions que hi haurà a la barra de navegació
            List<Map<String , String>> opcions  = new ArrayList<>();
            
            opcions.add(candidats);
            opcions.add(empreses);
            opcions.add(logout);
        
            modelview.getModelMap().addAttribute("ubicacio", "Vista general de les ofertes");
            modelview.getModelMap().addAttribute("opcions", opcions);
        
            return modelview;
      }    

      
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
            
            // Opció inici a la barra de navegació
            HashMap<String, String> inici = new HashMap<>();
            inici.put("paraula","Inici");
            inici.put("url","/");
            
            // Opció candidats a la barra de navegació
            HashMap<String, String> candidats = new HashMap<>();
            candidats.put("paraula","Candidats");
            candidats.put("url","/candidats");
            
            // Opció empreses a la barra de navegació
            HashMap<String, String> empreses = new HashMap<>();
            empreses.put("paraula","Empreses");
            empreses.put("url","/empreses");
        
            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");
            ofertes.put("url","/ofertesAdmin");
            
            // Opció candidatures a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");
            
            opcions.add(inici);
            opcions.add(candidats);
            opcions.add(empreses);
            opcions.add(ofertes);
            opcions.add(logout);
            
            modelview.getModelMap().addAttribute("ubicacio", "Vista d'ofertes per empresa");
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
      
      
      @RequestMapping(value = "/ofertesEmpresa/{codiEmpresa}", method = RequestMethod.GET)
      public ModelAndView OfertesEmpresaRequest(@PathVariable("codiEmpresa") int codiEmpresa, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ModelAndView modelview = new ModelAndView("ofertesAdminEmpresa");
            
            // En aquest cas haurem de mostrar les ofertes que pertanyen a l'empresa el codi de la qual passem com a PathVariable
            // Les hem d'obtenir de la bbdd mitjançant el mètode de la capa servei
            
            // Llista que contindrà les opcions que hi haurà a la barra de navegació
            List<Map<String , String>> opcions  = new ArrayList<>();
            
            // Opció inici a la barra de navegació
            HashMap<String, String> inici = new HashMap<>();
            inici.put("paraula","Inici");
            inici.put("url","/");
            
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            inici.put("paraula","Perfil");
            inici.put("url","/perfil");
            
            // Opció candidatures a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");
            
            opcions.add(inici);
            opcions.add(perfil);
            opcions.add(logout);
            
            modelview.getModelMap().addAttribute("ubicacio", "Ofertes generades");
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }

      @RequestMapping(value = "/candidat/{codiCandidat}", method = RequestMethod.GET)
      public ModelAndView CandidatPerCodi(@PathVariable("codiCandidat") String codiCandidat, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
            // Passem a la vista les opcions de la barra de navegacó PER USUARI EMPRESA
            // Aquesta mateixa vista l'hauria de poder veure com a mínim l'admin, probablement amb altres opcions a la barra de navegació
            // Queda pendent veure en quin moment podem passar unes o altres opcions segons el tipus d'usuari, i implementar-ho

            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");

            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");
            ofertes.put("url","/ofertesEmpresa"); // Fins que no la canviem aquesta és la url que porta a la vista on mostrem totes les ofertes

            // Opció candidatures a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,ofertes,logout};  


            ///// Inici dels objectes de prova que genero per poder muntar la vista ////////////////////////////////////////////////////////////////////////////////////////////

            // Genero habilitats de prova
            Habilitat h1 = new Habilitat();
            h1.setNomHab("habilitat1");

            Habilitat h2 = new Habilitat();
            h2.setNomHab("habilitat2");

            Habilitat h3 = new Habilitat();
            h3.setNomHab("habilitat3");

            // Omplo un arrayList amb les habilitats de prova per afegir-lo a l'objecte de prova
            ArrayList<Integer> habs = new ArrayList();
            habs.add(h1.getCodiHab());
            habs.add(h2.getCodiHab());
            habs.add(h3.getCodiHab());

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
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");
            ofertes.put("url","/espaiCandidat"); // AQUI LI HAUREM DE PASSAR A LA URL EL CODI DE CANDIDAT PERQUE ENS MOSTRI LA INFO ESCAIENTS

            // Opció logout  a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,ofertes,logout};
            
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

    
      @RequestMapping(value = "/logout", method = RequestMethod.GET)
      public ModelAndView logout(HttpServletRequest request) {
          
            
            // AQUI HEM DE CRIDAR EL MÈTODE QUE FARÀ EL LOGOUT 
            System.out.println("--- Hem entrat al mètode logout");
           
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
        
            // Opció logout  a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,candidatures,logout};  
            
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
            
            // LI HAUREM DE PASSAR AL MODEL EL CODI DEL CANDIDAT
            ModelAndView modelview = new ModelAndView("espaiCandidat");
            modelview.getModelMap().addAttribute("missatgeFeedback", missatgeFeedback);
            modelview.getModelMap().addAttribute("classeFeedback", classeFeedback);
            modelview.getModelMap().addAttribute("ubicacio", "Ofertes escaients per les teves dades");
            modelview.getModelMap().addAttribute("opcions", opcions);
           
           return modelview;
     }
     
     
     @RequestMapping(value = "/cancelarCandidatura/{dadesCancelacio}", method = RequestMethod.GET)
     public ModelAndView cancelacioCandidatura(@MatrixVariable(pathVar="dadesCancelacio") Map<String, Integer> dades){
           
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

            // Opció logout  a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,ofertes,logout};
            
            // També li haurem de passar la LLISTA AMB LES CANDIDATURES, amb l'estat en que estiguin, si no passen integrades dins l'objecte Oferta
            // Omplo un arrayList amb les candidatures de prova per poder enviar-les al controlador
            // AL TANTO, HAUREM DE FER SERVIR UN MÈTODE QUE ENS PASSI EL TÍTOL DE L'OFERTA PASSANT-LI EL CODI
            // PQ A LA TAULA HAURIA DE SORTIR EL TÍTOL DE L'OFERTA I A L'OBJECTE CANDIDATURA HI HA EL CODI
            List<Candidatura> candidatures = getListOfCandidatures();
            LlistaCandidatures llistaCandidatures = new LlistaCandidatures();
            llistaCandidatures.setLlista(candidatures);
           
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
     
     
      @RequestMapping(value = "/getHabilitats/{idOcupacio}", method = RequestMethod.GET)
      public @ResponseBody  List<Habilitat> getHabilitatsPerOcupacio(@PathVariable("idOcupacio") int idOcupacio,HttpServletRequest request, HttpServletResponse response) {
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
    
    
     @RequestMapping(value = "/altaOferta", method = RequestMethod.GET)
      public ModelAndView addOfertaRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Controlador que munta la vista d'alta d'oferta
            ModelAndView modelview = new ModelAndView("altaOferta");
            
            // Formulari d'alta oferta 
            Oferta formOferta = new Oferta();

            
            // Opció Inici a la barra de navegació
            HashMap<String, String> inici = new HashMap<>();
            inici.put("paraula","Inici");
            inici.put("url","/");
            
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");

            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");
            ofertes.put("url","/ofertesEmpresa");

            // Opció logout  a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");
            
            // AQUI LI HEM DE PASSAR ELS LLISTATS PER OMPLIR ELS SELECTS QUE VINDRAN DE LA BBDD
            // PER ARA ESTÀ SIMULAT AIXÍ
            LlistaFormacions llistaFormacions = new LlistaFormacions();
            LlistaOcupacions llistaOcupacions = new LlistaOcupacions();
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{inici,perfil,ofertes,logout};
            
            modelview.getModelMap().addAttribute("formOferta", formOferta);
            modelview.getModelMap().addAttribute("ubicacio", "Alta d'oferta");
            modelview.getModelMap().addAttribute("llistaFormacions", llistaFormacions);
            modelview.getModelMap().addAttribute("llistaOcupacions", llistaOcupacions);
            modelview.getModelMap().addAttribute("llistaTipusContracte", llistaTipusContracte);
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
      
      
      @RequestMapping(value = "/executaAltaOferta", method = RequestMethod.POST)
      public String executaAltaOferta(@ModelAttribute("formOferta") Oferta  formOferta, BindingResult result) {
            
            Boolean altaOfertaOK=false;
            System.out.println("--- Ja tenim la oferta a l'objecte formOferta. Fem amb ell el que faci falta.");
            
            // Invocarem els mètodes corresponents un cop fets els filtres que calguin
            
            
            // segons el resultat de l'execució del mètode...
            // ... haurem de redirigir a la vista que vulguem passant feedback a l'usuari (alta feta/alta no feta)
            return "redirect:/";
    }
      
      @RequestMapping(value = "/empresa/{codiEmpresa}", method = RequestMethod.GET)
      public ModelAndView mostraEmpresa(@PathVariable("codiEmpresa") int codiEmp,HttpServletRequest request, HttpServletResponse response) {
            // Controlador que munta la vista que mostra les dades de l'empresa el codi de la qual rebem com a PathVariable
            ModelAndView modelview = new ModelAndView("empresa");
            
            // Creem un objecte empresa de prova que li passem a la vista.
            // En realitat haurem de rebre aquest objecte de la bbdd mitjançant el mètode de la capa servei que invocarem aqui
            
            /////// CREACIÓ DE L'OBJECTE EMPRESA QUE PASSAREM A AL VISTA COM A PROVA
            Empresa empresaProva = new Empresa();
            empresaProva.setCodi(4);
            empresaProva.setNom("Random Industries Inc.");
            empresaProva.setDniNif("99666333K");
            empresaProva.setAdreca("Carrer de la carretara, 23");
            empresaProva.setPoblacio("L'Estany");
            empresaProva.setProvincia("Barcelona");
            empresaProva.setTelefon("+99 666 333 111");
            empresaProva.setEmail("random@industries.cat");
            empresaProva.setObservacions("Com evident experiència mostra, la debilitat de la nostra memòria, sotsmetent fàcilment a oblivió no solament los actes per longitud de temps envellits, mas encara los actes frescs de nostres dies, és estat doncs molt condecent, útil e expedient deduir en escrit les gestes e històries antigues dels homens forts e virtuosos, com sien espills molt clars, exemples e virtuosa doctrina de nostra vida, segons recita aquell gran orador Tul·li.");
            empresaProva.setResponsable("Pere Palanca i Parés");
            empresaProva.setTamany(120);
            empresaProva.setSector(4);
            empresaProva.setWeb("www.randomindustries.cat");
            //////
            
            // Opció inici a la barra de navegació
            HashMap<String, String> inici = new HashMap<>();
            inici.put("paraula","Inici");  
            inici.put("url","/");
            
            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");  
            ofertes.put("url","/ofertes");
            
            // Opció empreses a la barra de navegació
            HashMap<String, String> empreses = new HashMap<>();
            empreses.put("paraula","Empreses");  
            empreses.put("url","/empreses");
            
            // Opció candidats a la barra de navegació
            HashMap<String, String> candidats = new HashMap<>();
            candidats.put("paraula","Candidats");  
            candidats.put("url","/candidats");
            
            // Opció logout a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{inici,ofertes,empreses,candidats,logout};
            
            modelview.getModelMap().addAttribute("ubicacio", "Dades de l'empresa");
            modelview.getModelMap().addAttribute("empresa", empresaProva);
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
      
      
      @RequestMapping(value = "/empreses", method = RequestMethod.GET)
      public ModelAndView mostraEmpreses(HttpServletRequest request) {
            
            // Correspon a la vista que mostra el llistat de totes les empreses a l'admin
            ModelAndView modelview = new ModelAndView("empreses");
            
             // Opció inici a la barra de navegació
            HashMap<String, String> inici = new HashMap<>();
            inici.put("paraula","Inici");  
            inici.put("url","/");
            
            // Opció candidats  a la barra de navegació
            HashMap<String, String> candidats = new HashMap<>();
            candidats.put("paraula","Candidats");  
            candidats.put("url","/candidats");
            
            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");  
            ofertes.put("url","/ofertesAdmin");
            
            // Opció logout  a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{inici,ofertes,candidats,logout};
            
            modelview.getModelMap().addAttribute("ubicacio", "Empreses donades d'alta");
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
      
      
      @RequestMapping(value = "/candidats", method = RequestMethod.GET)
      public ModelAndView mostraCandidats(HttpServletRequest request) {
            
            // Correspon a la vista que mostra el llistat de tots els candidats a l'admin
            ModelAndView modelview = new ModelAndView("candidats");
                        
            // Li passarem a la vista una llista de candidats que en realitat haurem de rebre de la bbd mitjançant el corresponent mètode del servei
            // Aquesta llista està iniciliatitzada a l'inici, i al constructor, de la classe
            
            // Opció inici a la barra de navegació
            HashMap<String, String> inici = new HashMap<>();
            inici.put("paraula","Inici");  
            inici.put("url","/");
            
            // Opció empreses  a la barra de navegació
            HashMap<String, String> empreses = new HashMap<>();
            empreses.put("paraula","Empreses");  
            empreses.put("url","/empreses");
            
            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");  
            ofertes.put("url","/ofertesAdmin");
            
            // Opció logout  a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{inici,empreses,ofertes,logout};
            
            modelview.getModelMap().addAttribute("ubicacio", "Llistat de tots els candidats");
            modelview.getModelMap().addAttribute("candidats", candidats);
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
      }
      
      
      @RequestMapping(value = "/esborraCandidat/{codiCandidat}", method = RequestMethod.GET)
      public ModelAndView esborraCandidat(@PathVariable("codiCandidat") int codiCand,HttpServletRequest request, HttpServletResponse response) {
            // Gestiona l'eliminació d'un candidat
            // Aqui hem de cridar el mètode del servei que esborra el candidat i tornar true si s'ha fet l'operació o false si no s'ha completat.
            // Passarem la llista de candidats actualitzada a la vista.
            
            Boolean eliminacioOK=false;
            
            ModelAndView modelview = new ModelAndView("candidats");
            
            // Li passarem a la vista una llista de candidats que en realitat haurem de rebre de la bbd mitjançant el corresponent mètode del servei
            // Aquesta llista està iniciliatitzada a l'inici, i al constructor, de la classe
            
            // Opció inici a la barra de navegació
            HashMap<String, String> inici = new HashMap<>();
            inici.put("paraula","Inici");  
            inici.put("url","/");
            
            // Opció empreses  a la barra de navegació
            HashMap<String, String> empreses = new HashMap<>();
            empreses.put("paraula","Empreses");  
            empreses.put("url","/empreses");
            
            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");  
            ofertes.put("url","/ofertesAdmin");
            
            // Opció logout  a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");  
            logout.put("url","/j_spring_security_logout");
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{inici,empreses,ofertes,logout};
            
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
      
      
      @RequestMapping(value = "/enrera", method = RequestMethod.GET)
      public String anarEnrera(HttpServletRequest request) {
            // Controlador que utilitzem per tornar a la pàgina anterior 
            String referer = request.getHeader("Referer");            
            return "redirect:"+ referer;
      }
      
      
       
}
