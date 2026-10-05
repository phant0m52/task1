package ru.university.goncharov.repository;

import ru.university.goncharov.model.Birthday;
import ru.university.goncharov.model.Event;
import ru.university.goncharov.model.Meeting;

import java.util.List;
import java.util.Optional;


public interface TodoRepository {

    List<Event> getAll();

    Event create(Event event);

    Event update(Event event);

    boolean delete(index id);

    Optional<Event> getById(index id);
}