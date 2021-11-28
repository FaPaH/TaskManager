package ua.edu.sumdu.j2se.radchenko.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.StreamSupport;

public class Tasks {

    public static Iterable<Task>  incoming(Iterable<Task>  tasks, LocalDateTime start, LocalDateTime end){
        return new Iterable<Task>() {
            @Override
            public Iterator<Task> iterator() {
                return StreamSupport.stream(tasks.spliterator(), false).filter(task ->
                        task.nextTimeAfter(start) != null
                        && (end.isAfter(task.nextTimeAfter(start))
                        || end.isEqual(task.nextTimeAfter(start)))).iterator();
            }
        };
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task>  tasks, LocalDateTime  start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();

        for (Task task : tasks) {
            if (task.isActive()){
                LocalDateTime holder = task.nextTimeAfter(start.minusSeconds(1));
                while (holder != null && !holder.isAfter(end)) {
                    if (calendar.containsKey(holder)) {
                        calendar.get(holder).add(task);
                    } else {
                        Set<Task> taskSet = new HashSet<>();
                        taskSet.add(task);
                        calendar.put(holder, taskSet);
                    }
                    holder = task.nextTimeAfter(holder);
                }
            }
        }
        return calendar;
    }
}
