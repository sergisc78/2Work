
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homeRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("home");
        modelview.getModelMap().addAttribute("banner", "2work");
        modelview.getModelMap().addAttribute("tagline", "La teva web de recerca de feina");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap();
        map.put("desc", "Dona't d'alta per ofertes i demandes de feina!");
        map.put("url", "/add");
        map.put("icon","glyphicon glyphicon-log-in");
        list.add(map);
        
        Map<String, String> map1 = new HashMap();
        map1.put("desc", "Ja sóc EMPRESA 2work!");
        map1.put("url", "/login");
        map1.put("icon","glyphicon glyphicon-log-in");
        list.add(map1);
        
        Map<String, String> map2 = new HashMap();
        map2.put("desc", "Ja sóc CANDIDAT 2work!");
        map2.put("url", "/login");
        map2.put("icon","glyphicon glyphicon-log-in");
        list.add(map2);
        
        modelview.getModelMap().addAttribute("footer", "2Work 2020 Copyright");
        modelview.getModelMap().addAttribute("options", list);
        return modelview;
    }

     @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addArticleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("home");
        modelview.getModelMap().addAttribute("banner", "Benvinguts!");
        modelview.getModelMap().addAttribute("tagline", "Gràcies per formar part de 2work!");
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        Map<String, String> info = new HashMap();
        info.put("title", "Empresa");
        info.put("desc", "Formar part de les empreses de 2work");
        info.put("url", "/add/Empresa");
        info.put("icon", "glyphicon glyphicon-log-in");
        data.add(info);
        Map<String, String> info2 = new HashMap();
        info2.put("title", "Candidat");
        info2.put("desc", "Formar part dels candidats de 2work!");
        info2.put("url", "/add/Candidat");
        info2.put("icon", "glyphicon glyphicon-log-in");
        data.add(info2);
       
        modelview.getModelMap().addAttribute("options", data);
        return modelview;
    }
    @RequestMapping(value = "/add/{tipus}", method = RequestMethod.GET)
    public ModelAndView addArticleForm(@PathVariable("tipus") String tipusArticle, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        ModelAndView modelview = new ModelAndView("add" + tipusArticle);
        modelview.getModelMap().addAttribute("tagline", "Omplir formulari");
        
        modelview.getModelMap().addAttribute("banner", "2work!");
        return modelview;
    }
    
}
