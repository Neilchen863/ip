package hichat;

import hichat.event.Task;
import java.util.List;

public class TaskList {
    private List<Task> listOfTasks;

    public TaskList(){
    }

    /**
     * Constructor for TaskList
     * @param listOfTasks List of tasks
     */
    public TaskList(List<Task> listOfTasks){
        this.listOfTasks = listOfTasks;
    }


}