package hichat;

import hichat.event.Task;
import java.util.List;

public class TaskList {
    private List<Task> listOfTasks;

    public TaskList(){
    }

    public TaskList(List<Task> listOfTasks){
        this.listOfTasks = listOfTasks;
    }


}