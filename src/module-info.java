module ToDoList {
	requires javafx.controls;
	requires javafx.graphics;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
