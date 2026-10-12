package ru.university.goncharov.controller;

import ru.university.goncharov.model.Birthday;
import ru.university.goncharov.model.Event;
import ru.university.goncharov.model.Meeting;
import ru.university.goncharov.service.OrganizerService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

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
                System.out.println("1. Добавить День рождения");
                System.out.println("2. Добавить Встречу");
                System.out.println("3. Показать все события");
                System.out.println("4. Удалить событие");
                System.out.println("5. Обновить ДР или Событие");
                System.out.println("0. Выход");
                System.out.println("Выберите действие ");

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
                            delete();
                            break;

                        case "5":
                            update();
                            break;

                        default:
                            System.out.println("Неправильно введены данные");
                            break;
                    }
                } catch (DateTimeException e) {
                    System.out.println("Дата или время введены в неправильном формате");
                }
            }

            scan.close();
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
            Scanner scan = new Scanner(System.in);
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
            }else{
                for(int i =0 ;i<all.size();i++){
                    Event event = all.get(i);

                    if(event instanceof Birthday){
                        Birthday b =  (Birthday) event;
                        System.out.println("[День рождения] \n"
                                + "Индекс события: " + i + "\n"
                                + "Дата: " + event.getDate() + "\n"
                                + "Описание: " + event.getDescription() + "\n"
                                + "Именинник: " + b.getName() + "\n"
                                + "Час рождения: " + b.getTime() + "\n"
                                + "Подарок: " + b.getGift() + "\n");
                    }
                    else if (event instanceof Meeting) {
                    Meeting m = (Meeting) event;
                    System.out.println("[Встреча] \n"
                            + "Индекс события: " + i + "\n"
                            + "Дата: " + event.getDate() + "\n"
                            + "Описание: " + event.getDescription() + "\n"
                            + "Собеседник: " + m.getInterlocutor() + "\n"
                            + "Время начала: " + m.getStartTime() + "\n");
                    }
                }
            }
        }

        private void delete() {
            System.out.println("Выберите индекс события, которое хотите удалить, начиная с 0");

            try {
                int index = Integer.parseInt(scan.nextLine());
                boolean isRemoved = organizerService.delete(index);

                if (isRemoved) {
                    System.out.println("Событие удалено");
                } else {
                    System.out.println("Ошибка удаления");
                }
            } catch (NumberFormatException e) {
                System.out.println("Индекс должен быть числом");
            }
        }

        private void update() {


            System.out.println("Введите индекс события, которое хотите обновить");
            try {
                int index = Integer.parseInt(scan.nextLine());

                Optional<Event> optionalEvent = organizerService.getEventById(index);
                if (optionalEvent.isEmpty()){
                    System.out.println("События с индексом " + index + " нет");
                    return;
                }

                Event event = optionalEvent.get();
                if (organizerService.getEventById(index).isEmpty()){
                    System.out.println("События с индексом" + index + " нет");
                    return;
                }
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
                    organizerService.updateEvent(index, updatedB);
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
                    organizerService.updateEvent(index, updatedM);
                    System.out.println("Встреча успешно обновлена!");
                }




            }catch (NumberFormatException e){
                System.out.println("Индекс должен быть числом");
            }catch (DateTimeException e) {
                System.out.println("Дата или время введены в неправильном формате");
            }
        }
    }



