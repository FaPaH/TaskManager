package ua.edu.sumdu.j2se.radchenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.radchenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.radchenko.tasks.model.Task;
import ua.edu.sumdu.j2se.radchenko.tasks.model.Tasks;
import ua.edu.sumdu.j2se.radchenko.tasks.view.NotificatorView;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class NotificatorController extends Thread{

    private static final Logger logger = Logger.getLogger(NotificatorController.class);

    NotificatorView view;
    private AbstractTaskList taskList;

    public NotificatorController(NotificatorView view, AbstractTaskList taskList) {
        this.view = view;
        this.taskList = taskList;
    }

    @Override
    public void run() {
        while (true){
            SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(taskList, LocalDateTime.now().withSecond(0).withNano(0)
                    , LocalDateTime.now().withSecond(0).withNano(0).plusYears(200));
            for (Map.Entry<LocalDateTime, Set<Task>> element : calendar.entrySet()) {
                for (Task task : element.getValue()) {
                    if (task.isActive()) {
                        if (element.getKey().isEqual(LocalDateTime.now().withSecond(0).withNano(0))) {
                            view.printInfo(element.getKey(), task.getTitle());
                        }
                    }
                }
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e){
                logger.error(e);
            }
        }
    }
}
