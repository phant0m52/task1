package ru.university.goncharov.service;


import java.util.List;
import java.util.Optional;

import ru.university.goncharov.exception.EventDescriptionException;
import ru.university.goncharov.exception.EventNotFoundException;
import ru.university.goncharov.model.Birthday;
import ru.university.goncharov.model.Event;

import ru.university.goncharov.model.Meeting;
import ru.university.goncharov.repository.TodoRepository;

public class OrganizerService {

    private final TodoRepository repository;

    public OrganizerService(TodoRepository repository) {
        this.repository = repository;
    }

    public Event addEvent(Event event) {
        if(event == null){
            throw new EventDescriptionException("Событие не может быть пустым");
        }
        return repository.addEvent(event);
    }

    public Meeting addMeeting(Meeting meeting) {
        if(meeting == null){
            throw new EventDescriptionException("Событие не может быть пустым");
        }
        return repository.addMeeting(meeting);
    }

    public Birthday addBirthday(Birthday birthday) {
        if (birthday == null){
            throw new EventDescriptionException("Событие не может быть пустым");
        }
        return repository.addBirthday(birthday);
    }


    public List<Event> getAllEvents() {
        return repository.getAll();
    }

    public Event updateEvent(String id, Event event) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID не может быть пустым");
        }
        if (event == null) {
            throw new EventDescriptionException("Событие не может быть пустым");
        }
        if (repository.getById(id).isEmpty()){
            throw new EventNotFoundException(
                    "Событие с ID " + id + " не найдено"
            );
        }
        event.setId(id);
        return repository.update(id, event);
    }

    public boolean delete(String id) {

        return repository.deleteById(id);
    }

    public Optional<Event> getEventById(String id) {

        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return repository.getById(id);

    }

}
