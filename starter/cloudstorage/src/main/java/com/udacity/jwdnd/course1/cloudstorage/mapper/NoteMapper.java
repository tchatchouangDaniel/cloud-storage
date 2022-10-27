package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE noteid=#{noteid}")
    Note getNote(Integer noteid);

    @Select("SELECT * FROM NOTES n, USERS u WHERE n.userid = u.userid AND u.userid = #{userid}")
    List<Note> getAllNotes(Integer userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNotes(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid=#{noteid}")
    int deleteNotes(Integer noteid);

    @Update("UPDATE NOTES SET notetitle=#{notetitle}, notedescription=#{notedescription} WHERE noteid=#{noteid}")
    int updateNotes(Note note);
}
