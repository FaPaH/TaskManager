package ua.edu.sumdu.j2se.radchenko.tasks.controller;

import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.model.Task;
import ua.edu.sumdu.j2se.radchenko.tasks.view.AddTaskView;
import ua.edu.sumdu.j2se.radchenko.tasks.view.ChangeTaskView;
import ua.edu.sumdu.j2se.radchenko.tasks.view.DeleteTaskView;
import ua.edu.sumdu.j2se.radchenko.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;

public class ChangeTaskController extends Controller{

    public ChangeTaskController(View view, int chosenAction) {
        super(view, chosenAction);
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        int taskChosen = ((ChangeTaskView) view).chosenAction();

        if (taskChosen == 1){
            changeTask(taskList);
        } else if (taskChosen == 2){
            changeActive(taskList);
        } else if (taskChosen == 3){
            return MAIN_MENU;
        } else {
            getLogger().error(Errors.WRONG_NUMBER);
            return Controller.DELETE_TASK;
        }

        return view.printInfo(taskList);
    }

    private int changeActive(AbstractTaskList taskList){
        int taskId = ((ChangeTaskView) view).taskId();
        if (taskId < taskList.size()) {
            int isActive = ((ChangeTaskView) view).choseTaskActive();
            if (isActive == 1) {
                taskList.getTask(taskId).setActive(true);
            }
            else if (isActive == 2){
                taskList.getTask(taskId).setActive(false);
            } else {
                getLogger().error(Errors.WRONG_NUMBER);
                return Controller.TASK_CHANGE;
            }
        } else {
            getLogger().error(Errors.UNEXPECTED_ID);
            return TASK_CHANGE;
        }
        return Controller.MAIN_MENU;
    }

    private int changeTask(AbstractTaskList taskList){
        int taskId = ((ChangeTaskView) view).taskId();
        if (taskId < taskList.size()) {
            Task currentTask = taskList.getTask(taskId);
            if (taskList.getTask(taskId).isRepeated()) {
                int repTask = ((ChangeTaskView) view).repTask();
                if (repTask == 1) {
                    return changeName(currentTask);
                }
                else if (repTask == 2){
                    return changeRepTaskTime(currentTask);
                }
            } else {
                int nonRepTask = ((ChangeTaskView) view).nonRepTask();
                if (nonRepTask == 1) {
                    return changeName(currentTask);
                }
                else if (nonRepTask == 2){
                    return changeNonRepTaskTime(currentTask);
                }
            }
        } else {
            getLogger().error(Errors.UNEXPECTED_ID);
            return TASK_CHANGE;
        }
        return Controller.MAIN_MENU;
    }

    private int changeName(Task task){
        Task currentTask = task;
        ((ChangeTaskView) view).printCurrentName(task);
        String newName = ((ChangeTaskView) view).newTaskName();
        currentTask.setTitle(newName);
        return MAIN_MENU;
    }

    private int changeRepTaskTime(Task task){
        Task currentTask = task;
        ((ChangeTaskView) view).printCurrentStartTime(task);
        LocalDateTime newStartTime = ((ChangeTaskView) view).newTaskStartTime();

        if (newStartTime.isEqual(Errors.START_EPOCH)){
            getLogger().error(Errors.UNEXPECTED_START_TIME);
            ((ChangeTaskView) view).wrongStartTime();
            return TASK_CHANGE;
        }

        ((ChangeTaskView) view).printCurrentEndTime(task);
        LocalDateTime newEndTime = ((ChangeTaskView) view).newTaskEndTime();

        if (newEndTime.isEqual(Errors.START_EPOCH)){
            getLogger().error(Errors.UNEXPECTED_END_TIME);
            ((ChangeTaskView) view).wrongStartTime();
            return TASK_CHANGE;
        }

        ((ChangeTaskView) view).printCurrentInterval(task);
        int newInterval = ((ChangeTaskView) view).newTaskInterval();

        if (newInterval == Integer.MAX_VALUE || newInterval < 0){
            getLogger().error(Errors.UNEXPECTED_INTERVAL);
            ((ChangeTaskView) view).wrongInterval();
            return TASK_CHANGE;
        }

        if (newStartTime.isAfter(newEndTime)){
            getLogger().error(Errors.UNEXPECTED_START_TIME);
            ((ChangeTaskView) view).wrongStartTime();
            return TASK_CHANGE;
        }

        if (newEndTime.isBefore(LocalDateTime.now())){
            getLogger().error(Errors.UNEXPECTED_END_TIME);
            ((ChangeTaskView) view).wrongEndTime();
            return TASK_CHANGE;
        }
        currentTask.setTime(newStartTime, newEndTime, newInterval);
        return MAIN_MENU;
    }

    private int changeNonRepTaskTime(Task task){
        Task currentTask = task;
        ((ChangeTaskView) view).printCurrentTime(task);
        LocalDateTime newTime = ((ChangeTaskView) view).newTaskTime();

        if (newTime.isEqual(Errors.START_EPOCH)){
            getLogger().error(Errors.UNEXPECTED_TIME);
            ((ChangeTaskView) view).wrongTime();
            return ADD_TASK;
        }

        if (newTime.isBefore(LocalDateTime.now())){
            getLogger().error(Errors.UNEXPECTED_TIME);
            ((ChangeTaskView) view).wrongTime();
            return ADD_TASK;
        }

        currentTask.setTime(newTime);
        return MAIN_MENU;
    }
}
