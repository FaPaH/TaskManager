package ua.edu.sumdu.j2se.radchenko.tasks;

import ua.edu.sumdu.j2se.radchenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.radchenko.tasks.controller.ControllerImpl;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.view.View;
import ua.edu.sumdu.j2se.radchenko.tasks.view.ViewImpl;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {

		AbstractTaskList taskList = new ArrayTaskList();
		View mainView = new ViewImpl();
		Controller mainController = new ControllerImpl(taskList, mainView);
		mainController.process(null);

	}
}
