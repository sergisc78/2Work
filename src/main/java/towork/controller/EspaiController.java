
package towork.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import towork.domain.Habilitat;
import towork.domain.Oferta;

@Controller
public class EspaiController {
    
    // Crear el controller dels espais dls 3 usuaris del sistema,candidat, empresa i administrador
    
    @RequestMapping(value = "/espaiCandidat", method = RequestMethod.GET)
    public ModelAndView EspaiCandidatRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("espaiCandidat");
        modelview.getModelMap().addAttribute("ubicacio", "Ofertes escaients per les teves dades");
        return modelview;
    }
    @RequestMapping(value = "/espaiEmpresa", method = RequestMethod.GET)
    public ModelAndView EspaiEmpresaRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("espaiEmpresa");
        modelview.getModelMap().addAttribute("banner", "2work");
        modelview.getModelMap().addAttribute("tagline", "La teva web de recerca de feina");
        modelview.getModelMap().addAttribute("footer", "2Work Copyright 2020");
        return modelview;
    }
    @RequestMapping(value = "/espaiAdmin", method = RequestMethod.GET)
    public ModelAndView EspaiAdministradorRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("espaiAdmin");
        modelview.getModelMap().addAttribute("banner", "2work");
        modelview.getModelMap().addAttribute("tagline", "La teva web de recerca de feina");
        modelview.getModelMap().addAttribute("footer", "2Work Copyright 2020");
        return modelview;
    }
    
    @RequestMapping(value = "/oferta", method = RequestMethod.GET)
    public ModelAndView OfertaPerRef(@RequestParam("ref") String ref,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
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
        candidatures.put("url","/candidatures");
        
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
        h1.setNomHab("habilitat2");
        
        Habilitat h3 = new Habilitat();
        h1.setNomHab("habilitat3");
        
        
        // Omplo un arrayList amb les habilitats de prova per afegir-lo a l'objecte de prova
        ArrayList<Habilitat> habs = new ArrayList();
        habs.add(h1);
        habs.add(h2);
        habs.add(h3);
        
        // Genero un objecte Oferta DE PROVA mentre no implementem la línia anterior
        Oferta of = new Oferta();
        of.setDescripcio("Aquesta és la descripció de l'oferta blablabla");
        of.setEstat("Pendent");
        of.setFormacio("Formació que requereix aquesta oferta");
        of.setHabilitats(habs);
        of.setTorn("De 9 a 15h"); // Això està pendent d'acabar de definir al domini.
        of.setLocalitat("Cardona");
        of.setNifEmpresa("22333444K"); // Aquesta dada haurà de servir de PK per extreure el nom de l'empresa de la bbdd?
        of.setSou(25000d);
        of.setTipusContracte("Indefinit");
        of.setTitolOferta("Títol de l'oferta");
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
        // Afegeixo els atributs per passar a la vista
        modelview.getModelMap().addAttribute("ubicacio", "Detalls de l'oferta");
        modelview.getModelMap().addAttribute("oferta", of); // Passem a la vista l'oferta de prova. Haurà de ser la que afagem de la bbdd.
        modelview.getModelMap().addAttribute("opcions", opcions);
        
        return modelview;
    }
    
}
