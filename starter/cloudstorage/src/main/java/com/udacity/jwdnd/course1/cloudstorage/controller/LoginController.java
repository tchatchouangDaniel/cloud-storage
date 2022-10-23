package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.form.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.services.UserLogoutTrackerService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private UserLogoutTrackerService userLogoutTrackerService;

    public LoginController(UserLogoutTrackerService userLogoutTrackerService) {
        this.userLogoutTrackerService = userLogoutTrackerService;
    }

    @GetMapping()
    public String getHome(Model model){
        model.addAttribute("hasUserLogout", userLogoutTrackerService.hasUserLoggedOut());
        userLogoutTrackerService.setUserLoggedOut(false);
        return "login";
    }

//    @PostMapping("/login")
//    public String login(Authentication authentication, @Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors())
//            model.addAttribute("hasErrors", bindingResult.hasErrors());
//        if(authentication.isAuthenticated())
//            return "home";
//        return "login";
//    }
}
