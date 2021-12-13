package ua.edu.sumdu.j2se.radchenko.tasks.view;

import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface View {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public int printInfo(AbstractTaskList taskList);
}
