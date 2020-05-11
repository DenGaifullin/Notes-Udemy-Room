package com.example.notesudemy;

import android.app.Application;
import android.os.AsyncTask;

import androidx.room.Room;

import com.squareup.leakcanary.LeakCanary;

public class AccessNotesDatabase extends Application {
    public static AccessNotesDatabase instance;
    private NotesDatabase notesDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance =this;
        notesDatabase = Room.databaseBuilder( AccessNotesDatabase.this, NotesDatabase.class, "DataMain")
//                .allowMainThreadQueries()
                .build();
        LeakCanary.install(this);
    }

    public static AccessNotesDatabase getInstance(){
        return instance;
    }

    public NotesDatabase getNotesDatabase(){
        return notesDatabase;
    }
}