package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.ListOfListsModel;
import model.ToDoListModel;
import utilities.Task;

public class ListOfListsModelTesting {
	
	ListOfListsModel lists;
	
	@Before
	public void setUp() {
		lists = new ListOfListsModel();
	}

	@Test
	public void test() {
		ToDoListModel model = new ToDoListModel("List 1");
		Task task = new Task("Task 1", "Description 1", "11pm on Sunday");
		ToDoListModel model2 = new ToDoListModel("List 2");
		lists.addList(model);
		assertEquals(model, lists.getListOfList().get(0));
		lists.addTask(task);
		lists.addList(model2);
		assertEquals(model, lists.getList("List 1"));
		assertEquals(null, lists.getList("List 3"));
		lists.removeList(model);
		assertEquals(1, lists.getListOfList().size());
		assertEquals(model2, lists.getListOfList().get(0));
	}

}
