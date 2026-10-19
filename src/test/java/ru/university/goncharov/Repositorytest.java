package ru.university.goncharov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.university.goncharov.model.Birthday;
import ru.university.goncharov.model.Event;
import ru.university.goncharov.model.Meeting;
import ru.university.goncharov.repository.TodoRepositoryInMemoryImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class Repositorytest {

    private TodoRepositoryInMemoryImpl repository;
    private Birthday birthday;
    private Meeting meeting;

    @BeforeEach
    void setUp() {
        repository = new TodoRepositoryInMemoryImpl();
        birthday = new Birthday(LocalDate.of(2026, 11, 5), "ДР друга", "Иван", LocalTime.of(10, 30), "Книга");
        meeting = new Meeting(LocalDate.of(2026, 11, 10), "Обсуждение проекта", "Петр", LocalTime.of(12, 00));
    }


    @Test
    void getAll_emptyRepository_returnsEmptyList() {
        assertTrue(repository.getAll().isEmpty());
    }

    @Test
    void getAll_returnsAllAddedEventsInOrder() {
        repository.addBirthday(birthday);
        repository.addMeeting(meeting);

        assertEquals(2, repository.getAll().size());
        assertSame(birthday, repository.getAll().get(0));
        assertSame(meeting, repository.getAll().get(1));
    }

    @Test
    void addBirthday_returnsSameObjectAndStoresIt() {
        Birthday result = repository.addBirthday(birthday);

        assertSame(birthday, result);
        assertEquals(1, repository.getAll().size());
        assertTrue(repository.getAll().contains(birthday));
    }

    @Test
    void addMeeting_returnsSameObjectAndStoresIt() {
        Meeting result = repository.addMeeting(meeting);

        assertSame(meeting, result);
        assertEquals(1, repository.getAll().size());
        assertTrue(repository.getAll().contains(meeting));
    }

    @Test
    void addEvent_returnsSameObjectAndStoresIt() {
        Event result = repository.addEvent(meeting);

        assertSame(meeting, result);
        assertEquals(1, repository.getAll().size());
    }



    @Test
    void getById_existingId_returnsEvent() {
        repository.addBirthday(birthday);
        repository.addMeeting(meeting);

        Optional<Event> found = repository.getById(meeting.getId());

        assertTrue(found.isPresent());
        assertSame(meeting, found.get());
    }

    @Test
    void getById_unknownId_returnsEmpty() {
        repository.addBirthday(birthday);

        assertTrue(repository.getById("no-such-id").isEmpty());
    }

    @Test
    void getById_emptyRepository_returnsEmpty() {
        assertTrue(repository.getById(birthday.getId()).isEmpty());
    }


    @Test
    void update_existingId_replacesEventAndReturnsNewOne() {
        repository.addBirthday(birthday);
        Meeting replacement = new Meeting(LocalDate.of(2027, 1, 1), "Новая встреча", "Анна", LocalTime.of(9, 0));

        Event result = repository.update(birthday.getId(), replacement);

        assertSame(replacement, result);
        assertEquals(1, repository.getAll().size());
        assertSame(replacement, repository.getAll().get(0));
        assertFalse(repository.getAll().contains(birthday));
    }

    @Test
    void update_doesNotTouchOtherEvents() {
        repository.addBirthday(birthday);
        repository.addMeeting(meeting);
        Birthday replacement = new Birthday(LocalDate.of(2027, 2, 2), "Другой ДР", "Олег", LocalTime.of(8, 0), "Шарф");

        repository.update(birthday.getId(), replacement);

        assertEquals(2, repository.getAll().size());
        assertSame(replacement, repository.getAll().get(0));
        assertSame(meeting, repository.getAll().get(1));
    }

    @Test
    void update_unknownId_throwsIllegalArgumentException() {
        repository.addBirthday(birthday);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> repository.update("no-such-id", meeting));

        assertEquals("Событие с ID no-such-id не найдено", ex.getMessage());

        assertEquals(1, repository.getAll().size());
        assertSame(birthday, repository.getAll().get(0));
    }


    @Test
    void deleteById_existingId_returnsTrueAndRemovesEvent() {
        repository.addBirthday(birthday);

        boolean removed = repository.deleteById(birthday.getId());

        assertTrue(removed);
        assertTrue(repository.getAll().isEmpty());
    }

    @Test
    void deleteById_unknownId_returnsFalseAndKeepsEvents() {
        repository.addBirthday(birthday);

        boolean removed = repository.deleteById("no-such-id");

        assertFalse(removed);
        assertEquals(1, repository.getAll().size());
    }

    @Test
    void deleteById_removesOnlyTargetEvent() {
        repository.addBirthday(birthday);
        repository.addMeeting(meeting);

        repository.deleteById(birthday.getId());

        assertEquals(1, repository.getAll().size());
        assertSame(meeting, repository.getAll().get(0));
    }

    @Test
    void deleteById_calledTwice_secondCallReturnsFalse() {
        repository.addMeeting(meeting);

        assertTrue(repository.deleteById(meeting.getId()));
        assertFalse(repository.deleteById(meeting.getId()));
    }
}