This application allows users to create and use To Do Lists.  The user is able to create multiple 
separate To Do Lists, as well as add as many tasks to each list as they like.  There is no limit 
to the number of lists that can be created, and each list can have an infinite amount of tasks.  
Each task and list are individually removable.  Each task has a corresponding 'Complete' button.
Once a task has been completed, the user can highlight it as complete.  Tasks can be colored using the 'Color' 
button.  The tasks can be sorted in two different ways, alphabetically, and by completed.  
If the user chooses to sort the tasks by completed, all completed tasks will be moved to the bottom 
of the list, with incomplete tasks moved to the front.  This application utilizes JavaFX to create 
a functional graphical user interface for the user to interact with the program.  

In order to pull the Javadoc, we had a strange issue where you first needed to delete module-info.java before 
it could be pulled or else it will give you several errors.
When generating javadoc, after hitting next twice in the javadoc wizard, paste this line into VM arguments, replacing "path\to" with your local path:
--module-path "path\to\javafx-sdk-13.0.1\lib"
