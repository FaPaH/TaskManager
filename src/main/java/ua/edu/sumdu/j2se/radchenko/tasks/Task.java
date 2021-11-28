package ua.edu.sumdu.j2se.radchenko.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable, Serializable {

    private String title;

    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;

    private boolean active;
    private boolean repeated;

     //adding constuctors for repeating task and non-repeating

    public Task(String title, LocalDateTime time) throws IllegalArgumentException{
        this.title = title;
        this.time = time;
        repeated = false;

        if (time == null){
            throw new IllegalArgumentException("Time is null");
        }
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeated = true;

        if (start == null){
            throw new IllegalArgumentException("Time is null");
        }
    }

    //constuctors for changing params of task

    public void setTime(LocalDateTime start, LocalDateTime end, int interval)  throws IllegalArgumentException{
        if (!isRepeated()) {
            repeated = true;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;

        if (start == null){
            throw new IllegalArgumentException("Time is null");
        }
    }

    public void setTime(LocalDateTime time)  throws IllegalArgumentException{
        if (isRepeated()) {
            repeated = false;
        }
        this.time = time;

        if (time == null){
            throw new IllegalArgumentException("Time is null");
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

    public LocalDateTime getTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    public LocalDateTime getStartTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    public LocalDateTime getEndTime() {
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

    public LocalDateTime nextTimeAfter(LocalDateTime nextTask) {
        if (isActive()) {
            if (!isRepeated() && nextTask.isBefore(getTime())) {
                return getTime();
            }
            if (isRepeated()) {
                LocalDateTime time = getStartTime();
                while (time.isBefore(nextTask) || time.isEqual(nextTask)) {
                    if (getEndTime().isBefore(getStartTime())
                            || getRepeatInterval() < 0
                            || time.plusSeconds(getRepeatInterval()).isAfter(getEndTime())) {
                        return null;
                    }
                    time = time.plusSeconds(getRepeatInterval());
                }
                return time;
            }
        }
        return null;
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
