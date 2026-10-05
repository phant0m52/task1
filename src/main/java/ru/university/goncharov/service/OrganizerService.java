package ru.university.goncharov.service;
import ru.university.goncharov.model.Event;
import java.util.ArrayList;
import java.util.List;

public class OrganizerService{
    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event event){
        events.add(event);

    }

    public boolean  removeEvent(int index){
        if (index >=0 && index < events.size()){
            events.remove(index);
            return true;
        }
        else{
            return false;
        }

    }


    public List<Event> getAllEvents(){
        return events;
    }


}