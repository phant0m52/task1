package ru.university.goncharov.repository;

import ru.university.goncharov.model.Birthday;
import ru.university.goncharov.model.Event;
import ru.university.goncharov.model.Meeting;


import java.util.*;

public class TodoRepositoryInMemoryImpl implements TodoRepository {
    private final List<Event> events = new ArrayList<>();


    @Override
    public List<Event> getAll() {
        return events;
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
        for (int i = 0; i < events.size(); i++){
            if (events.get(i).getId().equals(id)){

                events.set(i, event);
                return event;
            }
        }
        throw new IllegalArgumentException(
                "Событие с ID " + id + "е найдено"
        );
    }

    @Override
    public boolean deleteById(String id) {
        return events.removeIf(event -> event.getId().equals(id));
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