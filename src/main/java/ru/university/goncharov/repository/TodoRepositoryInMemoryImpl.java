package ru.university.goncharov.repository;

import ru.university.goncharov.model.Birthday;
import ru.university.goncharov.model.Event;
import ru.university.goncharov.model.Meeting;


import java.util.*;

public class TodoRepositoryInMemoryImpl implements TodoRepository {
    private List<Event> events = new ArrayList<>();


    @Override
    public List<Event> getAll() {
        return List.of();
    }

    @Override
    public Event create(Event event) {
        return null;
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public boolean delete(index id) {
        return false;
    }

    @Override
    public Optional<Event> getById(index id) {
        return Optional.empty();
    }
}