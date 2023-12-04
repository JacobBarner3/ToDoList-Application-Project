package application;

import java.util.ArrayList;
import java.util.List;

import controller.ToDoListController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import model.ToDoListModel;
import utilities.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author Spencer Wollard, Jacob Barner, Ananmay Rajan, Anupama Hazarika
 * 
 *         Project: To Do List (Final Project)
 * 
 *         Description: This application allows users to create and use To Do
 *         Lists. The user is able to create multiple separate To Do Lists, as
 *         well as add as many tasks to each list as they like. Each task and
 *         list are individually removable and modifiable. This application
 *         utilizes JavaFX to create a functional graphical user interface for
 *         the user to interact with the program.
 *
 *         Class Description: This class is the View component of the
 *         application. This class is responsible for creating, drawing, and
 *         managing the GUI for the user to interact with. The View frequently
 *         calls various methods of the Controller in order to influence the
 *         Model, which tracks and updates the state of the application, which
 *         is then displayed via the View.
 */

public class ToDoList extends Application {
	private int MAIN_WIDTH = 500;
	private int MAIN_HEIGHT = 620;
	private Stage stage;
	private Scene mainScene;
	private Scene listScene;
	private Scene taskScene;
	private String currentList;
	private String currentTask;
	private ToDoListController controller;
	private Scene confirmRemoveTask;
	private Scene confirmRemoveList;

