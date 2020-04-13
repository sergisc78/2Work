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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import towork.domain.Candidat;
import towork.domain.Empresa;
import towork.domain.Experiencia;
import towork.formularis.LlistaFormacions;
import towork.formularis.LlistaOcupacions;
import towork.service.EmpresaService;
import towork.formularis.LlistaSectors;


/**
 *
 * @author SERGI
 */
@Controller
public class HomeController {
       
      @Autowired
      EmpresaService empresaService;
      
      @RequestMapping(value = "/", method = RequestMethod.GET)
      public ModelAndView homeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            // Creem el modelview
            ModelAndView modelview = new ModelAndView("home");
            
            // Creem les opcions que aniràn a la barra de navegació
            List<Map<String, String>> opcions = new ArrayList<>();
            
            
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
                  
            // Afegim les opcions a l'array
            opcions.add(admin);
            opcions.add(candidat);
            opcions.add(empresa);
            
            modelview.getModelMap().addAttribute("ubicacio", "La teva web de cerca de feina");
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
      }

      
      @RequestMapping(value = "/altaCandidat", method = RequestMethod.GET)
      public ModelAndView addCandidatRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ModelAndView modelview = new ModelAndView("altaCandidat");
        
            // Opció Inici a la barra de navegació
            HashMap<String, String> inici = new HashMap<>();
            inici.put("paraula","Inici");
            inici.put("url","/");
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{inici};
            
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
            for (Experiencia exp : formCandidat.getExperiencies()) {
                  System.out.println("--- Experiencia: "+exp.getDescripcio());
            }
            System.out.println("--- Habilitats: "+formCandidat.getHabilitats());
            // System.out.println("--- Propietat d'una de les experiències no omplertes: "+formCandidat.getExperiencies());
            
            // Invocarem els mètodes corresponents un cop fets els filtres que calguin
            // CandidatService.addCandidat(formCandidat);
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
            
            // Opció Inici a la barra de navegació
            HashMap<String, String> inici = new HashMap<>();
            inici.put("paraula","Inici");
            inici.put("url","/");
            
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{inici};
            
            //Formulari d'alta empresa 
            Empresa formEmpresa = new Empresa();
            
            LlistaSectors llistaSectors = new LlistaSectors();
        
            modelview.getModelMap().addAttribute("formEmpresa", formEmpresa);
            modelview.getModelMap().addAttribute("ubicacio", "Alta d'empresa");
            modelview.getModelMap().addAttribute("llistaSectors", llistaSectors);
            modelview.getModelMap().addAttribute("opcions", opcions);
            
            return modelview;
    }
    

      @RequestMapping(value = "/executaAltaEmpresa", method = RequestMethod.POST)
      public String executaAltaEmpresa(@ModelAttribute("formEmpresa") Empresa formEmpresa, BindingResult result) {
            
            System.out.println("--- Ja tenim l'empresa a l'objecte formEmpresa. Fem amb ell el que faci falta.");
            
            // Invocarem els mètodes corresponents un cop fets els filtres que calguin
            // CandidatService.addCandidat(formCandidat);
            // service.addExperiencies
            //....
            
            // segons el resultat de l'execució del mètode...
            // ... haurem de redirigir a la vista que vulguem passant feedback a l'usuari (alta feta/alta no feta)
            return "redirect:/";
    }
      
     
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView infoRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("info");
        return modelview;
    }
}
