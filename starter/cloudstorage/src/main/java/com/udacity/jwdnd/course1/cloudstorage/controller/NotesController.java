package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotesController {
    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/note")
    public String getHomePage(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, Model model){
        String username = authentication.getName();
        model.addAttribute("username", username);
        model.addAttribute("noteList",noteService.getAllNotes(username));
        model.addAttribute("isNotes", true);
        return "home";
    }

    @PostMapping("/add-note")
    public String addNote(Authentication authentication,@ModelAttribute("noteForm") NoteForm noteForm, Model model){
        String username = authentication.getName();

        if(noteForm.getNoteid() == null){
            noteService.addNote(noteForm, username);
            model.addAttribute("result", "success");
        }else{
            noteService.updateNote(noteForm, username);
            model.addAttribute("result", "success");
        }
        return "result";
    }

    @GetMapping("/remove-note/{noteId}")
    public String removeNote(Authentication authentication, @PathVariable Integer noteId, Model model){
        String username = authentication.getName();
        noteService.deleteNote(noteId);
        model.addAttribute("username", username);
        model.addAttribute("result", "success");
        return "result";
    }



}
