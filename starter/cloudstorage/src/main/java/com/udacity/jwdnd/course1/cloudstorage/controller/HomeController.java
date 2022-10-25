package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private final NoteService noteService;

    public HomeController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/home")
    public String getHome(Authentication authentication,@ModelAttribute("noteForm") NoteForm noteForm, Model model){
        String username = authentication.getName();
        model.addAttribute("username", username);
        model.addAttribute("noteList", noteService.getAllNotes(username));
        return "home";
    }
    @PostMapping("/home")
    public String register(Authentication authentication,@ModelAttribute("noteForm") NoteForm noteForm, Model model){
        String username = authentication.getName();
        noteService.addNote(noteForm, authentication.getName());
        model.addAttribute("username", username);
        model.addAttribute("noteList", noteService.getAllNotes(username));
        return "home";
    }

}
