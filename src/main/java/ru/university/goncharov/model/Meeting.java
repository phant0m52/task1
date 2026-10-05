package ru.university.goncharov.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Meeting extends Event{
    private String interlocutor;
    private LocalTime startTime;

    public Meeting(LocalDate date,String description,String interlocutor, LocalTime startTime){
        super(date, description);
        this.interlocutor = interlocutor;
        this.startTime = startTime;
    }

    public String getInterlocutor(){
        return interlocutor;
    }

    public LocalTime getStartTime(){
        return startTime;
    }

    public void setInterlocutor(String interlocutor){
        this.interlocutor = interlocutor;
    }

    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }
}