package ua.edu.sumdu.j2se.radchenko.tasks;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.stream.Stream;

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

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        AbstractTaskList list = TaskListFactory.createTaskList(getType());
        this.getStream().filter(task -> task != null
        && task.isActive()
        && task.nextTimeAfter(from) == null
        && task.getEndTime().isBefore(to)).forEach(list::add);
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

    public abstract Stream<Task> getStream();
}
