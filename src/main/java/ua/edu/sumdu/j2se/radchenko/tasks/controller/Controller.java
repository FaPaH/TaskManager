package ua.edu.sumdu.j2se.radchenko.tasks.controller;

import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.view.View;

import java.io.IOException;

public abstract class Controller {
    public static final int MAIN_MENU = 0;
    public static final int ADD_TASK = 1;
    public static final int TASK_CHANGE = 2;
    public static final int DELETE_TASK = 3;
    public static final int CHECK_TASK_LIST = 4;
    public static final int CHECK_FROM_TO = 5;
    public static final int EXIT = 6;

    protected View view;
    protected int chosenAction;

    public Controller(View view, int chosenAction) {
        this.view = view;
        this.chosenAction = chosenAction;
    }

    public boolean canProcess(int action){
        return action == chosenAction;
    }

    public abstract int process(AbstractTaskList taskList) throws IOException;
}
