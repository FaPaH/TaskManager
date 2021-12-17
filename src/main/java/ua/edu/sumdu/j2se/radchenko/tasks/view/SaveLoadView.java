package ua.edu.sumdu.j2se.radchenko.tasks.view;

import ua.edu.sumdu.j2se.radchenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;

import java.io.IOException;

public class SaveLoadView implements Action, View{

    private int checker;

    @Override
    public int chosenAction() {
        System.out.println("Choose option");
        System.out.println("1 - save, 2 - load, 3 - go back to manu");
        int chosenAction = 0;

        try {
            chosenAction = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e){
            return -1;
        }
        this.checker = chosenAction;
        return chosenAction;
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (checker == 1){
            System.out.println("File saved");
            return Controller.MAIN_MENU;
        } else {
            System.out.println("File loaded");
        }
        return Controller.MAIN_MENU;
    }

    public String fileName(){
        String fileName = "";
        try {
            System.out.println("Type name file");
            fileName = reader.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        return fileName;
    }
}
