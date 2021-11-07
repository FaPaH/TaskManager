package ua.edu.sumdu.j2se.radchenko.tasks;

public abstract class AbstractTaskList {

    public void add(Task task) {}

    public boolean remove(Task task) {
        return false;
    }

    public int size(){
        return 0;
    }

    public Task getTask(int index) {
        return null;
    }

    public abstract ListTypes.types getType();

    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList list = TaskListFactory.createTaskList(getType());
        Task task;
        for (int i = 0; i < list.size(); i++) {
            task = list.getTask(i);
            if (task.isActive()) {
                if (task.nextTimeAfter(task.getStartTime()) > from
                        && task.nextTimeAfter(task.getEndTime()) < to
                        && task.getTime() < to) {
                    list.add(task);
                }
            }
        }
        return list;
    }
}
