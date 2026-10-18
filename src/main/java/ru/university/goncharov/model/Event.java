package ru.university.goncharov.model;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Event {
    private String id;
    private LocalDate date;
    private String description;

    public Event(LocalDate date,String description){
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.description = description;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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