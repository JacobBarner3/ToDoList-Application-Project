package model;

import java.util.ArrayList;
import java.util.List;

import utilities.Task;

/**
 * 
 * @author Anupama, Ananmay, Jacob, Spencer
 * This class stores information about a list of tasks 
 * and has methods to add, remove, and update tasks. It
 * allows the getting and setting of its various fields
 * and has a String description which can be set by the user
 * and then displayed to them. 
 */
public class ToDoListModel {
	private List<Task> tasks;
	private String title;
	private int sorted;

	/**
	 * Constructor method.
	 * 
	 * Constructor method that sets the value of the title and initializes
	 * the arraylist storing the tasks. 
	 * @param title
	 */
	public ToDoListModel(String title) {
		this.title = title;
		tasks = new ArrayList<Task>();
		this.sorted = 0;
	}

	/**
	 * A getter method. 
	 * 
	 * Returns the array of tasks.
	 * @return A List<Task> object representing the list of tasks. 
	 */
	public List<Task> getTasks(){
		return tasks;
	}

	/**
	 * Adds a task to the list. 
	 * 
	 * Adds a new task to the list. 
	 * @param task A Task object representing a single task. 
	 */
	public void addTask(String name, String description, String dueDate) {
		Task newTask = new Task(name, description, dueDate);
		tasks.add(newTask);
	}

	/**
	 * Returns whether the list is empty or not.
	 * 
	 * Returns a boolean representing whether the list is empty or not
	 * based on its size.
	 * @return A boolean representing whether the list is empty or not.
	 */
	public boolean isEmpty() {
		if (tasks.size() == 0)
			return true;
		return false;
	}

	/**
	 * Getter method.
	 * 
	 * Getter method that returns the title of the list. 
	 * @return A String representing the title of the list. 
	 */
	public String getTitle() {
		return title;
	}

	//providing two methods to remove an item, by using whole task object as arguments
	/**
	 * Removes a task from the list.
	 * 
	 * Removes a task from the list. 
	 * @param task A Task object representing a specific task. 
	 */
	public void removeToDo(Task task) {
		tasks.remove(task);
	}

	 
	/**
	 * getTask method returns the task object corresponding 
	 * to the title.
	 * @param name representing the task title.
	 * @return A Task object representing the task. 
	 */
	public Task getTask(String name) {
		for(Task task : tasks) {
			if(task.getTitle() == name) {
				return(task);
			}
		}
		return(null);
	}

	/**
	 * Getter method.
	 * 
	 * Getter method that returns the size of the task list. 
	 * @return An int representing the size of the task list. 
	 */
	public int getTaskListSize() {
		return tasks.size();
	}

	/**
	 * Sorting method that sorts the task item according to the task status.
	 */
	public void sortByCompleted() {
		List<Task> complete = new ArrayList<Task>();
		List<Task> incomplete = new ArrayList<Task>();
		
		for(Task curr : tasks) {
			if(curr.getDone()) {
				complete.add(curr);
			}
			else {
				incomplete.add(curr);
			}
		}
		incomplete.addAll(complete);
		tasks = incomplete;
	}
	
	/**
	 * Sorting method that sorts according to the name of the task. 
	 * 
	 * Sorting method that sorts the tasks according to the name of the task.
	 */
	public void sortByTitle() {
		tasks.sort((d1,d2) -> d1.getTitle().toLowerCase().compareTo(d2.getTitle().toLowerCase()));
	}
	
	/**
	 * Returns the sorted value. 
	 * 
	 * Returns 0 and 1 based on value of sorted. 
	 * @return An int representing the sorted value. 
	 */
	public int getSortedVal() {
		return(this.sorted);
	}
	
	/**
	 * Changes the sorted value. 
	 * 
	 * Sets sorted to be either 1 or 0 if it was the other 
	 * before. 
	 */
	public void changeSortedVal() {
		if(getSortedVal() == 0) {
			this.sorted = 1;
		}
		else {
			this.sorted = 0;
		}
	}
}