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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author SERGI
 */
@Controller
public class HomeController {

      @RequestMapping(value = "/", method = RequestMethod.GET)
      public ModelAndView homeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            // Creem el modelview
            ModelAndView modelview = new ModelAndView("home");
            
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
            
            modelview.getModelMap().addAttribute("ubicacio", "La teva web de cerca de feina");
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
    }

      @RequestMapping(value = "/altaCandidat", method = RequestMethod.GET)
      public ModelAndView addcandidatRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ModelAndView modelview = new ModelAndView("altaCandidat");
        
            // Opció Inici a la barra de navegació
            HashMap<String, String> inici = new HashMap<>();
            inici.put("paraula","Inici");
            inici.put("url","/");
        
            // Hashmap que contindrà les opcions que hi haurà a la barra de navegació
            HashMap[] opcions = new HashMap[]{inici};  
        
            modelview.getModelMap().addAttribute("ubicacio", "Alta de candidat");
            modelview.getModelMap().addAttribute("opcions", opcions);
            return modelview;
    }
    
    @RequestMapping(value = "/altaEmpresa", method = RequestMethod.GET)
    public ModelAndView addempresaRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("altaEmpresa");
        modelview.getModelMap().addAttribute("banner", "2work");
        modelview.getModelMap().addAttribute("tagline", "La teva web de cerca de feina");
        return modelview;
    }
    
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView infoRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("info");
        return modelview;
    }
}
