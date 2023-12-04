package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ToDoListController;
import model.ToDoListModel;

public class ToDoControllerTesting {

	
	ToDoListController controller;
	
	@Before
	public void setUp() {
		controller = new ToDoListController();
	}

	@Test
	public void test_getListOfList_empty_list() {
		List<ToDoListModel> list = controller.getListOfList();
		assertEquals(0, list.size());
	}
	
	@Test
	public void test_getListOfList_add_list() {
		controller.addList("AA", "BB", "BBBBBBBBBBB", "6pm");
		ToDoListModel toDoListModel = controller.getList("AA");
		assertEquals(1, toDoListModel.getTaskListSize());
	}
	
	@Test
	public void test_3() {
		controller.addList("AA", "BB", "BBBBBBBBBBB", "6pm");
		List<ToDoListModel> list = controller.getListOfList();
		assertEquals(1, list.size());
	}
	
	@Test
	public void test_4() {
		controller.addList("AA", "BB", "BBBBBBBBBBB", "6pm");
		controller.addList("AAA", "BB", "BBBBBBBBBBB", "6pm");
		List<ToDoListModel> list = controller.getListOfList();
		assertEquals(2, list.size());
	}
	
	@Test
	public void test_getListOfList_add_list_5() {
		controller.addList("AA", "BB", "BBBBBBBBBBB", "6pm");
		controller.addList("AA", "BBSF", "BBBBBBBBBBB", "6pm");
		ToDoListModel toDoListModel = controller.getList("AA");
		assertEquals(2, toDoListModel.getTaskListSize());
	}
	
	
	
}