	/**
	 * This function is the standard start() method for JavaFX programs. It is
	 * responsible for displaying the application.
	 *
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			this.controller = new ToDoListController();
			stage.setTitle("To-Do List");
			stage.getIcons().add(new Image("file:icon.png"));
			createMainScene(0);

			stage.setScene(mainScene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates and displays the primary page for the application. The
	 * primary page displays all of the current To Do Lists to the user, as well as
	 * allows for lists to be added and removed.
	 * 
	 * @param start The starting index for the current page of lists from the
	 *              ListOfLists.
	 */
	public void createMainScene(int start) {
		BorderPane mainRoot = new BorderPane();
		this.mainScene = new Scene(mainRoot, MAIN_WIDTH, MAIN_HEIGHT);
		ArrayList<ToDoListModel> allLists = controller.getListOfList();
		List<ToDoListModel> lists = allLists.subList(start, allLists.size());
		Text header1 = new Text("To-Do Lists");
		header1.setX(220);
		header1.setY(20);
		Text header2 = new Text("Your Lists");
		header2.setX(80);
		header2.setY(50);
		Rectangle addList = new Rectangle();
		addList.setX(400);
		addList.setY(30);
		addList.setFill(Color.GRAY);
		addList.setWidth(50);
		addList.setHeight(40);
		addList.setOnMouseClicked((event) -> {
			addList();
		});
		addList.setOnMouseEntered((event) -> {
			addList.setFill(Color.LIGHTGRAY);
		});
		addList.setOnMouseExited((event) -> {
			addList.setFill(Color.GRAY);
		});
		Text addText = new Text("+");
		addText.setX(412);
		addText.setY(60);
		addText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		addText.setOnMouseClicked((event) -> {
			addList();
		});
		addText.setOnMouseEntered((event) -> {
			addList.setFill(Color.LIGHTGRAY);
		});
		addText.setOnMouseExited((event) -> {
			addList.setFill(Color.GRAY);
		});

		Rectangle previous = new Rectangle();
		previous.setFill(Color.GRAY);
		previous.setWidth(80);
		previous.setHeight(30);
		previous.setY(580);
		previous.setX(10);
		previous.setOnMouseClicked((event) -> {
			if (start != 0) {
				createMainScene(start - 10);
				stage.setScene(mainScene);
			}
		});
		previous.setOnMouseEntered((event) -> {
			previous.setFill(Color.LIGHTGRAY);
		});
		previous.setOnMouseExited((event) -> {
			previous.setFill(Color.GRAY);
		});

		Text previousText = new Text("< Previous");
		previousText.setX(18);
		previousText.setY(600);
		previousText.setOnMouseClicked((event) -> {
			if (start != 0) {
				createMainScene(start - 10);
				stage.setScene(mainScene);
			}
		});
		previousText.setOnMouseEntered((event) -> {
			previous.setFill(Color.LIGHTGRAY);
		});
		previousText.setOnMouseExited((event) -> {
			previous.setFill(Color.GRAY);
		});

		Rectangle next = new Rectangle();
		next.setFill(Color.GRAY);
		next.setWidth(80);
		next.setHeight(30);
		next.setY(580);
		next.setX(410);
		next.setOnMouseClicked((event) -> {
			if ((start + 10) < lists.size()) {
				createMainScene(start + 10);
				stage.setScene(mainScene);
			}
		});
		next.setOnMouseEntered((event) -> {
			next.setFill(Color.LIGHTGRAY);
		});
		next.setOnMouseExited((event) -> {
			next.setFill(Color.GRAY);
		});

		Text nextText = new Text("Next >");
		nextText.setX(435);
		nextText.setY(600);
		nextText.setOnMouseEntered((event) -> {
			next.setFill(Color.LIGHTGRAY);
		});
		nextText.setOnMouseExited((event) -> {
			next.setFill(Color.GRAY);
		});
		nextText.setOnMouseClicked((event) -> {
			if ((start + 10) < lists.size()) {
				createMainScene(start + 10);
				stage.setScene(mainScene);
			}
		});

		mainRoot.getChildren().addAll(header1, addList, header2, addText, next, nextText, previous, previousText);

		int counter = 0;
		int x = 10;
		int y = 80;

		// Iterates over all existing lists, and draws each list on the page as well as
		// a corresponding remove button for each list.
		for (ToDoListModel current : lists) {
			if (counter == 10) {
				break;
			}
			Rectangle temp = new Rectangle();
			temp.setHeight(40);
			temp.setWidth(200);
			temp.setX(x);
			temp.setY(y);
			temp.setFill(Color.GRAY);
			temp.setOnMouseClicked((event) -> {
				currentList = current.getTitle();
				createListScene(0);
				stage.setScene(listScene);

			});
			temp.setOnMouseEntered((event) -> {
				temp.setFill(Color.LIGHTGRAY);
			});
			temp.setOnMouseExited((event) -> {
				temp.setFill(Color.GRAY);
			});
			Text tempText = new Text(current.getTitle());
			tempText.setX(25);
			tempText.setY(y + 25);
			tempText.setOnMouseClicked((event) -> {
				currentList = current.getTitle();
				createListScene(0);
				stage.setScene(listScene);

			});
			tempText.setOnMouseEntered((event) -> {
				temp.setFill(Color.LIGHTGRAY);
			});
			tempText.setOnMouseExited((event) -> {
				temp.setFill(Color.GRAY);
			});

			Rectangle remove = new Rectangle();
			remove.setX(400);
			remove.setY(y);
			remove.setWidth(50);
			remove.setHeight(40);
			remove.setFill(Color.GRAY);
			remove.setOnMouseEntered((event) -> {
				remove.setFill(Color.LIGHTGRAY);
			});
			remove.setOnMouseExited((event) -> {
				remove.setFill(Color.GRAY);
			});
			remove.setOnMouseClicked((event) -> {
				createRemoveList(current);
				stage.setScene(confirmRemoveList);
			});

			Text removeText = new Text("-");
			removeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
			removeText.setX(417);
			removeText.setY(y + 30);
			removeText.setOnMouseEntered((event) -> {
				remove.setFill(Color.LIGHTGRAY);
			});
			removeText.setOnMouseExited((event) -> {
				remove.setFill(Color.GRAY);
			});
			removeText.setOnMouseClicked((event) -> {
				createRemoveList(current);
				stage.setScene(confirmRemoveList);
			});
			mainRoot.getChildren().addAll(temp, tempText, remove, removeText);
			y += 50;
			counter += 1;
		}

	}

