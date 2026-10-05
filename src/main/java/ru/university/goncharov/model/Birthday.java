package ru.university.goncharov.model;

import java.time.LocalDate;
import java.time.LocalTime;



public class Birthday extends Event{

    private String name;
    private LocalTime timeBirth;
    private String gift;

    public Birthday(LocalDate date,String description,String name, LocalTime timeBirth, String gift){
        super(date, description);
        this.name = name;
        this.timeBirth = timeBirth;
        this.gift = gift;
    }

    public String getName(){
        return name;
    }

    public LocalTime getTime(){
        return timeBirth;
    }

    public String getGift(){
        return gift;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTime(LocalTime timeBirth){
        this.timeBirth = timeBirth;
    }

    public void setGift(String gift){
        this.gift = gift;
    }
}