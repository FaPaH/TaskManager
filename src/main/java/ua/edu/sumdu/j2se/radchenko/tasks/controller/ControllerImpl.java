package ua.edu.sumdu.j2se.radchenko.tasks.controller;

import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.view.*;

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
        controllerList.add(new CheckAllTaskController(new CheckAllTaskView(), Controller.CHECK_TASK_LIST));
        controllerList.add(new CalendarController(new CalendarView(), Controller.CALENDAR));
        controllerList.add(new SaveLoadController(new SaveLoadView(), Controller.SAVE_LOAD));
        controllerList.add(new DeleteTaskController(new DeleteTaskView(), Controller.DELETE_TASK));
        controllerList.add(new ChangeTaskController(new ChangeTaskView(), Controller.TASK_CHANGE));

        NotificatorController notificator = new NotificatorController(new NotificatorView(), taskList);
        notificator.setDaemon(true);
        notificator.start();
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        int actionHolder = view.printInfo(taskList);

        if (actionHolder == 1
                || actionHolder == 2
                || actionHolder == 3
                || actionHolder == 4
                || actionHolder == 5
                || actionHolder == 6
                || actionHolder == 7){
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
