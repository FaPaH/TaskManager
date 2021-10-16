package ua.edu.sumdu.j2se.radchenko.tasks;

public class Main {


	public static void main(String[] args) {
        Task task = new Task("karl", 10,20, 3);
        ArrayTaskList array = new ArrayTaskList();
        array.add(task);
        array.incoming(5, 50);

		System.out.println();
	}


}
