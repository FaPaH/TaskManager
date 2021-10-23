package ua.edu.sumdu.j2se.radchenko.tasks;

import java.util.Arrays;

public class LinkedTaskList {

    private int size = 0;
    private Task[] arrayOfTasks = new Task[0];

    public void add(Task task){
        size++;
        arrayOfTasks = Arrays.copyOf(arrayOfTasks, arrayOfTasks.length + size);
        if(task != null){
            for (int i = 0; i < size ;i++){
                if (arrayOfTasks[i] == null) {
                    arrayOfTasks[i] = task;
                    i++;
                }
            }
        }
        else{
            throw new IllegalArgumentException("Task cant be null");
        }
    }

    public boolean remove(Task task){
        if (task != null) {
            for (int i = 0; i < size; i++) {
                if (arrayOfTasks[i].equals(task)) {
                    System.arraycopy(arrayOfTasks, i + 1, arrayOfTasks, i, arrayOfTasks.length - 1 - i);
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    public int size(){
        return size;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (index > arrayOfTasks.length){
            throw new IndexOutOfBoundsException("index cant be more than: " + index);
        }
        return arrayOfTasks[index];
    }

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList linkedTaskList = new LinkedTaskList();
        for (int i = 0; i < size; i++) {
            Task task = arrayOfTasks[i];
            if (task.isActive()) {
                if (task.nextTimeAfter(task.getStartTime()) > from
                        && task.nextTimeAfter(task.getEndTime()) < to
                        && task.getTime() < to) {
                    linkedTaskList.add(arrayOfTasks[i]);
                }
            }
        }
        return linkedTaskList;
    }
}
