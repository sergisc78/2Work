/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import towork.domain.Candidat;
import towork.domain.Empresa;
import towork.formularis.LlistaFormacions;
import towork.formularis.LlistaOcupacions;
import towork.service.EmpresaService;
import towork.formularis.LlistaSectors;
import towork.service.CandidatService;


/**
 *
 * @author SERGI
 */
@Controller
public class HomeController {
             
      @Autowired
      EmpresaService empresaService;
      @Autowired
      CandidatService candidatService;
      
      // Opcions reutilitzables per la barra de navegació
      HashMap<String, String> op_entrar_candidat = new HashMap<>();
      HashMap<String, String> op_entrar_empresa = new HashMap<>();
      HashMap<String, String> op_inici = new HashMap<>();
      HashMap<String, String> op_logout = new HashMap<>();
      HashMap<String, String> op_ofertesCandidat = new HashMap<>();
      HashMap<String, String> op_candidatures = new HashMap<>();
      HashMap<String, String> op_perfil = new HashMap<>();
      HashMap<String, String> op_perfilEmpresa = new HashMap<>();
      HashMap<String, String> op_ofertesEmpresa = new HashMap<>();
      HashMap<String, String> op_candidats = new HashMap<>();
      HashMap<String, String> op_empreses = new HashMap<>();
      HashMap<String, String> op_ofertesAdmin = new HashMap<>();
      HashMap<String, String> op_altaOferta = new HashMap<>();
      
      
      /**
      * 
      * Constructor del controlador, sense paràmetres
      * 
      * @author Daniel Sevilla i Junyent
      * 
      */
      public HomeController() {
            
            // Opcions reutilitzables per la barra de navegació
            op_entrar_candidat.put("paraula","Candidat");
            op_entrar_candidat.put("url","/espaiCandidat");
            op_entrar_empresa.put("paraula","Empresa");
            op_entrar_empresa.put("url","/espaiEmpresa");
            op_inici.put("paraula","Inici");
            op_inici.put("url","/");
            op_ofertesCandidat.put("paraula","Ofertes");
            op_ofertesCandidat.put("url","/espaiCandidat");
            op_candidatures.put("paraula","Candidatures");  
            op_candidatures.put("url","/candidatures");
            op_candidats.put("paraula","Candidats");  
            op_candidats.put("url","/candidats");
            op_empreses.put("paraula","Empreses");  
            op_empreses.put("url","/empreses");
            op_logout.put("paraula","Logout");
            op_logout.put("url","/j_spring_security_logout");
            op_perfil.put("paraula","Perfil");
            op_perfil.put("url","/perfil");
            op_perfilEmpresa.put("url","/perfilEmpresa");
            op_perfilEmpresa.put("paraula","Perfil");
            op_ofertesEmpresa.put("paraula","Ofertes");
            op_ofertesEmpresa.put("url","/ofertesEmpresa"); // Fins que no la canviem aquesta és la url que porta a la vista on mostrem totes les ofertes
            op_ofertesAdmin.put("paraula","Ofertes");
            op_ofertesAdmin.put("url","ofertesAdmin");
            op_altaOferta.put("paraula","Crear oferta");
            op_altaOferta.put("url","/altaOferta");

      }
      
      
      @RequestMapping(value = "/", method = RequestMethod.GET)
      public ModelAndView homeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
            // Obtenim el rol de l'usuari loguejat
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String role = auth.getAuthorities().iterator().next().toString(); // El primer element dela collection auth.getAuthoritie. Assumim que només conté un element.
            System.out.println(role);
            String nom = auth.getName();
            
            // Creem el modelview
            ModelAndView modelview = new ModelAndView("home");
            
            // Creem les opcions que aniràn a la barra de navegació
            List<Map<String, String>> opcions = new ArrayList<>();
            
            // Afegim les opcions a l'array          
            switch(role){
                  case "ROLE_ANONYMOUS":
                        // No hi ha cap usuari loguejat
                        opcions.add(op_entrar_candidat);
                        opcions.add(op_entrar_empresa);
                        break;
                  case "ROLE_USER":
                        // HI ha un usuari candidat loguejat
                        opcions.add(op_ofertesCandidat);
                        opcions.add(op_candidatures);
                        opcions.add(op_perfil);
                        opcions.add(op_logout);
                        break;
                  case "ROLE_EMPRESA":
                        // Hi ha un usuari empresa empresa loguejat
                        try {
                              Integer codiEmpresa = empresaService.getCodiByEmail(nom);
                              op_altaOferta.put("usuari","/"+codiEmpresa);
                              op_ofertesEmpresa.put("usuari","/"+codiEmpresa);
                              op_perfilEmpresa.put("usuari","/"+codiEmpresa);
                        } catch (Exception e){}
                        opcions.add(op_altaOferta);
                        opcions.add(op_ofertesEmpresa);
                        opcions.add(op_perfilEmpresa);
                        opcions.add(op_logout);
                        break;
                  case "ROLE_ADMIN":
                        opcions.add(op_candidats);
                        opcions.add(op_empreses);
                        opcions.add(op_ofertesAdmin);
                        opcions.add(op_logout);
                        break;
            }
                  
