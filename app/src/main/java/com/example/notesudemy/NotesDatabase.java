package com.example.notesudemy;

import androidx.room.*;

@androidx.room.Database(entities = {Note.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDao getNotesDao();
}
