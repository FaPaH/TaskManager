package ua.edu.sumdu.j2se.radchenko.tasks;

import java.util.Objects;

public class Task implements Cloneable{

    private String title;

    private int time;
    private int start;
    private int end;
    private int interval;

    private boolean active;
    private boolean repeated;

     //adding constuctors for repeating task and non-repeating

    public Task(String title, int time) throws IllegalArgumentException{
        this.title = title;
        this.time = time;
        repeated = false;

        if (time < 0){
            throw new IllegalArgumentException("The time less than 0:" + getTime());
        }
    }

    public Task(String title, int start, int end, int interval) throws IllegalArgumentException {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeated = true;

        if (start < 0){
            throw new IllegalArgumentException("The start time less than 0:" + getStartTime());
        }
    }

    //constuctors for changing params of task

    public void setTime(int start, int end, int interval)  throws IllegalArgumentException{
        if (!isRepeated()) {
            repeated = true;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;

        if (start < 0){
            throw new IllegalArgumentException("The start time less than 0:" + getStartTime());
        }
    }

    public void setTime(int time)  throws IllegalArgumentException{
        if (isRepeated()) {
            repeated = false;
        }
        this.time = time;

        if (time < 0){
            throw new IllegalArgumentException("The time less than 0:" + getTime());
        }
    }

    // getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    public int getStartTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    public int getEndTime() {
        if (isRepeated()) {
            return end;
        }
        return time;
    }

    public int getRepeatInterval() {
        if (isRepeated()) {
            return interval;
        }
        return 0;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public int nextTimeAfter(int current) {
        if (isActive()) {
            if (getStartTime() > current) {
                return getTime();
            }
            if (getStartTime() >= getEndTime()
                    || getRepeatInterval() < 0
                    || current + getRepeatInterval() > getEndTime()) {
                return -1;
            }
            if (getStartTime() <= current) {        //Accepts the start time of the task...
                int time = getStartTime();          //and the current time after which you want to return...
                while (time <= current) {           //the execution time of the next task...
                    time += getRepeatInterval();    //with a specified interval.
                }
                return time;
            }
        } else if (!isActive()) {
            return -1;
        }
        return current;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), isActive(), getTime(), getRepeatInterval(), isRepeated(), getStartTime(), getEndTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return isActive() == task.isActive() &&
                getTime() == task.getTime() &&
                getRepeatInterval() == task.getRepeatInterval() &&
                isRepeated() == task.isRepeated() &&
                getStartTime() == task.getStartTime() &&
                getEndTime() == task.getEndTime() &&
                Objects.equals(getTitle(), task.getTitle());
    }

    public Object clone(){
        try {
            Task copy = (Task) super.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                ", repeated=" + repeated +
                '}';
    }
}
