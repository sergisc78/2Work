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
 * @author Sergi
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/loginCandidat", method = RequestMethod.GET)
    public ModelAndView loginCandidatRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("loginCandidat");
        modelview.getModelMap().addAttribute("banner", "2work");
        modelview.getModelMap().addAttribute("tagline", "La teva web de recerca de feina");
       return modelview;
    }
    
}
