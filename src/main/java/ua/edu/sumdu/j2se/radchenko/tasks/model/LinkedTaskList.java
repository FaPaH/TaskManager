package ua.edu.sumdu.j2se.radchenko.tasks.model;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList implements Iterable<Task>, Cloneable{

    private Node head;
    private Node tail;
    private int size;

    public LinkedTaskList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    static class Node{
        private Task data;
        private Node next;

        public Node(Task data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Task getData() {
            return data;
        }

        public void setData(Task data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public void add(Task task) throws IllegalArgumentException{
        if (task != null) {
            if (isEmpty()) {
                head = new Node(task, null);
                tail = head;
            } else {
                tail.setNext(new Node(task, null));
                tail = tail.getNext();
            }
            size++;
        }
        else{
            throw new IllegalArgumentException("Task cant be null");
        }
    }

    public boolean remove(Task task){
        Node current = head;
        Node previous = null;
        Node beforePrevious = null;

        if (task != null) {
            if (current.getNext() != null) {
                previous = current;
                current = current.getNext();
                while (!task.equals(previous.getData())) {
                    beforePrevious = previous;
                    previous = current;
                    current = current.getNext();
                }
            }
            if (beforePrevious == null){
                head = current;
            } else if (current == null){
                tail = beforePrevious;
            } else {
                beforePrevious.setNext(current);
                previous.setNext(null);
            }
            size--;
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException("index cant be more than: " + index);
        }
        if (index < size() - 1){
            Node current = head;
            for(int j = 0; j < index; j++){
                current = current.getNext();
            }
            return current.getData();
        }
        return tail.getData();
    }

    public ListTypes.types getType() {
        return ListTypes.types.LINKED;
    }


    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> iterator = new Iterator<Task>() {
            Node current = head;
            Node previous = null;
            Node beforePrevious = null;
            boolean removeCall = false;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Task next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                Task tas = current.getData();
                beforePrevious = previous;
                previous = current;
                current = current.getNext();
                removeCall = false;
                return tas;
            }

            @Override
            public void remove() {
                if (previous == null || removeCall) {
                    throw new IllegalStateException();
                }
                if (beforePrevious == null) {
                    head = current;
                } else {
                    beforePrevious.setNext(current);
                }
                size--;
                removeCall = true;
            }
        };
        return iterator;
    }

    public Stream<Task> getStream(){
        Task[] arrayOfTasks = new Task[this.size()];
        for (int i = 0; i < this.size(); i++) {
            arrayOfTasks[i] = this.getTask(i);
        }
        return Stream.of(arrayOfTasks);
    }

    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }
}
