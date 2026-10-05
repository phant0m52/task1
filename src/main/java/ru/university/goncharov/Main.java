package ru.university.goncharov;

import ru.university.goncharov.model.Birthday;
import ru.university.goncharov.model.Event;
import ru.university.goncharov.model.Meeting;
import ru.university.goncharov.service.OrganizerService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        OrganizerService organizer = new OrganizerService();
        Scanner scan = new Scanner(System.in);
        boolean cases = true;

        while (cases) {
            System.out.println("1. Добавить День рождения");
            System.out.println("2. Добавить Встречу");
            System.out.println("3. Показать все события");
            System.out.println("4. Удалить событие");
            System.out.println("0. Выход");
            System.out.println("Выберите действие ");

            String choice = scan.nextLine();

            if (choice.equals("0")) {
                break;
            }

            try {
                switch (choice) {
                    case "1":
                        addB(organizer);
                        break;

                    case "2":
                        addM(organizer);
                        break;

                    case "3":
                        getAll(organizer);
                        break;

                    case "4":
                        removeEl(organizer);
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

    private static void getAll(OrganizerService organizer) {
        System.out.println("<-Список ваших событий->");
        List<Event> events = organizer.getAllEvents();

        if (events.isEmpty()) {
            System.out.println("Список ваших событий пуст");
        } else {
            for (int i = 0; i < events.size(); i++) {
                Event event = events.get(i);

                if (event instanceof Birthday) {
                    Birthday b = (Birthday) event;
                    System.out.println("[День рождения] \n"
                            + "Индекс события: " + i + "\n"
                            + "Дата: " + event.getDate() + "\n"
                            + "Описание: " + event.getDescription() + "\n"
                            + "Именинник: " + b.getName() + "\n"
                            + "Время: " + b.getTime() + "\n"
                            + "Подарок: " + b.getGift() + "\n");
                } else if (event instanceof Meeting) {
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

    private static void removeEl(OrganizerService organizer) {
        System.out.println("Выберите индекс события, которое хотите удалить, начиная с 0");
        Scanner scan = new Scanner(System.in);
        try {
            int index = Integer.parseInt(scan.nextLine());
            boolean isRemoved = organizer.removeEvent(index);

            if (isRemoved) {
                System.out.println("Событие удалено");
            } else {
                System.out.println("Ошибка удаления");
            }
        } catch (NumberFormatException e) {
            System.out.println("Индекс должен быть числом");
        }
    }

    private static void addB(OrganizerService organizer){
        Scanner scan = new Scanner(System.in);
        System.out.println("Выберите дату (формат: год-месяц-дата)");

        LocalDate date = LocalDate.parse(scan.nextLine());

        System.out.println("Введите описание");
        String bDesc = scan.nextLine();

        System.out.println("Введите имя именинника");
        String bName = scan.nextLine();

        System.out.println("Введите время (формат: час:минуты)");
        LocalTime bTime = LocalTime.parse(scan.nextLine());

        System.out.println("Введите что хотите подарить");
        String bGift = scan.nextLine();

        Birthday birthday = new Birthday(date, bDesc, bName, bTime, bGift);
        organizer.addEvent(birthday);
        System.out.println("Событие добавлено");
    }

    private static void addM(OrganizerService organizer){
        System.out.println("Выберите дату (формат: год-месяц-дата)");
        Scanner scan = new Scanner(System.in);
        LocalDate mDate = LocalDate.parse(scan.nextLine());

        System.out.println("Введите описание");
        String mDesc = scan.nextLine();

        System.out.println("Введите собеседника(ов)");
        String mInterlocutor = scan.nextLine();

        System.out.println("Введите время начала (формат: час:минуты)");
        LocalTime mTime = LocalTime.parse(scan.nextLine());

        Meeting meeting = new Meeting(mDate, mDesc, mInterlocutor, mTime);
        organizer.addEvent(meeting);
        System.out.println("Событие добавлено");
    }
}