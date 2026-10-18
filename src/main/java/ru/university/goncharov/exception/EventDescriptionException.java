package ru.university.goncharov.exception;

public class EventDescriptionException extends BusinessException {
    public EventDescriptionException() {
        super("Описание события не может быть пустым");
    }
}
