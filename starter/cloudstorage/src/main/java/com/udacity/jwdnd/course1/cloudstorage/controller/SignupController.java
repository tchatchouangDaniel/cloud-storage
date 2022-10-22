package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.form.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SignupController {
    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getSignupPage(@ModelAttribute("signupForm") SignupForm signupForm){
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(@Valid @ModelAttribute("signupForm") SignupForm signupForm, BindingResult bindingResult, Model model){
        List<String> signupError = new ArrayList<>();

        if(!userService.isUsernameAvailable(signupForm.getUsername())){
            signupError.add("This username is not available");
        }

        if(signupError.isEmpty() && !bindingResult.hasErrors()){
            int rowsAdded = userService.createUser(signupForm);
            if(rowsAdded < 0){
                signupError.add("There was an error on signup. Please try again.");
            }
        }

        if(signupError.isEmpty() && !bindingResult.hasErrors()){
            model.addAttribute("signupSuccess", "You signed up successfully.");
        }else {
            model.addAttribute("signupError", signupError);
            model.addAttribute("hasErrors", bindingResult.hasErrors());
        }

        return "signup";
    }
}