	/**
	 * This method creates and displays a list page for the application. This page
	 * displays a selected list along with all corresponding tasks on the selected
	 * list. Each task is 'clickable', providing a Title, Due By, and short
	 * description of the task. The user can cycle the color of the task by clicking
	 * the color button in the task page.
	 * 
	 * @param start The starting index for the current page of tasks from the
	 *              ToDoList.
	 */
	public void createListScene(int start) {
		ToDoListModel currList = controller.getList(currentList);
		BorderPane listRoot = new BorderPane();
		this.listScene = new Scene(listRoot, MAIN_WIDTH, MAIN_HEIGHT);
		Text listTitle = new Text("Items To-Do");
		listTitle.setX(10);
		listTitle.setY(65);

		Text dates = new Text("Due:");
		dates.setX(220);
		dates.setY(65);

		Text completed = new Text("Completed?");
		completed.setX(310);
		completed.setY(65);
		Rectangle back = new Rectangle();
		back.setHeight(30);
		back.setWidth(60);
		back.setX(10);
		back.setY(10);
		back.setFill(Color.GRAY);
		back.setOnMouseClicked((event) -> {
			stage.setScene(mainScene);
		});
		back.setOnMouseEntered((event) -> {
			back.setFill(Color.LIGHTGRAY);
		});
		back.setOnMouseExited((event) -> {
			back.setFill(Color.GRAY);
		});
		Text backText = new Text("< Back");
		backText.setX(20);
		backText.setY(30);
		backText.setOnMouseClicked((event) -> {
			stage.setScene(mainScene);
		});
		backText.setOnMouseEntered((event) -> {
			back.setFill(Color.LIGHTGRAY);
		});
		backText.setOnMouseExited((event) -> {
			back.setFill(Color.GRAY);
		});
		BorderPane taskslist = new BorderPane();

		Rectangle addTask = new Rectangle();
		addTask.setX(400);
		addTask.setY(30);
		addTask.setFill(Color.GRAY);
		addTask.setWidth(50);
		addTask.setHeight(40);
		addTask.setOnMouseClicked((event) -> {
			addTask();
		});
		addTask.setOnMouseEntered((event) -> {
			addTask.setFill(Color.LIGHTGRAY);
		});
		addTask.setOnMouseExited((event) -> {
			addTask.setFill(Color.GRAY);
		});
		Text addTaskText = new Text("+");
		addTaskText.setX(412);
		addTaskText.setY(60);
		addTaskText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		addTaskText.setOnMouseClicked((event) -> {
			addTask();
		});
		addTaskText.setOnMouseEntered((event) -> {
			addTask.setFill(Color.LIGHTGRAY);
		});
		addTaskText.setOnMouseExited((event) -> {
			addTask.setFill(Color.GRAY);
		});

		Text sorterText = new Text();
		sorterText.setX(130);
		sorterText.setY(30);
		if (controller.getList(currentList).getSortedVal() == 0) {
			sorterText.setText("A -> Z");
		} else {
			sorterText.setText("By Completed");
		}
		Rectangle sorter = new Rectangle();
		sorter.setWidth(130);
		sorter.setHeight(30);
		sorter.setX(120);
		sorter.setY(10);
		sorter.setFill(Color.GRAY);
		Text sortPrompt = new Text("Sort:");
		sortPrompt.setX(90);
		sortPrompt.setY(30);
		sorter.setOnMouseClicked((event) -> {
			if (controller.getList(currentList).getSortedVal() == 0) {
				controller.getList(currentList).sortByTitle();
				controller.getList(currentList).changeSortedVal();
			} else {
				controller.getList(currentList).sortByCompleted();
				controller.getList(currentList).changeSortedVal();
			}
			createListScene(0);
			stage.setScene(listScene);
		});
		sorterText.setOnMouseClicked((event) -> {
			if (controller.getList(currentList).getSortedVal() == 0) {
				controller.getList(currentList).sortByTitle();
				controller.getList(currentList).changeSortedVal();
				sorterText.setText("By Completed");
			} else {
				controller.getList(currentList).sortByCompleted();
				controller.getList(currentList).changeSortedVal();
				sorterText.setText("A -> Z");
			}
			createListScene(0);
			stage.setScene(listScene);
		});
		sorter.setOnMouseEntered((event) -> {
			sorter.setFill(Color.LIGHTGRAY);
		});
		sorter.setOnMouseExited((event) -> {
			sorter.setFill(Color.GRAY);
		});
		sorterText.setOnMouseEntered((event) -> {
			sorter.setFill(Color.LIGHTGRAY);
		});
		sorterText.setOnMouseExited((event) -> {
			sorter.setFill(Color.GRAY);
		});
		Rectangle previous = new Rectangle();
		previous.setFill(Color.GRAY);
		previous.setWidth(80);
		previous.setHeight(30);
		previous.setY(580);
		previous.setX(10);
		previous.setOnMouseClicked((event) -> {
			if (start != 0) {
				createListScene(start - 10);
				stage.setScene(listScene);
			}
		});
		previous.setOnMouseEntered((event) -> {
			previous.setFill(Color.LIGHTGRAY);
		});
		previous.setOnMouseExited((event) -> {
			previous.setFill(Color.GRAY);
		});

		Text previousText = new Text("< Previous");
		previousText.setX(18);
		previousText.setY(600);
		previousText.setOnMouseClicked((event) -> {
			if (start != 0) {
				createListScene(start - 10);
				stage.setScene(listScene);
			}
		});
		previousText.setOnMouseEntered((event) -> {
			previous.setFill(Color.LIGHTGRAY);
		});
		previousText.setOnMouseExited((event) -> {
			previous.setFill(Color.GRAY);
		});

		Rectangle next = new Rectangle();
		next.setFill(Color.GRAY);
		next.setWidth(80);
		next.setHeight(30);
		next.setY(580);
		next.setX(410);
		next.setOnMouseClicked((event) -> {
			if ((start + 10) < currList.getTaskListSize()) {
				createListScene(start + 10);
				stage.setScene(listScene);
			}
		});
		next.setOnMouseEntered((event) -> {
			next.setFill(Color.LIGHTGRAY);
		});
		next.setOnMouseExited((event) -> {
			next.setFill(Color.GRAY);
		});

		Text nextText = new Text("Next >");
		nextText.setX(435);
		nextText.setY(600);
		nextText.setOnMouseEntered((event) -> {
			next.setFill(Color.LIGHTGRAY);
		});
		nextText.setOnMouseExited((event) -> {
			next.setFill(Color.GRAY);
		});
		nextText.setOnMouseClicked((event) -> {
			if ((start + 10) < currList.getTaskListSize()) {
				createListScene(start + 10);
				stage.setScene(listScene);
			}
		});

		List<Task> thisPage = currList.getTasks().subList(start, currList.getTaskListSize());
		int counter = 0;
		int x = 10;
		int y = 80;
		for (Task current : thisPage) {
			if (counter == 10) {
				break;
			}
			Rectangle temp = new Rectangle();
			temp.setHeight(40);
			temp.setWidth(200);
			temp.setX(x);
			temp.setY(y);
			temp.setFill(current.getColor());
			temp.setOnMouseClicked((event) -> {
				this.currentTask = current.getTitle();
				createTaskScene();
				stage.setScene(taskScene);
			});
			temp.setOnMouseEntered((event) -> {
				temp.setFill(current.getAccentColor());
			});
			temp.setOnMouseExited((event) -> {
				temp.setFill(current.getColor());
			});
			Text tempText = new Text(current.getTitle());
			tempText.setX(25);
			tempText.setY(y + 25);
			tempText.setOnMouseClicked((event) -> {
				this.currentTask = current.getTitle();
				createTaskScene();
				stage.setScene(taskScene);
			});
			tempText.setOnMouseEntered((event) -> {
				temp.setFill(current.getAccentColor());
			});
			tempText.setOnMouseExited((event) -> {
				temp.setFill(current.getColor());
			});
			Text date = new Text(current.getDueDate());
			date.setX(x + 210);
			date.setY(y + 25);

			Rectangle complete = new Rectangle();
			complete.setX(322);
			complete.setY(y);
			complete.setWidth(40);
			complete.setHeight(40);
			if (current.getDone()) {
				complete.setFill(Color.rgb(55, 207, 50));
			} else {
				complete.setFill(Color.rgb(237, 97, 97));
			}
			complete.setOnMouseClicked((event) -> {
				current.setDone(!current.getDone());
				createListScene(0);
				stage.setScene(listScene);
			});

			Rectangle remove = new Rectangle();
			remove.setX(400);
			remove.setY(y);
			remove.setWidth(50);
			remove.setHeight(40);
			remove.setFill(Color.GRAY);
			remove.setOnMouseEntered((event) -> {
				remove.setFill(Color.LIGHTGRAY);
			});
			remove.setOnMouseExited((event) -> {
				remove.setFill(Color.GRAY);
			});
			remove.setOnMouseClicked((event) -> {
				createRemoveTask(current);
				stage.setScene(confirmRemoveTask);
			});

			Text removeText = new Text("-");
			removeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
			removeText.setX(417);
			removeText.setY(y + 30);
			removeText.setOnMouseEntered((event) -> {
				remove.setFill(Color.LIGHTGRAY);
			});
			removeText.setOnMouseExited((event) -> {
				remove.setFill(Color.GRAY);
			});
			removeText.setOnMouseClicked((event) -> {
				createRemoveTask(current);
				stage.setScene(confirmRemoveTask);
			});
			taskslist.getChildren().addAll(temp, tempText, date, complete, remove, removeText);
			y += 50;
			counter += 1;
		}
		taskslist.getChildren().addAll(back, backText, listTitle, dates, completed, addTask, addTaskText, sorter,
				sorterText, sortPrompt, previous, previousText, next, nextText);
		listRoot.getChildren().add(taskslist);
	}

