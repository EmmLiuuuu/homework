package com.project.homework.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    @RequestMapping("/error")
    public String error(){
        return getErrorPath();
    }

    @Override
    public String getErrorPath() {
        return "errorPage";
    }
}
