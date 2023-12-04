package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import utilities.Task;

public class TaskTesting {
	
	Task task;
	
	@Before
	public void setUp() {
		task = new Task("Task 1", "This is the description.", "11pm on Sunday");
	}

	@Test
	public void test() {
		assertEquals("11pm on Sunday", task.getDueDate());
	}
	
	@Test
	public void test1() {
		assertEquals("This is the description.", task.getDescription());
	}
	
	@Test
	public void test2() {
		assertEquals(false, task.getDone());
	}
	
	@Test
	public void test3() {
		task.setDone(true);
		assertEquals(true, task.getDone());
	}
	
	@Test
	public void test4() {
		assertEquals("Task 1", task.getTitle());
	}
	
	@Test
	public void test5() {
		assertEquals(Color.BLUE, task.changeColor());
		assertEquals(Color.LIGHTBLUE, task.getAccentColor());
		assertEquals(Color.GREEN, task.changeColor());
		assertEquals(Color.LIGHTGREEN, task.getAccentColor());
		assertEquals(Color.YELLOW, task.changeColor());
		assertEquals(Color.LIGHTYELLOW, task.getAccentColor());
		assertEquals(Color.ORANGE, task.changeColor());
		assertEquals(Color.LIGHTCORAL, task.getAccentColor());
		assertEquals(Color.RED, task.changeColor());
		assertEquals(Color.LIGHTPINK, task.getAccentColor());
		assertEquals(Color.HOTPINK, task.changeColor());
		assertEquals(Color.PINK, task.getAccentColor());
		assertEquals(Color.PURPLE, task.changeColor());
		assertEquals(Color.LAVENDER, task.getAccentColor());
		assertEquals(Color.CYAN, task.changeColor());
		assertEquals(Color.LIGHTCYAN, task.getAccentColor());
		assertEquals(Color.GRAY, task.changeColor());
		assertEquals(Color.LIGHTGRAY, task.getAccentColor());
	}

}
