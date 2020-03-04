/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towork.controller;

import java.io.IOException;
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
    
    @RequestMapping(value="/",method=RequestMethod.GET)
    public ModelAndView homeRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview=new ModelAndView("home");
        modelview.getModelMap().addAttribute("presentacio","Benvinguts a 2Work");
        modelview.getModelMap().addAttribute("tagline", "2Work és una web de recerca de feina");
        modelview.getModelMap().addAttribute("footer", "2Work 2020 Copyright");
        return modelview;
    }
}
