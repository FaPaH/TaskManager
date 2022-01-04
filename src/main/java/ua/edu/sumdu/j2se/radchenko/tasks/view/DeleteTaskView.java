package ua.edu.sumdu.j2se.radchenko.tasks.view;

import ua.edu.sumdu.j2se.radchenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.radchenko.tasks.controller.Errors;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.time.LocalDateTime;

public class DeleteTaskView implements View, Action{

    @Override
    public int chosenAction() {
        System.out.println("Choose option");
        System.out.println("1 - delete, 2 - go back to manu");
        int chosenAction = 0;

        try {
            chosenAction = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e){
            return -1;
        }
        return chosenAction;
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Task deleted");
        return Controller.DELETE_TASK;
    }

    public int taskId(){
        System.out.print("Enter id task what you want delete: ");
        int id = 0;
        try {
            id = Integer.parseInt(reader.readLine());
        }catch (IOException | NumberFormatException e){
            logger.error(Errors.UNEXPECTED_DATA);
        }
        return id;
    }
}
