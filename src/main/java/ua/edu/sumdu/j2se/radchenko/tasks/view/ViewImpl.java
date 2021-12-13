package ua.edu.sumdu.j2se.radchenko.tasks.view;

import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;

import java.io.IOException;

public class ViewImpl implements View{

    @Override
    public int printInfo(AbstractTaskList taskList) {

        System.out.println("Choose one option");
        System.out.println("\n1 - Add task");
//        System.out.println("2 - Change parameters of task\n");
//        System.out.println("3 - Delete task\n");
//        System.out.println("4 - Check all tasks\n");
//        System.out.println("5 - Check from `time` to `time` tasks\n");
        System.out.println("\n6 - Exit");

        int chosenParam = 0;

        try {
            chosenParam = Integer.parseInt(reader.readLine());
        }catch (IOException | NumberFormatException e){
            return 0;
        }
        return chosenParam;
    }
}