	/**
	 * This method displays a page that allows the user to add a list as well as the
	 * first task on the list. The input is retrieved from the user using the
	 * TextField class of JavaFX.
	 * 
	 */
	public void addList() {

		VBox newList = new VBox();
		HBox confirmation = new HBox();
		newList.setSpacing(10);
		newList.setAlignment(Pos.CENTER);
		Scene defineList = new Scene(newList, MAIN_WIDTH, MAIN_HEIGHT);
		Text setName = new Text("Enter the name of your new To-Do List");
		TextField name = new TextField();
		name.setMinHeight(20);
		name.setMinWidth(200);
		name.setMaxWidth(200);

		Text firstTask = new Text("Enter the name of your first task in this List");
		TextField initialTaskName = new TextField();
		initialTaskName.setMinHeight(20);
		initialTaskName.setMinWidth(200);
		initialTaskName.setMaxWidth(200);

		Text dueDate = new Text("Enter when you want to have this completed by:");
		TextField initialDueDate = new TextField();
		initialDueDate.setMinHeight(20);
		initialDueDate.setMinWidth(90);
		initialDueDate.setMaxWidth(90);

		Text newDescription = new Text("Enter a short description of the Task:");
		TextField description = new TextField();
		description.setMinHeight(20);
		description.setMinWidth(200);
		description.setMaxWidth(200);

		newList.getChildren().addAll(setName, name, firstTask, initialTaskName, dueDate, initialDueDate, newDescription,
				description, confirmation);
		Button confirm = new Button("Add");
		confirm.setMinHeight(30);
		confirm.setMinWidth(70);
		confirm.setStyle("-fx-background-color: #808080");
		confirmation.setAlignment(Pos.CENTER);
		confirm.setOnMouseClicked((event) -> {
			if (name.getText() == null || initialTaskName.getText() == null || initialDueDate.getText() == null
					|| description.getText() == null) {

			} else if (name.getText() == "" || initialTaskName.getText() == "" || initialDueDate.getText() == ""
					|| description.getText() == "") {

			} else {
				controller.addList(name.getText(), initialTaskName.getText(), description.getText(),
						initialDueDate.getText());
				createMainScene(0);
				stage.setScene(mainScene);
			}
		});
		confirm.setOnMouseEntered((event) -> {
			confirm.setStyle("-fx-background-color: #b0b0b0");
		});
		confirm.setOnMouseExited((event) -> {
			confirm.setStyle("-fx-background-color: #808080");
		});
		confirmation.getChildren().addAll(confirm);
		stage.setScene(defineList);

	}

