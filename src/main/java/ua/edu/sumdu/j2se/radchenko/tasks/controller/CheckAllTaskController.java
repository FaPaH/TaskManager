package ua.edu.sumdu.j2se.radchenko.tasks.controller;

import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.view.View;

import java.io.IOException;

public class CheckAllTaskController extends  Controller{

    public CheckAllTaskController(View view, int chosenAction) {
        super(view, chosenAction);
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        return view.printInfo(taskList);
    }
}
