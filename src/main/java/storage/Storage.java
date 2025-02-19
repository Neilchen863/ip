package storage;

import core.TaskList;
import ui.Ui;
import exception.BaimiException;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * manages the storage of tasks in the application.
 *
 */
public class Storage {
    private static final String DIRECTORY_PATH = "./data/";
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return The list of tasks loaded from the file.
     * @throws BaimiException If an error occurs while loading tasks.
     */
    public ArrayList<Task> load() throws BaimiException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) continue;

                Task task;
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                 switch (type) {
                 case "T":
                    task = new Todo(description);
                    break;
                 case "D":
                     task = new Deadline(description, parts[3]);
                     break;
                 case "E":
                     task = new Event(description, parts[3], parts[4]);
                     break;
                 default:
                     continue;
                }

                if (isDone) {
                    task.markAsDone();
                }
                if (parts.length >= 4 && parts[parts.length - 1].contains(",")) {
                    String[] tagArray = parts[parts.length - 1].split(",");
                    for (String tag : tagArray) {
                        task.addTag(tag.trim());
                    }
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BaimiException("Error loading tasks from file.");
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws BaimiException If an error occurs while saving tasks.
     */
    public void save(ArrayList<Task> tasks) throws BaimiException {
        try {
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(formatTask(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new BaimiException("Error saving tasks.");
        }
    }

    /**
     * Formats a task to a string that can be saved to the file.
     *
     * @param task The task to format.
     * @return The formatted string.
     */
    private String formatTask(Task task) {
        String status = task.isDone() ? "1" : "0";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String tagString = task.getTags().isEmpty() ? "" : " #" + String.join(" #", task.getTags());

        if (task instanceof Todo) {
            return "T | " + status + " | " + task.getDescription() + tagString;
        } else if (task instanceof Deadline) {
            return "D | " + status + " | " + task.getDescription() + " | "
                    + ((Deadline) task).getBy().format(formatter) + tagString;
        } else if (task instanceof Event) {
            return "E | " + status + " | " + task.getDescription() + " | "
                    + ((Event) task).getFrom().format(formatter) + " | " + ((Event) task).getTo().format(formatter) + tagString;
        }
        return "";
    }
}

