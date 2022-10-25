package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String getHome(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model){
        if(error != null)model.addAttribute("error", true);
        if(logout != null)model.addAttribute("logout, true");
        return "login";
    }
}
