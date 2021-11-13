package ua.edu.sumdu.j2se.radchenko.tasks;

import java.util.Iterator;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable{

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

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Task task : this) {
            hashCode = 31 * hashCode + (task == null ? 0 : task.hashCode());
        }
        return hashCode;
    }

    @Override
    public Object clone(){
        AbstractTaskList copy = TaskListFactory.createTaskList(getType());
        for (Task task : this) {
            copy.add(task);
        }
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AbstractTaskList){
            AbstractTaskList otherList = (AbstractTaskList) o;
            if (this.size() == 0 && otherList.size() == 0){
                return true;
            } else if (this.size() == otherList.size()) {
                Iterator<Task> list1 = this.iterator();
                Iterator<Task> list2 = otherList.iterator();

                while (list1.hasNext()) {
                    Object e1 = list1.next();
                    Object e2 = list2.next();

                    if (!(e1 == null ? e2 == null : e1.equals(e2))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
