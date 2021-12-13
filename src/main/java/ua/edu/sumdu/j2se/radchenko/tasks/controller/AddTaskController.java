package ua.edu.sumdu.j2se.radchenko.tasks.controller;

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
        int taskAddChoose = ((AddTaskView) view).chosenAction();

        if (taskAddChoose == 1){
            addNonRepeatTask(taskList);
        }
        else if (taskAddChoose == 2){
            addRepeatTask(taskList);
        }
        else if (taskAddChoose == 3){
            return Controller.MAIN_MENU;
        } else {
            System.out.println("Wrong number");
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
            System.out.println("Unexpected end time");
            return ADD_TASK;
        }
        if (end.isBefore(LocalDateTime.now())){
            System.out.println("Unexpected end time");
            return ADD_TASK;
        }
        if (interval == Integer.MAX_VALUE || interval < 0){
            System.out.println("Unexpected interval");
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
            System.out.println("Unexpected time");
            return ADD_TASK;
        }

        task = new Task(name, time);
        task.setActive(true);
        taskList.add(task);
        return ADD_TASK;
    }
}
