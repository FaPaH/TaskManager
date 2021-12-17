package ua.edu.sumdu.j2se.radchenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.view.AddTaskView;
import ua.edu.sumdu.j2se.radchenko.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.radchenko.tasks.view.View;

import java.io.IOException;

public class CalendarController extends Controller{

    public CalendarController(View view, int chosenAction) {
        super(view, chosenAction);
    }

    @Override
    public int process(AbstractTaskList taskList) {

        int taskChoose = ((CalendarView) view).chosenAction();

        if (taskChoose == 1){
            return view.printInfo(taskList);
        } else if (taskChoose == 2){
            return Controller.MAIN_MENU;
        } else {
            getLogger().error(Errors.WRONG_NUMBER);
            return Controller.CALENDAR;
        }
    }
}
