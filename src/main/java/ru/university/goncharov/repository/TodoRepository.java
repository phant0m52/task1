package ru.university.goncharov.repository;

import ru.university.goncharov.model.Birthday;
import ru.university.goncharov.model.Event;
import ru.university.goncharov.model.Meeting;

import java.util.List;
import java.util.Optional;


public interface TodoRepository {

    List<Event> getAll();

    Event addEvent(Event event);

    Birthday addBirthday(Birthday birthday);

    Meeting addMeeting(Meeting meeting);

    Event update(String id, Event event);

    boolean deleteById(String id);

    Optional<Event> getById(String id);
}