	/**
	 * This method displays a page that allows the user to add a new task to an
	 * existing list. This page prompts the user for a task name, as well as a due
	 * by and description of the task.
	 * 
	 */
	public void addTask() {
		ToDoListModel currList = controller.getList(currentList);
		VBox newTask = new VBox();
		HBox confirmation = new HBox();
		newTask.setSpacing(10);
		newTask.setAlignment(Pos.CENTER);
		Scene defineTask = new Scene(newTask, MAIN_WIDTH, MAIN_HEIGHT);
		Text setName = new Text("Enter the name of your new Task");
		TextField name = new TextField();
		name.setMinHeight(20);
		name.setMinWidth(200);
		name.setMaxWidth(200);

		Text newDescription = new Text("Enter the description of your Task");
		TextField description = new TextField();
		description.setMinHeight(20);
		description.setMinWidth(200);
		description.setMaxWidth(200);

		Text initialDate = new Text("Enter when you want to have this completed by:");
		TextField dueDate = new TextField();
		dueDate.setMinHeight(20);
		dueDate.setMinWidth(90);
		dueDate.setMaxWidth(90);

		newTask.getChildren().addAll(setName, name, initialDate, dueDate, newDescription, description, confirmation);
		Button confirm = new Button("Add");
		confirm.setMinHeight(30);
		confirm.setMinWidth(70);
		confirm.setAlignment(Pos.CENTER);
		confirm.setStyle("-fx-background-color: #808080");
		confirmation.setAlignment(Pos.CENTER);
		confirm.setOnMouseClicked((event) -> {
			if (name.getText() == null || description.getText() == null || dueDate.getText() == null) {

			} else if (name.getText() == "" || description.getText() == "" || dueDate.getText() == "") {

			} else {
				currList.addTask(name.getText(), description.getText(), dueDate.getText());
				createListScene(0);
				stage.setScene(listScene);
			}
		});

		confirm.setOnMouseEntered((event) -> {
			confirm.setStyle("-fx-background-color: #606060");
		});
		confirm.setOnMouseExited((event) -> {
			confirm.setStyle("-fx-background-color: #808080");
		});
		confirmation.getChildren().addAll(confirm);
		stage.setScene(defineTask);
	}

