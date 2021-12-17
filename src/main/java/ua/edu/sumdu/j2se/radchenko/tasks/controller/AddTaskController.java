package ua.edu.sumdu.j2se.radchenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.model.Task;
import ua.edu.sumdu.j2se.radchenko.tasks.view.AddTaskView;
import ua.edu.sumdu.j2se.radchenko.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddTaskController extends Controller{

    public AddTaskController(View view, int chosenAction) {
        super(view, chosenAction);
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        Task task;
        int taskChoose = ((AddTaskView) view).chosenAction();

        if (taskChoose == 1){
            addNonRepeatTask(taskList);
        }
        else if (taskChoose == 2){
            addRepeatTask(taskList);
        }
        else if (taskChoose == 3){
            return Controller.MAIN_MENU;
        } else {
            getLogger().error(Errors.WRONG_NUMBER);
            return Controller.ADD_TASK;
        }

        return view.printInfo(taskList);
    }

    private int addRepeatTask(AbstractTaskList taskList){
        Task task;
        String name = ((AddTaskView) view).taskName();
        LocalDateTime start = ((AddTaskView) view).startTime();
        LocalDateTime end = ((AddTaskView) view).endTime();
        int interval = ((AddTaskView) view).repeatInterval();

        if (end.isBefore(start)){
            getLogger().error(Errors.UNEXPECTED_END_TIME);
            return ADD_TASK;
        }
        if (end.isBefore(LocalDateTime.now())){
            getLogger().error(Errors.UNEXPECTED_END_TIME);
            return ADD_TASK;
        }
        if (interval == Integer.MAX_VALUE || interval < 0){
            getLogger().error(Errors.UNEXPECTED_INTERVAL);
            return ADD_TASK;
        }
        task = new Task(name, start, end, interval);
        task.setActive(true);
        taskList.add(task);
        return ADD_TASK;
    }

    private int addNonRepeatTask(AbstractTaskList taskList){
        Task task;
        String name = ((AddTaskView) view).taskName();
        LocalDateTime time = ((AddTaskView) view).timeTask();

        if (time.isBefore(LocalDateTime.now())){
            getLogger().error(Errors.UNEXPECTED_TIME);
            return ADD_TASK;
        }

        task = new Task(name, time);
        task.setActive(true);
        taskList.add(task);
        return ADD_TASK;
    }
}
