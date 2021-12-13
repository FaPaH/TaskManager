package ua.edu.sumdu.j2se.radchenko.tasks.controller;

import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.view.AddTaskView;
import ua.edu.sumdu.j2se.radchenko.tasks.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerImpl extends Controller{

    private AbstractTaskList taskList;
    private List<Controller> controllerList = new ArrayList<>();

    public ControllerImpl(AbstractTaskList taskList, View view) {
        super(view, Controller.MAIN_MENU);
        this.taskList = taskList;

        controllerList.add(this);
        controllerList.add(new AddTaskController(new AddTaskView(), Controller.ADD_TASK));
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        int actionHolder = view.printInfo(taskList);

        if (actionHolder == 1
                || actionHolder == 2
                || actionHolder == 3
                || actionHolder == 4
                || actionHolder == 5
                || actionHolder == 6){
            for (;;){
                for (Controller controller: controllerList) {
                    if (controller.canProcess(actionHolder)) {
                        actionHolder = controller.process(this.taskList);
                    }
                }
                if (actionHolder == EXIT){
                    break;
                }
            }
        }
        return EXIT;
    }
}
