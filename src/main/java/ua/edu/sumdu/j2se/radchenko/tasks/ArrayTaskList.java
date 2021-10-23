package ua.edu.sumdu.j2se.radchenko.tasks;

import java.util.Arrays;

public class ArrayTaskList {

    private int size = 0;
    private Task[] arrayOfTasks = new Task[10];

    public void add(Task task) throws IllegalArgumentException{
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
        if (index > size){
            throw new IndexOutOfBoundsException("index cant be more than: " + index);
        }
        return arrayOfTasks[index];
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        for (int i = 0; i < size; i++) {
            Task task = arrayOfTasks[i];
            if (task.isActive()) {
                if (task.nextTimeAfter(task.getStartTime()) > from
                        && task.nextTimeAfter(task.getEndTime()) < to
                        && task.getTime() < to) {
                        arrayTaskList.add(arrayOfTasks[i]);
                }

                // I think this variant what if commented is better

//            if (arrayOfTasks[i].isActive()) {
//                if (arrayOfTasks[i].getStartTime() > from && arrayOfTasks[i].getEndTime() < to
//                        || arrayOfTasks[i].getTime() > from && arrayOfTasks[i].getTime() < to) {
//                    arrayTaskList.add(arrayOfTasks[i]);
//                }
//            }

            }
        }
        return arrayTaskList;
    }
}
