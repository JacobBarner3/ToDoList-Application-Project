package controller;
import java.util.ArrayList;

import model.ListOfListsModel;
import model.ToDoListModel;
import utilities.Task;

/**
 * * @author Ananmay, Jacob, Spencer, Anupama
 * ToDoListController class acts as Controller part of MVC design pattern. 
 * 
 * This class is an interface between model and view part.  It receives request 
 * from View and process the request with the help of model and sends back the 
 * response to View.   
 *
 */
public class ToDoListController {

	private ListOfListsModel listOfListsModel;

	/**
	 * Controller of the ToDoListController class. 
	 * If No argument is passes it creates an instance for ListOfListsModel class 
	 */
	public ToDoListController() {
		this.listOfListsModel  = new ListOfListsModel(); 
	}
	
	/**
	 * getListOfList method is used to get list of all TODO item.
	 * @return List of ToDoListModel.
	 */
	public ArrayList<ToDoListModel> getListOfList() {
		return this.listOfListsModel.getListOfList();
	}


	/**
	 * addTask method is used to add new Task or new TODO  item .
	 * it will create a instance of the Task class and it will forward 
	 * the task object to ListOfListModel for adding to the list. 
	 * @param name represents name of the task
	 * @param description represents description of TODO task
	 * @param dueDate represents due date of TOTO task
	 * @param done represents status of the TODO task
	 */
	public void addTask(String name, String description, String dueDate) {
		Task task = new Task(name, description, dueDate);
	
		listOfListsModel.addTask(task);
		
	}


	/**
	 * addList method used to add new group of TODO list items.
	 * @param name name of group of task
	 * @param task represents task object need to be added
	 */
	public void addList(String name, String taskName, String description, String dueDate) {
		ToDoListModel newList = new ToDoListModel(name);
		newList.addTask(taskName, description, dueDate);
		listOfListsModel.addList(newList);
		
	}
	
	/**
	 * removeList method helps in deleting the all TODO tasks of a particular group.
	 * @param list represents list of TODO item need to be removed.
	 */
	public void removeList(ToDoListModel list) {
		listOfListsModel.removeList(list);
	}
	
	/**
	 * getList method helps in getting the list of TODO items based on the particular group 
	 * @param name represents group name
	 * @return instance of ToDoListModel
	 */
	public ToDoListModel getList(String name) {
		return(listOfListsModel.getList(name));
	}
}