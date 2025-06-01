package hichat;

import hichat.event.Task;
import hichat.event.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> listOfTasks;

    public TaskList(){
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList
     * @param listOfTasks List of tasks
     */
    public TaskList(List<Task> listOfTasks){
        this.listOfTasks = listOfTasks;
    }


    public String find(String searchString) {
        String result = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).getTask().contains(searchString)) {
                result += (i + 1) + ". " + listOfTasks.get(i) + "\n";
            }
        }
        if (result.equals("")) {
            return "No matching tasks found!";
        }
        return result;
    }

    public int size() {
        return listOfTasks.size();
    }

    public Task get(int taskNumber) {
        return listOfTasks.get(taskNumber);
    }

    public void add(Task toDo) {
        listOfTasks.add(toDo);
    }

    public void haha() {}

    public Task remove(int taskNumber) {
        return listOfTasks.remove(taskNumber);
    }

    public void add(int i, Task task) {
        listOfTasks.add(i, task);
    }
}