package ua.edu.sumdu.j2se.radchenko.tasks.controller;

import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.view.DeleteTaskView;
import ua.edu.sumdu.j2se.radchenko.tasks.view.View;

import java.io.IOException;

public class DeleteTaskController extends Controller{

    public DeleteTaskController(View view, int chosenAction) {
        super(view, chosenAction);
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        int taskChosen = ((DeleteTaskView) view).chosenAction();

        if (taskChosen == 1){
            deleteTask(taskList);
        } else if (taskChosen == 2){
            return MAIN_MENU;
        } else {
            getLogger().error(Errors.WRONG_NUMBER);
            return Controller.DELETE_TASK;
        }

        return view.printInfo(taskList);
    }

    private int deleteTask(AbstractTaskList taskList) {
        int id = ((DeleteTaskView) view).taskId();

        if (id > taskList.size()){
            getLogger().error(Errors.UNEXPECTED_ID);
            return DELETE_TASK;
        }
        taskList.remove(taskList.getTask(id));
        return MAIN_MENU;
    }
}
