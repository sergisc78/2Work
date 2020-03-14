
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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EspaiController {
    
    // Crear el controller dels espais dls 3 usuaris del sistema,candidat, empresa i administrador
    
     @RequestMapping(value = "/espaiCandidat", method = RequestMethod.GET)
    public ModelAndView EspaiCandidatRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("espaiCandidat");
        modelview.getModelMap().addAttribute("banner", "2work");
        modelview.getModelMap().addAttribute("tagline", "La teva web de recerca de feina");
        modelview.getModelMap().addAttribute("footer", "2Work Copyright 2020");
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
    
}
