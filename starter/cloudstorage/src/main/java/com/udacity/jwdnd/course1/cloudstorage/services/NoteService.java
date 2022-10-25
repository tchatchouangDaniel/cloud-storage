package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.form.NoteForm;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private final UserMapper userMapper;
    private User user;



    public NoteService(NoteMapper noteMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;

    }

    public void addNote(NoteForm noteForm, String username){
        int userId = userMapper.getUser(username).getUserid();
        noteMapper.addNotes(new Note(null, noteForm.getTitle(), noteForm.getDescription(), userId));
    }

    public List<Note> getAllNotes(String username){
        int userid = userMapper.getUser(username).getUserid();
        return noteMapper.getAllNotes(userid);
    }

}
