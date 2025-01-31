package hiChat;

import hiChat.event.Task;
import java.util.List;

public class TaskList {
    private List<Task> listOfTasks;

    public TaskList(){
    }

    public TaskList(List<Task> listOfTasks){
        this.listOfTasks = listOfTasks;
    }

    public List<Task> getListOfTasks(){
        return this.listOfTasks;
    }

    public void addTask(Task task){
        this.listOfTasks.add(task);
    }

    public void removeTask(int index){
        this.listOfTasks.remove(index);
    }
}