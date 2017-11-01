package com.project.homework.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.homework.model.Question;
import com.project.homework.model.User;
import com.project.homework.service.QuestionService;
import com.project.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.RolesAllowed;

@Controller
@RolesAllowed("ROLE_TEACHER")
public class ManageController {

    private QuestionService questionService;
    private UserService userService;

    @Autowired
    public ManageController(QuestionService questionService, UserService userService){
        this.questionService = questionService;
        this.userService = userService;
    }

    @RequestMapping("/manage")
    public String manage(Model model){
        model.addAttribute("question",new Question());
        return "manage";
    }

    @RequestMapping("/manage/deleteStu")
    public String deleteStudent(User user){
        userService.deleteUser(user);
        return "redirect:/manage";
    }

    @RequestMapping("/manage/setStuAuthority")
    public String setStuAuthority(User user){
        userService.setStuAuthority(user);
        return "redirect:/manage";
    }

    @RequestMapping("/manage/setTeaAuthority")
    public String setTeaAuthority(User user){
        userService.setTeaAuthority(user);
        return "redirect:/manage";
    }

    @RequestMapping("/manage/deleteQuestion")
    public String deleteQuestion(@RequestParam("id") Long id){
        questionService.removeQuestion(id);
        return "redirect:/manage";
    }

    @RequestMapping("/manage/uploadQuestion")
    @ResponseBody
    @RolesAllowed("ROLE_TEACHER")
    public ObjectNode uploadQuestion(@RequestBody Question question){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        questionService.addQuestion(question);
        node.put("status", true);
        return node;
    }
}
