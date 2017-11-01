package com.project.homework.web;

import com.project.homework.model.User;
import com.project.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private UserService userService;

    @Autowired
    public ProfileController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String profile(Model model, HttpSession httpSession){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userService.findUserByUserName(userName);
        model.addAttribute("user", user);
        httpSession.setAttribute("user", user);
        return "profile";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateProfile(User user, Model model, HttpSession httpSession){
        if (!validate(user.getPhoneNumber(), user.getEmailAddress(), user.getName())){
            model.addAttribute("status", Boolean.FALSE);
            user.setUserName(((User)httpSession.getAttribute("user")).getUserName());
            return "profile";
        }
        User sessionUser = (User) httpSession.getAttribute("user");
        sessionUser.setPhoneNumber(user.getPhoneNumber());
        sessionUser.setEmailAddress(user.getEmailAddress());
        sessionUser.setName(user.getName());
        sessionUser.setClassName(user.getClassName());
        userService.updateUser(sessionUser);
        model.addAttribute("status", Boolean.TRUE);
        user.setUserName(((User)httpSession.getAttribute("user")).getUserName());
        return "profile";
    }

    private boolean validate(String phoneNumber, String emailAddress, String name){
        if (phoneNumber == null || phoneNumber.length() != 11)
            return false;
        if (emailAddress == null || name == null)
            return false;
        return true;
    }
}