            modelview.getModelMap().addAttribute("ubicacio", "La teva web de cerca de feina");
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
      }

      
      @RequestMapping(value = "/altaCandidat", method = RequestMethod.GET)
      public ModelAndView addCandidatRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ModelAndView modelview = new ModelAndView("altaCandidat");
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici};
            
            // Formulari d'alta candidat 
            Candidat formCandidat = new Candidat();
            
            
            // AQUI LI HEM DE PASSAR ELS LLISTATS PER OMPLIR ELS SELECTS QUE VINDRAN DE LA BBDD
            // PER ARA ESTÀ SIMULAT AIXÍ
            LlistaFormacions llistaFormacions = new LlistaFormacions();
            LlistaOcupacions llistaOcupacions = new LlistaOcupacions();
            
            modelview.getModelMap().addAttribute("formCandidat", formCandidat);
            modelview.getModelMap().addAttribute("ubicacio", "Alta de candidat");
            modelview.getModelMap().addAttribute("llistaFormacions", llistaFormacions);
            modelview.getModelMap().addAttribute("llistaOcupacions", llistaOcupacions);
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
      }
      
    
      @RequestMapping(value = "/executaAltaCandidat", method = RequestMethod.POST)
      public String executaAltaCandidat(@ModelAttribute("formCandidat") Candidat formCandidat, BindingResult result) {
            
            System.out.println("--- Ja tenim el candidat a l'objecte formCandidat. Fem amb ell el que faci falta.");
            /*for (Experiencia exp : formCandidat.getExperiencies()) {
                  System.out.println("--- Experiencia: "+exp.getDescripcio());
            }
            System.out.println("--- Habilitats: "+formCandidat.getHabilitats());*/
            // System.out.println("--- Propietat d'una de les experiències no omplertes: "+formCandidat.getExperiencies());
            
            // Invocarem els mètodes corresponents un cop fets els filtres que calguin
              candidatService.addCandidat(formCandidat);
            // service.addExperiencies
            //....
            
            
            // segons el resultat de l'execució del mètode...
            // ... haurem de redirigir a la vista que vulguem passant feedback a l'usuari (alta feta/alta no feta)
            return "redirect:/";
    }
      
      
      @RequestMapping(value = "/altaEmpresa", method = RequestMethod.GET)
      public ModelAndView addempresaRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
            // Invoca la vista que mostra el formulari d'alta d'empresa
            ModelAndView modelview = new ModelAndView("altaEmpresa");     
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici};
            
            //Formulari d'alta empresa 
            Empresa formEmpresa = new Empresa();
            
            LlistaSectors llistaSectors = new LlistaSectors();
        
            modelview.getModelMap().addAttribute("formEmpresa", formEmpresa);
            modelview.getModelMap().addAttribute("ubicacio", "Alta d'empresa");
            modelview.getModelMap().addAttribute("llistaSectors", llistaSectors);
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
    }
    
      /**
       * 
       * Prova d'executar l'alta de l'empresa que rebem de la vista 
       * Passa feedback a l'usuari
       * 
       * @author Daniel Sevilla i Junyent
       * @param formEmpresa L'objecte de tipus Empresa que rebem del formulari d'alta d'empresa
       * @return Un objecte modelandview que invoca la vista home amb un missatge de feedback per l'usuari.
       */
      @RequestMapping(value = "/executaAltaEmpresa", method = RequestMethod.POST)
      public ModelAndView executaAltaEmpresa(@ModelAttribute("formEmpresa") Empresa formEmpresa) {
            ModelAndView modelview = new ModelAndView("home");
            
            String missatgeFeedback;
            String classeFeedback;
            
            try {
                  empresaService.addEmpresa(formEmpresa);
                  missatgeFeedback = "L'alta s'ha realitzat amb èxit :)";
                  classeFeedback = "alert-warning";
            } catch (Exception e) {
                  missatgeFeedback = "L'alta no s'ha pogut realitzar :(";
                  classeFeedback = "alert-danger";
            }
            
            HashMap[] opcions = new HashMap[]{op_entrar_candidat, op_entrar_empresa};
            modelview.getModelMap().addAttribute("missatgeFeedback", missatgeFeedback);
            modelview.getModelMap().addAttribute("classeFeedback", classeFeedback);
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
    }
      
     
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView infoRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("info");
        return modelview;
    }
    
    @RequestMapping(value = "/notFound", method = RequestMethod.GET)
    public ModelAndView notFoundRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("notFound");
        return modelview;
    }
    

}
