package ua.edu.sumdu.j2se.radchenko.tasks.view;

import ua.edu.sumdu.j2se.radchenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.radchenko.tasks.controller.Errors;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddTaskView implements View, Action{

    @Override
    public int printInfo(AbstractTaskList taskList) {
        return Controller.ADD_TASK;
    }

    @Override
    public int chosenAction() {
        System.out.println("Chose task what you want to add");
        System.out.println("1 - non repeat, 2 - repeat, 3 - go back to main menu");
        int chosenAction = 0;
        try {
            chosenAction = Integer.parseInt(reader.readLine());
        }catch (IOException | NumberFormatException e){
            return -1;
        }
        return chosenAction;
    }

    public String taskName(){
        System.out.println("Type task name: ");
        String name = "null";
        try {
            name = reader.readLine();
        }catch (IOException e){
            logger.error(Errors.UNEXPECTED_DATA);
        }
        return name;
    }

    public void success(){
        System.out.println("Task is added to list");
    }

    public void unSuccess(){
        System.out.println("Task is NOT added to list");
    }

    public LocalDateTime timeTask(){
        System.out.print("Enter the due date for the task (example yyyy-MM-dd HH:mm = 2021-12-13 12:30): ");
        return stringToTime();
    }


    public LocalDateTime startTime(){
        System.out.print("Enter the start time for the task (example yyyy-MM-dd HH:mm = 2021-12-13 12:30): ");
        return stringToTime();
    }

    public LocalDateTime endTime(){
        System.out.print("Enter the end time for the task (example yyyy-MM-dd HH:mm = 2021-12-13 12:30): ");
        return stringToTime();
    }

    public void wrongStartTime(){
        System.out.println("Wrong start time, expected 2021-12-13 12:30");
    }

    public void wrongEndTime(){
        System.out.println("Wrong start time, expected 2021-12-13 12:30");
    }

    public void wrongTime(){
        System.out.println("Wrong start time, expected 2021-12-13 12:30");
    }

    public void wrongInterval(){
        System.out.println("Wrong interval, please type numbers with out letters");
    }

    public int repeatInterval(){
        System.out.println("Enter repeat interval of task in minutes");
        int intervalHolder = 0;
        try {
            intervalHolder = Integer.parseInt(reader.readLine());
        }catch (IOException | NumberFormatException e){
            intervalHolder = Integer.MAX_VALUE;
        }
        return intervalHolder;
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
            return LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusSeconds(0).minusNanos(0);
        }
        return timeHolder;
    }
}
