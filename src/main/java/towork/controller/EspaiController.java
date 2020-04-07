
package towork.controller;

import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import towork.domain.Candidat;
import towork.domain.Candidatura;
import towork.domain.Formacio;
import towork.domain.Habilitat;
import towork.domain.LlistaCandidatures;
import towork.domain.Oferta;

@Controller
public class EspaiController {
    
      // Crear el controller dels espais dls 3 usuaris del sistema,candidat, empresa i administrador
    
      @RequestMapping(value = "/espaiCandidat", method = RequestMethod.GET)
      public ModelAndView EspaiCandidatRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");
        
            // Opció candidatures a la barra de navegació
            HashMap<String, String> candidatures = new HashMap<>();
            candidatures.put("paraula","Candidatures");  
            candidatures.put("url","/candidatures?candidat='0'"); // LI HEM DE PODER PASSAR LA REFERÈNCIA A L'USUARI 
        
            // Opció logout a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");
            logout.put("url","/logout");
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,candidatures,logout};  
          
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

      
      @RequestMapping(value = "/oferta", method = RequestMethod.GET)
      public ModelAndView OfertaPerRef(@RequestParam("ref") String ref, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
            // Opció perfil a la barra de navegació
            HashMap<String, String> perfil = new HashMap<>();
            perfil.put("paraula","Perfil");
            perfil.put("url","/perfil");
        
            // Opció ofertes a la barra de navegació
            HashMap<String, String> ofertes = new HashMap<>();
            ofertes.put("paraula","Ofertes");
            ofertes.put("url","/espaiCandidat"); // Fins que no la canviem aquesta és la url que porta a la vista on mostrem totes les ofertes
        
            // Opció candidatures a la barra de navegació
            HashMap<String, String> candidatures = new HashMap<>();
            candidatures.put("paraula","Candidatures");  
            candidatures.put("url","/candidatures?candidat='0'"); // LI HEM DE PODER PASSAR LA REFERÈNCIA A L'USUARI 
        
            // Opció logout a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");
            logout.put("url","/logout");
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,ofertes,candidatures,logout};  
        
            ModelAndView modelview = new ModelAndView("oferta");
            // Oferta of = toWorkService.getOfertaByRef(ref); // En aquesta linia invocarem el mètode del servei per recuperar l'objecte oferta que després passarem a la vista
        
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // Genero habilitats de prova
            Habilitat h1 = new Habilitat();
            h1.setNomHab("habilitat1");
        
            Habilitat h2 = new Habilitat();
            h2.setNomHab("habilitat2");
        
            Habilitat h3 = new Habilitat();
            h3.setNomHab("habilitat3");
        
        
            // Omplo un arrayList amb les habilitats de prova per afegir-lo a l'objecte de prova
            ArrayList<Habilitat> habs = new ArrayList();
            habs.add(h1);
            habs.add(h2);
            habs.add(h3);
        
            // Genero un objecte Oferta DE PROVA mentre no implementem la línia anterior
            Oferta of = new Oferta();
            of.setDescripcio("Aquesta és la descripció de l'oferta blablabla. Aquest text en principi ha podria ser una mica llarg. És l'únic camp que permet explicar lliurement segons quins detalls de l'oferta. Com, per exemple, que pretenen explotar el treballador o bé pagar-li amb hortalisses o objectes d'escriptori usats.");
            of.setEstat("Pendent");
            of.setFormacio("Formació que requereix aquesta oferta");
            of.setHabilitats(habs);
            of.setTorn("De 9 a 15h"); // Això crec està pendent d'acabar de definir bé al domini.
            of.setLocalitat("Cardona");
            of.setNifEmpresa("22333444K"); // Aquesta dada haurà de servir de PK per extreure el nom de l'empresa de la bbdd? Mostrarem el nom i no el 
            of.setSou(25000d);
            of.setTipusContracte("Indefinit");
            of.setTitolOferta("Títol de l'oferta");
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
            // Afegeixo els atributs per passar a la vista
            modelview.getModelMap().addAttribute("ubicacio", "Detall de l'oferta");
            modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que agafem de la bbdd.
            modelview.getModelMap().addAttribute("opcions", opcions);
                    
            return modelview;
      }

      
        @RequestMapping(value = "/ofertaPropietari", method = RequestMethod.GET)
        public ModelAndView OfertaPropietariPerRef(@RequestParam("ref") String ref, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Mostrarà l'oferta al seu propietari (de manera diferent als no propietaris, però compartirem vista)      
              
        // Opció perfil a la barra de navegació
        HashMap<String, String> perfil = new HashMap<>();
        perfil.put("paraula","Perfil");
        perfil.put("url","/perfil");
        
        // Opció ofertes a la barra de navegació
        HashMap<String, String> ofertes = new HashMap<>();
        ofertes.put("paraula","Ofertes");
        ofertes.put("url","/espaiCandidat"); // Fins que no la canviem aquesta és la url que porta a la vista on mostrem totes les ofertes
            
        // Opció logout a la barra de navegació
        HashMap<String, String> logout = new HashMap<>();
        logout.put("paraula","Logout");
        logout.put("url","/logout");
        
        // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
        HashMap[] opcions = new HashMap[]{perfil,ofertes,logout};  
        
        ModelAndView modelview = new ModelAndView("oferta");
        // Oferta of = toWorkService.getOfertaByRef(ref); // En aquesta linia invocarem el mètode del servei per recuperar l'objecte oferta que després passarem a la vista
        
        
        ///// Inici dels objectes de prova que genero per poder muntar la vista ////////////////////////////////////////////////////////////////////////////////////////////
        
        // Genero habilitats de prova
        Habilitat h1 = new Habilitat();
        h1.setNomHab("habilitat1");
        
        Habilitat h2 = new Habilitat();
        h2.setNomHab("habilitat2");
        
        Habilitat h3 = new Habilitat();
        h3.setNomHab("habilitat3");
        
        
        // Omplo un arrayList amb les habilitats de prova per afegir-lo a l'objecte de prova
        ArrayList<Habilitat> habs = new ArrayList();
        habs.add(h1);
        habs.add(h2);
        habs.add(h3);
        
        // Genero un objecte Oferta DE PROVA mentre no fem el getOfertaPerRef, o com sigui
        Oferta of = new Oferta();
        of.setDescripcio("Aquesta és la descripció de l'oferta blablabla. Aquest text en principi podria ser una mica llarg. És l'únic camp que permet explicar lliurement segons quins detalls de l'oferta. Com, per exemple, que pretenen explotar el treballador o bé pagar-li amb hortalisses o objectes d'escriptori usats.");
        of.setEstat("Pendent");
        of.setFormacio("Formació que requereix aquesta oferta");
        of.setHabilitats(habs);
        of.setTorn("De 9 a 15h"); // Això crec està pendent d'acabar de definir bé al domini.
        of.setLocalitat("Cardona");
        of.setNifEmpresa("22333444K"); // Aquesta dada haurà de servir de PK per extreure el nom de l'empresa de la bbdd? Mostrarem el nom i no el 
        of.setSou(25000d);
        of.setTipusContracte("Indefinit");
        of.setTitolOferta("Títol de l'oferta");
        
    
        // Hashmap que emmagatzema els POSSIBLES ESTATS QUE PODEN  TENIR LES CANDIDATURES (per poder fer la prova). Probablement no és el millor lloc on tenir aquest llistat.
        HashMap<Integer, String> estatsPossibles = new HashMap<>();
        estatsPossibles.put(0,"Pendent revisar");
        estatsPossibles.put(1,"Denegada"); 
        estatsPossibles.put(2,"Aprovada");
     
        
        // També li haurem de passar la LLISTA AMB LES CANDIDATURES, amb l'estat en que estiguin, si no passen integrades dins l'objecte Oferta
        // Omplo un arrayList amb les candidatures de prova per poder enviar-les al controlador
        List<Candidatura> candidatures = getListOfCandidatures();
        LlistaCandidatures llistaCandidatures = new LlistaCandidatures();
        llistaCandidatures.setLlistat(candidatures);
        
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
    
        
    @RequestMapping(value = "/ofertesEmpresa", method = RequestMethod.GET)
    public ModelAndView OfertesEmpresaRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
        // Opció perfil a la barra de navegació
        HashMap<String, String> perfil = new HashMap<>();
        perfil.put("paraula","Perfil");
        perfil.put("url","/perfil");
        
        // Opció logout a la barra de navegació
        HashMap<String, String> logout = new HashMap<>();
        logout.put("paraula","Logout");
        logout.put("url","/logout");
        
        // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
        HashMap[] opcions = new HashMap[]{perfil,logout};  

        
        ModelAndView modelview = new ModelAndView("ofertesEmpresa");
        modelview.getModelMap().addAttribute("ubicacio", "Ofertes generades");
        modelview.getModelMap().addAttribute("opcions", opcions);
        
        return modelview;
    }    


      @RequestMapping(value = "/candidat", method = RequestMethod.GET)
      public ModelAndView CandidatPerCodi(@RequestParam("codi") String ref, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
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

            // Opció logout a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");
            logout.put("url","/logout");

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
            ArrayList<Habilitat> habs = new ArrayList();
            habs.add(h1);
            habs.add(h2);
            habs.add(h3);

            // Genero formacions de prova (ara no sé si només en pot haver una)
            Formacio f1 = new Formacio();
            f1.setNomFormPostUni("Nom de la formació1");

            Formacio f2 = new Formacio();
            f2.setNomFormPostUni("Nom de la formació2");

            // Omplo un arrayList amb les formacions de prova per afegir-lo a l'objecte de prova
            ArrayList<Formacio> formacions = new ArrayList();
            formacions.add(f1);
            formacions.add(f2);

            // Genero un objecte Candidat DE PROVA mentre no fem el getCandidatPerCodi, o com sigui
            // Només hi poso les dades que han de sortir a la vista
            Candidat cand = new Candidat();
            cand.setCodiFormacio("codiFormació");
            cand.setEmail("e@mail.cat");
            cand.setTelefon("969996633");
            cand.setHabilitats(habs);
            cand.setFormacions(formacions); // Vam quedar amb una sola formació possible o amb un array de formacions?

            ModelAndView modelview = new ModelAndView("candidat");
            modelview.getModelMap().addAttribute("ubicacio", "Detall del candidat");
            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("candidat", cand);
            modelview.getModelMap().addAttribute("referer", request.getHeader("Referer"));

            return modelview;
      }
    
 
      @RequestMapping(value = "/candidatures", method = RequestMethod.GET)
      public ModelAndView CandidaturesPerCandidat(@RequestParam("candidat") String codiCandidat, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
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
            ofertes.put("url","/ofertes?codi="+codiCandidat);

            // Opció logout a la barra de navegació
            HashMap<String, String> logout = new HashMap<>();
            logout.put("paraula","Logout");
            logout.put("url","/logout");

            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{perfil,ofertes,logout};
            
            // També li haurem de passar la LLISTA AMB LES CANDIDATURES, amb l'estat en que estiguin, si no passen integrades dins l'objecte Oferta
            // Omplo un arrayList amb les candidatures de prova per poder enviar-les al controlador
            // AL TANTO, HAUREM DE FER SERVIR UN MÈTODE QUE ENS PASSI EL TÍTOL DE L'OFERTA PASSANT-LI EL CODI
            // PQ A LA TAULA HAURIA DE SORTIR EL TÍTOL DE L'OFERTA I A L'OBJECTE CANDIDATURA HI HA EL CODI
            List<Candidatura> candidatures = getListOfCandidatures();
            LlistaCandidatures llistaCandidatures = new LlistaCandidatures();
            llistaCandidatures.setLlistat(candidatures);

            
            ModelAndView modelview = new ModelAndView("candidatures");
            modelview.getModelMap().addAttribute("ubicacio", "Les meves candidatures");
            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("candidatures", candidatures);

            return modelview;
    }

    
      @RequestMapping(value = "/logout", method = RequestMethod.GET)
      public ModelAndView logout(HttpServletRequest request) {
          
            // AQUI HEM DE CRIDAR EL MÈTODE QUE FARÀ EL LOGOUT
          
            // Creem les opcions que aniràn a la barra de navegació
            
            // Opció Administrador a la barra de navegació
            HashMap<String, String> admin = new HashMap<>();
            admin.put("paraula","Administrador");
            admin.put("url","/loginAdmin");
        
            // Opció Candidat a la barra de navegació
            HashMap<String, String> candidat = new HashMap<>();
            candidat.put("paraula","Candidat");
            candidat.put("url","/loginCandidat");
        
            // Opció Empresa a la barra de navegació
            HashMap<String, String> empresa = new HashMap<>();
            empresa.put("paraula","Empresa");
            empresa.put("url","/loginEmpresa");
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{admin,candidat,empresa};  
          
            ModelAndView modelview = new ModelAndView("home");
            modelview.getModelMap().addAttribute("opcions", opcions);
            modelview.getModelMap().addAttribute("missatgeAlerta", "Has sortit de l'aplicació. Fins aviat!");
            
            return modelview;
    }
    
      
    @RequestMapping(value = "/enrera", method = RequestMethod.GET)
     public String anarEnrera(HttpServletRequest request) {
            // Controlador que utilitzem per tornar a la pàgina anterior 
            String referer = request.getHeader("Referer");            
            return "redirect:"+ referer;
    }
  
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    // Mètode que afegeig una llista de candidatures
    private List<Candidatura> getListOfCandidatures() {
      List<Candidatura> cands = new ArrayList<>();
      cands.add(new Candidatura("codiCandidatura1", "dniCandidat1", "codiOferta1", 2));
      cands.add(new Candidatura("codiCandidatura2", "dniCandidat2", "codiOferta2", 2));
      cands.add(new Candidatura("codiCandidatura3", "dniCandidat3", "codiOferta3", 1));
      cands.add(new Candidatura("codiCandidatura4", "dniCandidat4", "codiOferta4", 0));
      return cands;
   }
    
}
