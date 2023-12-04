package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.ToDoListModel;

public class ToDoListModelTesting {
	
	ToDoListModel model;
	
	@Before
	public void setUp() {
		model = new ToDoListModel("List 1");
	}

	@Test
	public void test() {
		assertEquals(true, model.isEmpty());
		model.addTask("Task 1", "This is the description.", "11pm on Sunday");
		assertEquals("Task 1", model.getTasks().get(0).getTitle());
		assertEquals(1, model.getTaskListSize());
		assertEquals(false, model.isEmpty());
		assertEquals("List 1", model.getTitle());
		assertEquals("Task 1", model.getTask("Task 1").getTitle());
		assertEquals(null, model.getTask("Task 2"));
	    model.removeToDo(model.getTask("Task 1"));
		assertEquals(0, model.getTaskListSize());
	}
	
	@Test
	public void test1() {
		model.addTask("Task 1", "Description 1", "11pm on Sunday");
		model.addTask("Task 2", "Description 2", "11pm on Sunday");
		model.getTask("Task 1").setDone(true);
		model.sortByCompleted();
		assertEquals("Task 2", model.getTasks().get(0).getTitle());
		model.sortByTitle();
		assertEquals("Task 1", model.getTasks().get(0).getTitle());
		assertEquals(0, model.getSortedVal());
		model.changeSortedVal();
		assertEquals(1, model.getSortedVal());
		model.changeSortedVal();
		assertEquals(0, model.getSortedVal());
	}
}
