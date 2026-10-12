package ru.university.goncharov;


import ru.university.goncharov.controller.Controller;
import ru.university.goncharov.model.Event;
import ru.university.goncharov.repository.TodoRepositoryInMemoryImpl;
import ru.university.goncharov.service.OrganizerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Event> events = new ArrayList<>();
        TodoRepositoryInMemoryImpl todorepository = new TodoRepositoryInMemoryImpl(events);
        OrganizerService organizerservice = new OrganizerService(todorepository);
        Scanner scan = new Scanner(System.in);
        Controller controller = new Controller(organizerservice, scan);
        controller.run();

    }
}