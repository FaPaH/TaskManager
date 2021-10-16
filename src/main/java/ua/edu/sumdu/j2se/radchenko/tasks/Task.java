package ua.edu.sumdu.j2se.radchenko.tasks;

public class Task {

    private String title;

    private int time;
    private int start;
    private int end;
    private int interval;

    private boolean active;
    private boolean repeated;

     //adding constuctors for repeating task and non-repeating

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        repeated = false;
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeated = true;
    }

    //constuctors for changing params of task

    public void setTime(int start, int end, int interval) {
        if (!isRepeated()) {
            repeated = true;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public void setTime(int time) {
        if (isRepeated()) {
            repeated = false;
        }
        this.time = time;
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