	/**
	 * This method displays the individual task page. This page shows the name of
	 * the selected task, as well as a Due By, and description of the task. This
	 * page allows the user to customize the color of the task via the color button.
	 * 
	 */
	public void createTaskScene() {
		Task currTask = controller.getList(currentList).getTask(currentTask);
		BorderPane taskRoot = new BorderPane();
		this.taskScene = new Scene(taskRoot, MAIN_WIDTH, MAIN_HEIGHT);
		Rectangle back = new Rectangle();
		back.setHeight(30);
		back.setWidth(60);
		back.setX(10);
		back.setY(10);
		back.setFill(Color.GRAY);
		back.setOnMouseClicked((event) -> {
			stage.setScene(listScene);
		});
		back.setOnMouseEntered((event) -> {
			back.setFill(Color.LIGHTGRAY);
		});
		back.setOnMouseExited((event) -> {
			back.setFill(Color.GRAY);
		});
		Text backText = new Text("< Back");
		backText.setX(20);
		backText.setY(30);
		backText.setOnMouseClicked((event) -> {
			stage.setScene(listScene);
		});
		backText.setOnMouseEntered((event) -> {
			back.setFill(Color.LIGHTGRAY);
		});
		backText.setOnMouseExited((event) -> {
			back.setFill(Color.GRAY);
		});

		Rectangle color = new Rectangle();
		color.setWidth(60);
		color.setHeight(30);
		color.setX(400);
		color.setY(10);
		color.setFill(currTask.getColor());
		color.setOnMouseClicked((event) -> {
			currTask.changeColor();
			createTaskScene();
			createListScene(0);
			stage.setScene(taskScene);
		});

		Text colorText = new Text("Color");
		colorText.setX(416);
		colorText.setY(30);
		colorText.setOnMouseClicked((event) -> {
			currTask.changeColor();
			createTaskScene();
			createListScene(0);
			stage.setScene(taskScene);
		});
		Color currColor = currTask.getColor();
		Text title = new Text(currTask.getTitle());
		title.setX(20);
		title.setY(80);
		title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		Text dateHeader = new Text("Due By: ");
		dateHeader.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		dateHeader.setX(20);
		dateHeader.setY(120);
		Text dueDate = new Text(currTask.getDueDate().toString());
		dueDate.setX(20);
		dueDate.setY(140);
		Text descriptionHeader = new Text("Description: ");
		descriptionHeader.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		descriptionHeader.setX(20);
		descriptionHeader.setY(180);
		Text taskDescription = new Text(currTask.getDescription());
		taskDescription.setX(20);
		taskDescription.setY(200);
		taskDescription.setWrappingWidth(400);
		if (currColor != Color.GRAY) {
			title.setFill(currColor);
			dateHeader.setFill(currColor);
			dueDate.setFill(currColor);
			descriptionHeader.setFill(currColor);
			taskDescription.setFill(currColor);
		}
		taskRoot.getChildren().addAll(back, backText, title, dateHeader, dueDate, descriptionHeader, taskDescription,
				color, colorText);
	}

