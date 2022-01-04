package ua.edu.sumdu.j2se.radchenko.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.radchenko.tasks.controller.NotificatorController;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface View {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Logger logger = Logger.getLogger(NotificatorController.class);

    public int printInfo(AbstractTaskList taskList);
}
