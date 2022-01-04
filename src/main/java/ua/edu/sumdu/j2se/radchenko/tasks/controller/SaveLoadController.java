package ua.edu.sumdu.j2se.radchenko.tasks.controller;

import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.model.Task;
import ua.edu.sumdu.j2se.radchenko.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.radchenko.tasks.view.SaveLoadView;
import ua.edu.sumdu.j2se.radchenko.tasks.view.View;

import java.io.*;

public class SaveLoadController extends Controller{

    private final static String SAVE_DIRECTORY = "saves";
    private final static String FILE_FORMAT = ".json";

    public SaveLoadController(View view, int chosenAction) {
        super(view, chosenAction);
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        int taskChoose = ((SaveLoadView) view).chosenAction();

        File directory = new File(SAVE_DIRECTORY);
        directory.mkdir();

        if (taskChoose == 1){
            if (Save(taskList)){
                return view.printInfo(taskList);
            } else {
                ((SaveLoadView) view).fileNotFound();
            }
        }
        else if (taskChoose == 2){
            if (Load(taskList)){
                return view.printInfo(taskList);
            } else {
                ((SaveLoadView) view).fileNotFound();
            }
        }
        else if (taskChoose == 3){
            return MAIN_MENU;
        } else {
            getLogger().error(Errors.WRONG_NUMBER);
            return Controller.SAVE_LOAD;
        }

        return Controller.MAIN_MENU;
    }

    private boolean Save(AbstractTaskList taskList){
        try {
            String nameFile = ((SaveLoadView) view).fileName();
            File saveFile = new File(SAVE_DIRECTORY + "/" + nameFile + FILE_FORMAT);
            TaskIO.write(taskList, new FileWriter(saveFile));
        } catch (IOException e){
            getLogger().error(Errors.FILE_NOT_FOUND);
            return false;
        }
        return true;
    }

    private boolean Load(AbstractTaskList taskList){
        try {
            String nameFile = ((SaveLoadView) view).fileName();
            File saveFile = new File(SAVE_DIRECTORY + "/" + nameFile + FILE_FORMAT);
            TaskIO.read(taskList, new FileReader(saveFile));
        } catch (IOException e){
            getLogger().error(Errors.FILE_NOT_FOUND);
            return false;
        }
        return true;
    }
}
