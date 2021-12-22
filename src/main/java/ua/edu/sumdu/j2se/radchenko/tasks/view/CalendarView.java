package ua.edu.sumdu.j2se.radchenko.tasks.view;

import ua.edu.sumdu.j2se.radchenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.radchenko.tasks.controller.Errors;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.model.Task;
import ua.edu.sumdu.j2se.radchenko.tasks.model.Tasks;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class CalendarView implements View, Action{


    @Override
    public int chosenAction() {
        System.out.println("Choose option");
        System.out.println("1 - Check tasks from - to, 2 - go back to main menu");
        int chosenAction = 0;
        try {
            chosenAction = Integer.parseInt(reader.readLine());
        }catch (IOException | NumberFormatException e){
            return -1;
        }
        return chosenAction;
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        LocalDateTime startTime = startTime();

        if (startTime.isEqual(Errors.START_EPOCH)){
            logger.error(Errors.UNEXPECTED_START_TIME);
            System.out.println("Wrong start time");
            return Controller.CALENDAR;
        }

        LocalDateTime endTime = endTime();

        if (endTime.isEqual(Errors.START_EPOCH)){
            logger.error(Errors.UNEXPECTED_END_TIME);
            System.out.println("Wrong end time");
            return Controller.CALENDAR;
        }

        if (endTime.isBefore(LocalDateTime.now())){
            logger.error(Errors.UNEXPECTED_END_TIME);
            System.out.println("Wrong end time");
            return Controller.CALENDAR;
        }

        SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(taskList, startTime, endTime);
        if (calendar.size() != 0) {
            for (Map.Entry<LocalDateTime, Set<Task>> element : calendar.entrySet()) {
                for (Task task : element.getValue()) {
                    System.out.println("Title: " + task.getTitle() + " - ");
                }
                System.out.println("Time: " + element.getKey() + "\n");
            }
        } else {
            System.out.println("On this time you didnt have tasks");
        }
        return Controller.MAIN_MENU;
    }

    public LocalDateTime startTime(){
        System.out.print("Enter the start time for the task (example yyyy-MM-dd HH:mm = 2021-12-13 12:30): ");
        return stringToTime();
    }

    public LocalDateTime endTime(){
        System.out.print("Enter the end time for the task (example yyyy-MM-dd HH:mm = 2021-12-13 12:30): ");
        return stringToTime();
    }


    private LocalDateTime stringToTime(){
        String date = "null";
        LocalDateTime timeHolder = null;

        try {
            date = reader.readLine();
        }catch (IOException | NumberFormatException e){
            logger.error(Errors.UNEXPECTED_DATA);
        }

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            timeHolder = LocalDateTime.parse(date, dateTimeFormatter);
        }catch (DateTimeParseException e){
            return LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC);
        }
        return timeHolder;
    }
}
