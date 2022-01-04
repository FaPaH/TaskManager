package ua.edu.sumdu.j2se.radchenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.model.Task;
import ua.edu.sumdu.j2se.radchenko.tasks.view.AddTaskView;
import ua.edu.sumdu.j2se.radchenko.tasks.view.ChangeTaskView;
import ua.edu.sumdu.j2se.radchenko.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddTaskController extends Controller{

    public AddTaskController(View view, int chosenAction) {
        super(view, chosenAction);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int taskChoose = ((AddTaskView) view).chosenAction();

        if (taskChoose == 1){
            if(addNonRepeatTask(taskList) == MAIN_MENU){
                ((AddTaskView) view).success();
            } else {
                ((AddTaskView) view).unSuccess();
            }
        }
        else if (taskChoose == 2){
            if(addRepeatTask(taskList) == MAIN_MENU){
                ((AddTaskView) view).success();
            } else {
                ((AddTaskView) view).unSuccess();
            }
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

        if (start.isEqual(Constant.START_EPOCH)){
            getLogger().error(Errors.UNEXPECTED_START_TIME);
            ((AddTaskView) view).wrongStartTime();
            return ADD_TASK;
        }

        LocalDateTime end = ((AddTaskView) view).endTime();

        if (end.isEqual(Constant.START_EPOCH)){
            getLogger().error(Errors.UNEXPECTED_END_TIME);
            ((AddTaskView) view).wrongEndTime();
            return ADD_TASK;
        }

        int interval = ((AddTaskView) view).repeatInterval() * Constant.SECONDS_60;

        if (interval < 0){
            getLogger().error(Errors.UNEXPECTED_INTERVAL);
            ((AddTaskView) view).wrongInterval();
            return ADD_TASK;
        }
        if (end.isBefore(start)){
            getLogger().error(Errors.UNEXPECTED_END_TIME);
            ((AddTaskView) view).wrongStartTime();
            return ADD_TASK;
        }
        if (end.isBefore(LocalDateTime.now())){
            getLogger().error(Errors.UNEXPECTED_END_TIME);
            ((AddTaskView) view).wrongEndTime();
            return ADD_TASK;
        }

        task = new Task(name, start, end, interval);
        task.setActive(true);
        taskList.add(task);
        return MAIN_MENU;
    }

    private int addNonRepeatTask(AbstractTaskList taskList){
        Task task;
        String name = ((AddTaskView) view).taskName();
        LocalDateTime time = ((AddTaskView) view).timeTask();

        if (time.isEqual(Constant.START_EPOCH)){
            getLogger().error(Errors.UNEXPECTED_TIME);
            ((AddTaskView) view).wrongTime();
            return ADD_TASK;
        }

        if (time.isBefore(LocalDateTime.now())){
            getLogger().error(Errors.UNEXPECTED_TIME);
            ((AddTaskView) view).wrongTime();
            return ADD_TASK;
        }

        task = new Task(name, time);
        task.setActive(true);
        taskList.add(task);
        return MAIN_MENU;
    }
}