	/**
	 * This method displays a confirmation page which prompts the user with a Yes
	 * and a No button, asking if they wish to delete the current task.
	 * 
	 * @param task: The current task being removed.
	 */
	public void createRemoveTask(Task task) {
		BorderPane removeRoot = new BorderPane();
		confirmRemoveTask = new Scene(removeRoot, MAIN_WIDTH, MAIN_HEIGHT);
		VBox elements = new VBox();
		Text title = new Text("Are you sure you want to delete this Task?");

		HBox buttons = new HBox();
		Button yes = new Button("Yes");
		yes.setMinSize(60, 30);
		Button no = new Button("No");
		no.setMinSize(60, 30);

		yes.setOnMouseClicked((event) -> {
			// Calls the controller to get the current list in view, and removes the passed
			// Task from it.
			controller.getList(currentList).removeToDo(task);
			createListScene(0);
			stage.setScene(listScene);
		});

		no.setOnMouseClicked((event) -> {
			stage.setScene(listScene);
		});

		buttons.getChildren().addAll(yes, no);
		buttons.setSpacing(60);
		buttons.setAlignment(Pos.CENTER);
		buttons.setTranslateX(22);
		elements.getChildren().addAll(title, buttons);

		elements.setSpacing(40);
		elements.setTranslateX(140);
		elements.setTranslateY(200);

		removeRoot.getChildren().add(elements);

	}

	/**
	 * This method displays a confirmation page which prompts the user with a Yes
	 * and a No button, asking if they wish to delete the current list.
	 * 
	 * @param list: Current list being removed
	 */
	public void createRemoveList(ToDoListModel list) {
		BorderPane removeRoot = new BorderPane();
		confirmRemoveList = new Scene(removeRoot, MAIN_WIDTH, MAIN_HEIGHT);
		VBox elements = new VBox();
		Text title = new Text("Are you sure you want to delete this List?");

		HBox buttons = new HBox();
		Button yes = new Button("Yes");
		yes.setMinSize(60, 30);
		Button no = new Button("No");
		no.setMinSize(60, 30);

		yes.setOnMouseClicked((event) -> {
			controller.removeList(list);
			createMainScene(0);
			stage.setScene(mainScene);
		});

		no.setOnMouseClicked((event) -> {
			stage.setScene(mainScene);
		});

		buttons.getChildren().addAll(yes, no);
		buttons.setSpacing(60);
		buttons.setAlignment(Pos.CENTER);
		buttons.setTranslateX(22);
		elements.getChildren().addAll(title, buttons);

		elements.setSpacing(40);
		elements.setTranslateX(140);
		elements.setTranslateY(200);

		removeRoot.getChildren().add(elements);

	}

	public static void main(String[] args) {
		launch(args);
	}
}