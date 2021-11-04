package ua.edu.sumdu.j2se.radchenko.tasks;

public class AbstractTaskList {

    public void add(Task task) {

    }

    public boolean remove(Task task) {
        return false;
    }

    public int size(){
        return 0;
    }

    public Task getTask(int index) {
        return null;
    }

    public AbstractTaskList incoming(int from, int to) {
        return null;
    }

}
