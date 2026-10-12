package ru.university.goncharov.repository;

import ru.university.goncharov.model.Birthday;
import ru.university.goncharov.model.Event;
import ru.university.goncharov.model.Meeting;


import java.util.*;

public class TodoRepositoryInMemoryImpl implements TodoRepository {
    private final List<Event> events;
    private int id = 0;

    public TodoRepositoryInMemoryImpl(List<Event> events) {
        this.events = new ArrayList<>(events);
    }

    @Override
    public List<Event> getAll() {
        return new ArrayList<>(events);
    }

    @Override
    public Event addEvent(Event event) {
        event.setId(id++);
        events.add(event);
        return event;
    }

    @Override
    public Birthday addBirthday(Birthday birthday) {
        events.add(birthday);
        return birthday;
    }

    @Override
    public Meeting addMeeting(Meeting meeting) {
        events.add(meeting);
        return meeting;
    }

    @Override
    public Event update(int index, Event event) {
        events.set(index, event);
        return event;
    }

    @Override
    public boolean delete(int index) {
        if (index < 0 || index >= events.size()){
            return false;
        }
        events.remove(index);
        return true;
    }

    @Override
    public Optional<Event> getById(int index) {
        if (index < 0 || index >= events.size()){
            return Optional.empty();
        }
        return Optional.of(events.get(index));
    }


}