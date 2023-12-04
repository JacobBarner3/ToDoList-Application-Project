package model;

import java.util.ArrayList;
import java.util.List;

import utilities.Task;
/**
 * 
 * @author Jacob, Spencer, Anupama, Ananmay
 * This class stores information about a list of list of tasks 
 * and has methods to add and remove lists. It has a method to
 * return the lists and methods to add/remove tasks from lists.
 */
public class ListOfListsModel {
	private ArrayList<ToDoListModel> lists;
	private List<Task> tasks;
	
	/**
	 * Constructor method.
	 * 
	 * Constructor method that initialises the list of lists.
	 */
	public ListOfListsModel() {
		this.lists = new ArrayList<ToDoListModel>();
		this.tasks = new ArrayList<Task>();
	}
	
	/**
	 * Method to add a list.
	 * 
	 * Public method which adds a new list to the list of lists 
	 * with a first task in the list as well.
	 * @param name A String representing the name of the list.
	 * @param task A String representing the first task.
	 */
	public void addList(ToDoListModel list) {
		lists.add(list);
	}

	/**
	 * Method that returns the list of lists.
	 * 
	 * Public method that returns the list of lists.
	 * @return An ArrayList<ToDoListModel> object that represents the
	 * list of lists.
	 */
	public ArrayList<ToDoListModel> getListOfList() {
		return lists;
	}

	/**
	 * Adds a task.
	 * 
	 * Method that adds a task to tasks. 
	 * @param task A Task object representing a task.
	 */
	public void addTask(Task task) {
		tasks.add(task);
		
	}

	/**
	 * Removes a list.
	 * 
	 * Public method that removes a list from the list of lists.
	 * @param list A ToDoListModel object that represents one of 
	 * the lists. 
	 */
	public void removeList(ToDoListModel list) {
		lists.remove(list);
		
	}

	
	/**
	 * Returns a specific list.
	 * 
	 * Public method that returns a specific list from the lists of lists.
	 * @param name A String representing the name of the specific list.
	 * @return A ToDoListModel object representing the specific list. 
	 */
	public ToDoListModel getList(String name) {
		for(ToDoListModel list : lists) {
			if(list.getTitle() == name) {
				return(list);
			}
		}
		return(null);
	}
}		