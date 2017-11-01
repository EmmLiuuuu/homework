package com.project.homework.web;

import com.project.homework.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model, HttpSession httpSession){
        if (httpSession.getAttribute("lastUsername") != null)
            model.addAttribute("user", new User((String) httpSession.getAttribute("lastUsername"), null));
        else
            model.addAttribute("user", new User());
        return "login";
    }
}
