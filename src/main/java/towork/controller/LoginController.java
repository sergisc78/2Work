package towork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

      @RequestMapping(value = "/login", method = RequestMethod.GET)
      public ModelAndView login() {
            ModelAndView modelview = new ModelAndView("login");
            modelview.getModelMap().addAttribute("ubicacio", "Login");
            return modelview;
    }

      @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
      public ModelAndView loginerror(Model model) {
            ModelAndView modelview = new ModelAndView("login");
            modelview.getModelMap().addAttribute("ubicacio", "Login");
            modelview.getModelMap().addAttribute("error", "true");
            return modelview;
      }

}