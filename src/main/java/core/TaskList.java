package core;

import java.util.ArrayList;
import task.Task;
import ui.Ui;
import exception.TaskIndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTasks(Task... newTasks) {
        for (Task task : newTasks) {
            tasks.add(task);
            System.out.println("Added: " + task);
        }
    }


    public void deleteTask(int index) throws TaskIndexOutOfBoundsException {
        assert index >= 0 : "Task index cannot be negative";
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException(tasks.size());
        }
        tasks.remove(index);
    }

    public void markTask(int index) throws TaskIndexOutOfBoundsException {
        assert index >= 0 : "Task index cannot be negative";
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException(tasks.size());
        }
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) throws TaskIndexOutOfBoundsException {
        assert index >= 0 : "Task index cannot be negative";
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException(tasks.size());
        }
        tasks.get(index).markAsNotDone();
    }

    /**
     * Finds tasks that contain a specific keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public void printList(Ui ui) {
        if (tasks.isEmpty()) {
            ui.showMessage("No tasks available.");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getListAsString() {
        if (tasks.isEmpty()) {
            return "No tasks available.";
        } else {
            StringBuilder sb = new StringBuilder("Here are your tasks:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public void tagTask(int index, String tag) throws TaskIndexOutOfBoundsException {
        assert tag != null && !tag.trim().isEmpty() : "Tag cannot be null or empty";

        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException(tasks.size());
        }
        tasks.get(index).addTag(tag);
    }

    /**
     * Untags a task with a specific tag.
     *
     * @param index The index of the task to untag.
     * @param tag The tag to remove from the task.
     * @throws TaskIndexOutOfBoundsException If the index is out of bounds.
     */
    public void untagTask(int index, String tag) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException(tasks.size());
        }
        tasks.get(index).removeTag(tag);
    }
}

