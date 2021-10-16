package ua.edu.sumdu.j2se.radchenko.tasks;

import java.util.Arrays;

public class ArrayTaskList {

    Task[] arrayOfTasks = new Task[0];

    public void add(Task task){
        arrayOfTasks = Arrays.copyOf(arrayOfTasks, arrayOfTasks.length + 1);
        if(task != null){
            for (int i = 0; i < arrayOfTasks.length ;i++){
                if (arrayOfTasks[i] == null) {
                    arrayOfTasks[i] = task;
                    i++;
                }
            }
        }
    }

    public boolean remove(Task task){
        if (task != null) {
            for (int i = 0; i < arrayOfTasks.length; i++) {
                if (arrayOfTasks[i] == task) {
                    System.arraycopy(arrayOfTasks, i + 1, arrayOfTasks, i, arrayOfTasks.length - 1 - i);
                    arrayOfTasks = Arrays.copyOf(arrayOfTasks, arrayOfTasks.length - 1);
                    return true;
                }
            }
        }
        return false;
    }

    public int size(){
        return arrayOfTasks.length;
    }

    public Task getTask(int index){
        if (index > arrayOfTasks.length){
            System.out.println("index out of bounds");
        }
        return arrayOfTasks[index];
    }

    public ArrayTaskList[] incoming(int from, int to){
        ArrayTaskList[] incomingTasks = new Task[0];
        for (int i = 0; i < arrayOfTasks.length; i++) {
            if (arrayOfTasks[i].getStartTime() >= from && arrayOfTasks[i].getEndTime() <= to
                || arrayOfTasks[i].getTime() >= from && arrayOfTasks[i].getTime() <= to) {
                incomingTasks = Arrays.copyOf(incomingTasks, i + 1);
                for (int j = 0; j < i; j++) {
                    incomingTasks[j] = arrayOfTasks[i];
                    j++;
                }
            }
        }
        return incomingTasks;
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "arrayOfTasks=" + Arrays.toString(arrayOfTasks) +
                '}';
    }
}
