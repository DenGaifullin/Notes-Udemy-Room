package com.example.notesudemy;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * from NotesRoom")
    List<Note> getAll();

    @Query("SELECT * from NotesRoom where id = :id")
    Note getById(int id);

    @Insert
    void insert(Note note);

    @Delete()
    void delete(Note note);

    @Update
    void update(Note note);
}
