package ru.university.goncharov.repository;

import ru.university.goncharov.model.Birthday;
import ru.university.goncharov.model.Event;
import ru.university.goncharov.model.Meeting;


import java.util.*;

public class TodoRepositoryInMemoryImpl implements TodoRepository {
    private final List<Event> events = new ArrayList<>();
    private int id = 0;

    @Override
    public List<Event> getAll() {
        return new ArrayList<>(events);
    }

    @Override
    public Event addEvent(Event event) {
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
    public Event update(String id, Event event) {
        events.set(Integer.parseInt(id), event);
        return event;
    }

    @Override
    public boolean deleteById(String id) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equals(id)) {
                events.remove(i);
                return true;
            }
        }

        return false;
    }
    @Override
    public Optional<Event> getById(String id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return Optional.of(event);
            }
        }
        return Optional.empty();
    }


}