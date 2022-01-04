package ua.edu.sumdu.j2se.radchenko.tasks.view;

import ua.edu.sumdu.j2se.radchenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;

import java.time.LocalDateTime;

public class NotificatorView{
    public int printInfo(LocalDateTime time, String name) {

        System.out.println("-----------------ALARM------------------------");
        System.out.println("Task " + name + "need to be done in time: " + time);
        System.out.println("-----------------ALARM------------------------");

        return Controller.MAIN_MENU;
    }
}
