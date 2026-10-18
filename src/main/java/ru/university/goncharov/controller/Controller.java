package ru.university.goncharov.controller;

import ru.university.goncharov.model.Birthday;
import ru.university.goncharov.model.Event;
import ru.university.goncharov.model.Meeting;
import ru.university.goncharov.service.OrganizerService;
import ru.university.goncharov.exception.BusinessException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Controller {

    private final OrganizerService organizerService;
    private final Scanner scan;

    public Controller(OrganizerService organizerService, Scanner scan) {
        this.organizerService = organizerService;
        this.scan = scan;
    }
    public void run() {
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        boolean cases = true;
        while (cases) {
                printMenu();

                String choice = scan.nextLine();

                if (choice.equals("0")) {
                    break;
                }

                try {
                    switch (choice) {
                        case "1":
                            addB();
                            break;

                        case "2":
                            addM();
                            break;

                        case "3":
                            getAll();
                            break;

                        case "4":
                            getByID();
                            break;

                        case "5":
                            delete();
                            break;

                        case "6":
                            update();
                            break;

                        default:
                            System.out.println("Неправильно введены данные");
                            break;
                    }
                }catch (BusinessException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Возникла непредвиденная ошибка: " + e.getMessage());
                }
            } scan.close();
    }

        private void printMenu(){
            System.out.println("1. Добавить День рождения");
            System.out.println("2. Добавить Встречу");
            System.out.println("3. Показать все события");
            System.out.println("4. Показать событие по ID");
            System.out.println("5. Удалить событие");
            System.out.println("6. Обновить ДР или Событие");
            System.out.println("0. Выход");
            System.out.println("Выберите действие ");
        }




        private void addB() {
            System.out.println("Выберите дату (формат: год-месяц-дата)");

            LocalDate date = LocalDate.parse(scan.nextLine());

            System.out.println("Введите описание");
            String bDesc = scan.nextLine();

            System.out.println("Введите имя именинника");
            String bName = scan.nextLine();

            System.out.println("Введите время рождения");
            LocalTime bTime = LocalTime.parse(scan.nextLine());

            System.out.println("Введите что хотите подарить");
            String bGift = scan.nextLine();

            Birthday b = new Birthday(date, bDesc, bName, bTime, bGift);
            organizerService.addBirthday(b);

        }

        private void addM(){
            System.out.println("Выберите дату (формат: год-месяц-дата)");
            LocalDate mDate = LocalDate.parse(scan.nextLine());

            System.out.println("Введите описание");
            String mDesc = scan.nextLine();

            System.out.println("Введите собеседника(ов)");
            String mInterlocutor = scan.nextLine();

            System.out.println("Введите время начала (формат: час:минуты)");
            LocalTime mTime = LocalTime.parse(scan.nextLine());

            Meeting m = new Meeting(mDate, mDesc, mInterlocutor, mTime);
            organizerService.addMeeting(m);

        }

        private void getAll() {
            List<Event> all = organizerService.getAllEvents();
            if (all.isEmpty()) {
                System.out.println("Нет событий");
                return;
            }
            for (Event event : all) {
                printEvent(event);
            }


        }


        private void getByID() {
            System.out.println("Выберите ID события, которое хотите увидеть");
            String id = scan.nextLine();
            Optional<Event> optionalEvent =  organizerService.getEventById(id);
            if (optionalEvent.isEmpty()) {
                System.out.println("События с индексом " + id + " нет");
                return;
            }
            printEvent(optionalEvent.get());
        }

        private void delete() {
            System.out.println("Выберите ID события, которое хотите удалить");
            String id = scan.nextLine();
            boolean isRemoved = organizerService.delete(id);

            if (isRemoved) {
                System.out.println("Событие удалено");
            }else{
                System.out.println("События с таким id не найдено");
            }
        }

        private void update() {


            System.out.println("Введите ID события, которое хотите обновить:");
            String id = scan.nextLine();
            try {


                Optional<Event> optionalEvent = organizerService.getEventById(id);
                if (optionalEvent.isEmpty()){
                    System.out.println("События с индексом " + id + " нет");
                    return;
                }

                Event event = optionalEvent.get();

                if (event instanceof Birthday){

                    System.out.println("Выберите дату (формат: год-месяц-дата)");
                    LocalDate date = LocalDate.parse(scan.nextLine());

                    System.out.println("Введите описание");
                    String bDesc = scan.nextLine();

                    System.out.println("Введите имя именинника");
                    String bName = scan.nextLine();

                    System.out.println("Введите время рождения (формат: часы:минуты)");
                    LocalTime bTime = LocalTime.parse(scan.nextLine());

                    System.out.println("Введите что хотите подарить");
                    String bGift = scan.nextLine();

                    Birthday updatedB = new Birthday(date, bDesc, bName, bTime, bGift);
                    organizerService.updateEvent(id, updatedB);
                    System.out.println("День рождения успешно обновлен!");
                }else if (event instanceof Meeting){
                    System.out.println("Выберите дату (формат: год-месяц-дата)");
                    LocalDate mDate = LocalDate.parse(scan.nextLine());

                    System.out.println("Введите описание");
                    String mDesc = scan.nextLine();

                    System.out.println("Введите собеседника(ов)");
                    String mInterlocutor = scan.nextLine();

                    System.out.println("Введите время начала (формат: час:минуты)");
                    LocalTime mTime = LocalTime.parse(scan.nextLine());

                    Meeting updatedM = new Meeting(mDate, mDesc, mInterlocutor, mTime);
                    organizerService.updateEvent(id, updatedM);
                    System.out.println("Встреча успешно обновлена!");
                }




            }catch (NumberFormatException e){
                System.out.println("Индекс должен быть числом");
            }catch (DateTimeException e) {
                System.out.println("Дата или время введены в неправильном формате");
            }
        }


    private void printEvent(Event event) {
        if (event instanceof Birthday) {
            Birthday b = (Birthday) event;

            System.out.println("[День рождения]");
            System.out.println("ID: " + event.getId());
            System.out.println("Дата: " + event.getDate());
            System.out.println("Описание: " + event.getDescription());
            System.out.println("Именинник: " + b.getName());
            System.out.println("Время рождения: " + b.getTime());
            System.out.println("Подарок: " + b.getGift());

        } else if (event instanceof Meeting) {
            Meeting m = (Meeting) event;

            System.out.println("[Встреча]");
            System.out.println("ID: " + event.getId());
            System.out.println("Дата: " + event.getDate());
            System.out.println("Описание: " + event.getDescription());
            System.out.println("Собеседник: " + m.getInterlocutor());
            System.out.println("Время начала: " + m.getStartTime());
        }
    }

}



