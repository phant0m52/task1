package ru.university.goncharov.service;


import java.util.List;
import java.util.Optional;
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
            throw new IllegalArgumentException("Событие не может быть null");
        }
        return repository.addEvent(event);
    }

    public Meeting addMeeting(Meeting meeting) {
        if(meeting == null){
            throw new IllegalArgumentException("Событие не может быть null");
        }
        return repository.addMeeting(meeting);
    }

    public Birthday addBirthday(Birthday birthday) {
        if (birthday == null){
            throw new IllegalArgumentException("День рождения не может быть null");
        }
        return repository.addBirthday(birthday);
    }


    public List<Event> getAllEvents() {
        return repository.getAll();
    }

    public Event updateEvent(int index, Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Событие не может быть null");
        }
        if (index < 0 || index >= getAllEvents().size()) {
            throw new EventNotFoundException("Событие с индексом " + index + " не найдено");
        }
        return repository.update(index, event);
    }

    public boolean delete(int index) {
        if(index < 0 || index >= getAllEvents().size()) {
            System.out.println("Введите правильный индекс");
            return false;
        }
        return repository.delete(index);
    }

    public Optional<Event> getEventById(int index) {
        List<Event> events = repository.getAll();
        if (index < 0 || index >= events.size()) {
            return Optional.empty();
        }
        return Optional.of(getAllEvents().get(index));

    }

}
