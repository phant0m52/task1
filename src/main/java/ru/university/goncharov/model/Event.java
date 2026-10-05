package ru.university.goncharov.model;

import java.io.Serializable;
import java.time.LocalDate;


public abstract class Event implements Serializable{
    private LocalDate date;
    private String description;

    public Event(LocalDate date,String description){
        this.date = date;
        this.description = description;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getDescription(){
        return description;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setDescription(String description){
        this.description = description;
    }

}