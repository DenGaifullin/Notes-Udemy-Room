package com.example.notesudemy;

import android.graphics.Color;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "NotesRoom")
class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String header;
    private String description;
    @ColumnInfo(name = "day_of_week")
    private int dayOfWeek;
    @ColumnInfo(name = "num_of_color_priority")
    private int numOfColorPriority;

    public Note(int id, String header, String description, int dayOfWeek, int numOfColorPriority) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.numOfColorPriority = numOfColorPriority;
    }

    @Ignore
    public Note(String header, String description, int dayOfWeek, int numOfColorPriority) {
        this.header = header;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.numOfColorPriority = numOfColorPriority;
    }

    public String getDayOfWeekAsString(){
        switch (dayOfWeek){
            case 0 : return "Понедельник";
            case 1 : return "Вторник";
            case 2 : return "Среда";
            case 3 : return "Четверг";
            case 4 : return "Пятница";
            case 5 : return "Суббота";
            case 6 : return "Воскресенье";
        }
        return null;
    }
    public int getColorPriority(){
        switch (numOfColorPriority){
            case 1 : return Color.RED;
            case 2 : return Color.YELLOW;
            case 3 : return Color.GREEN;
        }
        return Color.RED;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setNumOfColorPriority(int numOfColorPriority) {
        this.numOfColorPriority = numOfColorPriority;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getNumOfColorPriority() {
        return numOfColorPriority;
    }
}