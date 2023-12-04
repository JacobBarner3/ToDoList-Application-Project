package utilities;

import javafx.scene.paint.Color;

/**
 * @author Anupama, Ananmay, Jacob, Spencer
 * This class stores information about a task whether
 * the task is done or not. It allows the getting and setting of its
 * various fields and has a String description which can be set by the
 * user and then displayed to them.
 */
public class Task {
	private String dueDate;
	private String description;
	private boolean done;
	private String title;
	private int color;

	/**
	 * Constructor method.
	 * 
	 * Constructor method that uses its arguments to set the initial value of the
	 * various fields in this class.
	 * 
	 * @param title       A String representing the title of the task.
	 * @param description A String representing the description of the task.
	 * @param dueDate     A Date object representing the date the task is due.
	 */
	public Task(String title, String description, String dueDate) {
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.done = false;
		this.color = 0;
	}

	/**
	 * A getter method.
	 * 
	 * Returns the due date of the task.
	 * 
	 * @return A Date object representing the due date of the task.
	 */
	public String getDueDate() {
		return (dueDate);
	}

	/**
	 * A getter method.
	 * 
	 * Returns the description of the task.
	 * 
	 * @return A String object representing the description of the task.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * A getter method.
	 * 
	 * Returns whether the task is done or not.
	 * 
	 * @return A boolean representing whether the task is done or not.
	 */
	public boolean getDone() {
		return done;
	}

	/**
	 * A setter method.
	 * 
	 * Sets whether the task is done or not.
	 * 
	 * @param done A boolean representing whether the task is done or not.
	 */
	public void setDone(boolean done) {
		this.done = done;
	}

	/**
	 * A getter method.
	 * 
	 * Returns the title of the task.
	 * 
	 * @return A String object representing the title of the task.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method cycles through the color value for the given task.
	 * 
	 * @return the current color of the Task
	 */
	public Color changeColor() {
		if (this.color == 8) {
			this.color = 0;
		} else {
			this.color += 1;
		}
		return (getColor());
	}

	/**
	 * This method checks the current color value of the Task and returns the
	 * designated Color Object.
	 * 
	 * @return The Color Object of the Task
	 */
	public Color getColor() {
		if (this.color == 1) {
			return (Color.BLUE);
		}
		if (this.color == 2) {
			return (Color.GREEN);
		}
		if (this.color == 3) {
			return (Color.YELLOW);
		}
		if (this.color == 4) {
			return (Color.ORANGE);
		}
		if (this.color == 5) {
			return (Color.RED);
		}
		if (this.color == 6) {
			return (Color.HOTPINK);
		}
		if (this.color == 7) {
			return (Color.PURPLE);
		}
		if (this.color == 8) {
			return (Color.CYAN);
		}
		return (Color.GRAY);
	}

	/**
	 * This method returns the associated accent color of the Task, similar to the
	 * getColor() method. The accent color usually represents the color of the
	 * buttons when you hover over them.
	 * 
	 * @return The Accent color of the Task
	 */
	public Color getAccentColor() {
		if (this.color == 1) {
			return (Color.LIGHTBLUE);
		}
		if (this.color == 2) {
			return (Color.LIGHTGREEN);
		}
		if (this.color == 3) {
			return (Color.LIGHTYELLOW);
		}
		if (this.color == 4) {
			return (Color.LIGHTCORAL);
		}
		if (this.color == 5) {
			return (Color.LIGHTPINK);
		}
		if (this.color == 6) {
			return (Color.PINK);
		}
		if (this.color == 7) {
			return (Color.LAVENDER);
		}
		if (this.color == 8) {
			return (Color.LIGHTCYAN);
		}
		return (Color.LIGHTGRAY);
	}
}
