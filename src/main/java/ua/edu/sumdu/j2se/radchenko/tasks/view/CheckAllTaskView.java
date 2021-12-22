package ua.edu.sumdu.j2se.radchenko.tasks.view;

import ua.edu.sumdu.j2se.radchenko.tasks.controller.Constant;
import ua.edu.sumdu.j2se.radchenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.model.Task;

public class CheckAllTaskView implements View{

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Journal of tasks");
        int id = 0;

        if (taskList.size() == 0){
            System.out.println("Task list is empty");
        } else {
            for (Task task : taskList) {
                if (task.isRepeated()) {
                    System.out.println("id: " + id++
                            + ". Task name: " + task.getTitle()
                            + ". Start time: " + task.getStartTime()
                            + ". End time: " + task.getEndTime()
                            + ". Interval: every " + task.getRepeatInterval()/Constant.SECONDS_60 + " minutes"
                            + ". Is active: " + task.isActive());
                } else {
                    System.out.println("id: " + id++
                            + ". Task name: " + task.getTitle()
                            + ". Start time: " + task.getTime()
                            + ". Is active: " + task.isActive());
                }
            }
        }

        return Controller.MAIN_MENU;
    }
}
