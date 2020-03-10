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
public class LoginController {

    @RequestMapping(value = "/loginCandidat", method = RequestMethod.GET)
    public ModelAndView LoginCandidatRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("loginCandidat");
        modelview.getModelMap().addAttribute("banner", "2work");
        modelview.getModelMap().addAttribute("tagline", "La teva web de recerca de feina");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap();
        map.put("url", "/loginCandidat");
        list.add(map);
        modelview.getModelMap().addAttribute("footer", "2Work Copyright 2020");
        modelview.getModelMap().addAttribute("options", list);
        return modelview;
    }
      @RequestMapping(value = "/loginEmpresa", method = RequestMethod.GET)
    public ModelAndView LoginEmpresaRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("loginEmpresa");
        modelview.getModelMap().addAttribute("banner", "2work");
        modelview.getModelMap().addAttribute("tagline", "La teva web de recerca de feina");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap();
        map.put("url", "/loginCandidat");
        list.add(map);
        modelview.getModelMap().addAttribute("footer", "2Work Copyright 2020");
        modelview.getModelMap().addAttribute("options", list);
        return modelview;
    }
    
}
