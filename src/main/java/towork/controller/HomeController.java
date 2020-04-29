/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.controller;

import java.io.IOException;
import java.sql.Date;
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
import towork.formularis.CandidatFormulari;
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
      HashMap<String, String> op_baixaEmpresa = new HashMap<>();
      HashMap<String, String> op_perfilCandidat = new HashMap<>();
      HashMap<String, String> op_baixaCandidat = new HashMap<>();
      HashMap<String, String> op_ofertesEmpresa = new HashMap<>();
      HashMap<String, String> op_candidats = new HashMap<>();
      HashMap<String, String> op_empreses = new HashMap<>();
      HashMap<String, String> op_ofertesAdmin = new HashMap<>();
      HashMap<String, String> op_altaOferta = new HashMap<>();
      
      // Missatges reutilitzables
      final String msgErrorBBDD = "Hi ha hagut algun problema rebent les dades de la base de dades.";
      final String baseline = "La teva web de cerca de feina";
      
      
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
            op_baixaEmpresa.put("url","/eliminaEmpresa");
            op_baixaEmpresa.put("paraula","Baixa");
            op_perfilCandidat.put("url","/perfilCandidat");
            op_perfilCandidat.put("paraula","Perfil");
            op_baixaCandidat.put("url","/eliminaCandidat");
            op_baixaCandidat.put("paraula","Baixa");
            op_ofertesEmpresa.put("paraula","Ofertes");
            op_ofertesEmpresa.put("url","/ofertesEmpresa"); // Fins que no la canviem aquesta és la url que porta a la vista on mostrem totes les ofertes
            op_ofertesAdmin.put("paraula","Ofertes");
            op_ofertesAdmin.put("url","ofertesAdmin");
            op_altaOferta.put("paraula","Crear oferta");
            op_altaOferta.put("url","/altaOferta");

      }
      
      
      @RequestMapping(value = "/", method = RequestMethod.GET)
      public ModelAndView home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
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
                        try {
                              
                              // PENDENT DE TENIR EL MÈTODE OK
                              //Integer codiCandidat = candidatService.
                              Integer codiCandidat=1; // De prova mentre no podem recuperar el candidat de la bbdd
                              
                              op_ofertesCandidat.put("usuari","/"+codiCandidat);
                              op_candidatures.put("usuari","/"+codiCandidat);
                              op_perfilCandidat.put("usuari","/"+codiCandidat);
                              op_baixaCandidat.put("usuari","/"+codiCandidat);
                              
                              opcions.add(op_ofertesCandidat);
                              opcions.add(op_candidatures);
                              opcions.add(op_perfilCandidat);
                              opcions.add(op_baixaCandidat);
                              opcions.add(op_logout);
                        } catch (Exception e){
                              // SI no podem recuperar de la bbdd el codi del candidat
                              opcions.add(op_inici);
                              opcions.add(op_logout);
                              modelview.getModelMap().addAttribute("missatgeFeedback", msgErrorBBDD);
                              modelview.getModelMap().addAttribute("classeFeedback", "alert-danger");
                        }                              
                        break;
                        
                  case "ROLE_EMPRESA":
                        // Hi ha un usuari empresa empresa loguejat
                        try {
                              Integer codiEmpresa = empresaService.getCodiByEmail(nom);
                              op_altaOferta.put("usuari","/"+codiEmpresa);
                              op_ofertesEmpresa.put("usuari","/"+codiEmpresa);
                              op_perfilEmpresa.put("usuari","/"+codiEmpresa);
                        } catch (Exception e){
                        }
                              opcions.add(op_altaOferta);
                              opcions.add(op_ofertesEmpresa);
                              opcions.add(op_perfilEmpresa);
                              opcions.add(op_baixaEmpresa);
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
      

      /**
       * 
       * @param request
       * @param response
       * @return
       * @throws ServletException
       * @throws IOException 
       */
      @RequestMapping(value = "/altaCandidat", method = RequestMethod.GET)
      public ModelAndView altaCandidat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ModelAndView modelview = new ModelAndView("altaCandidat");
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{op_inici};
            
            // Formulari d'alta candidat 
            CandidatFormulari formCandidat = new CandidatFormulari();
            
            
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
      
      
      /**
       * 
       * @param formCandidat
       * @param result
       * @return 
       */
      @RequestMapping(value = "/executaAltaCandidat", method = RequestMethod.POST)
      public String executaAltaCandidat(@ModelAttribute("formCandidat") CandidatFormulari formCandidat, BindingResult result) {
            
            System.out.println("--- Ja tenim el candidat a l'objecte formCandidat. Fem amb ell el que faci falta.");
            System.out.println("--- Data rebuda: "+formCandidat.getDataNaix());
            /*for (Experiencia exp : formCandidat.getExperiencies()) {
                  System.out.println("--- Experiencia: "+exp.getDescripcio());
            }
            System.out.println("--- Habilitats: "+formCandidat.getHabilitats());*/
            // System.out.println("--- Propietat d'una de les experiències no omplertes: "+formCandidat.getExperiencies());
            
            // Invocarem els mètodes corresponents un cop fets els filtres que calguin
            try {
                  candidatService.addCandidat(aCandidat(formCandidat));
            } catch(Exception e) {
                  System.out.println("--- No s'ha fet l'alta del candidat");
            }
              
            // service.addExperiencies
            //....
            
            
            // segons el resultat de l'execució del mètode...
            // ... haurem de redirigir a la vista que vulguem passant feedback a l'usuari (alta feta/alta no feta)
            return "redirect:/";
    }
      
      
      @RequestMapping(value = "/altaEmpresa", method = RequestMethod.GET)
      public ModelAndView altaEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
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
    public ModelAndView info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("info");
        return modelview;
    }
    
    @RequestMapping(value = "/notFound", method = RequestMethod.GET)
    public ModelAndView notFound(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("notFound");
        return modelview;
    }
    
/**
 * 
 * Converteix un item de candidat, de tipus CandidatFormulari (el que fa servir el formulari) a tipus Candidat, apte per desar a la base de dades
 * Diferències implementades:
 *    La data de naixement, a la classe CandidatFormulari, és de tipus LocalDate / per desar a la bbdd ha de ser de tipus java.sql.Date
 * 
 * @author Daniel Sevilla i Junyent
 * @param c El candidat, de tipus CandidatFormulari, que li passem
 * @return L'objecte convertit a objecte de la classe Candidat
 */
    public Candidat aCandidat(CandidatFormulari c){
          
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
