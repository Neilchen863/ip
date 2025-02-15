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

    public List<Task> getListOfTasks(){
        return this.listOfTasks;
    }

    public void addTask(Task task){
        this.listOfTasks.add(task);
    }

    /**
     * Removes a task from the list of tasks
     * @param index Index of the task to be removed
     */
    public void removeTask(int index){
        this.listOfTasks.remove(index);
    }
}