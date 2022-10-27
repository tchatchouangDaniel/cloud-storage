package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.form.NoteForm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private final UserMapper userMapper;
    private final UserService userService;
    private User user;



    public NoteService(NoteMapper noteMapper, UserMapper userMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
        this.userService  = userService;

    }

    public void addNote(NoteForm noteForm, String username){
        int userId = userMapper.getUser(username).getUserid();
        noteMapper.addNotes(new Note(null, noteForm.getTitle(), noteForm.getDescription(), userId));
    }

    public List<Note> getAllNotes(String username){
        int userid = userMapper.getUser(username).getUserid();
        return noteMapper.getAllNotes(userid);
    }

    public int updateNote(NoteForm noteForm, String username){
        int userid = userMapper.getUser(username).getUserid();
        return noteMapper.updateNotes(new Note(noteForm.getNoteid(), noteForm.getTitle(), noteForm.getDescription(), userid));
    }

    public boolean doesNoteExist(int noteid){
        return noteMapper.getNote(noteid) != null;
    }

    public int deleteNote(int noteid){
        return noteMapper.deleteNotes(noteid);
    }

}
