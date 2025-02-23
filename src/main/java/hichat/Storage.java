package hichat;

import hichat.event.Deadline;
import hichat.event.Event;
import hichat.event.Task;
import hichat.event.ToDo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Storage {
    /**
     * Writes the list of tasks to a file.
     *
     * @param listOfTasks List of tasks to be written to file.
     */
    public static void writeListToFile(TaskList listOfTasks) {
        try {
            File file = new File("./data/hiChat.txt");
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < listOfTasks.size(); i++) {
                Task task = listOfTasks.get(i);
                if (task instanceof ToDo) {
                    fileWriter.write(task.toString() + "\n");
                } else if (task instanceof Deadline) {
                    fileWriter.write(task.toString() + "\n");
                } else if (task instanceof Event) {
                    fileWriter.write(task.toString() + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Reads the list of tasks from a file.
     *
     * @param listOfTasks List of tasks to be read from file.
     */
    public static void readListFromFile(TaskList listOfTasks) {
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File("data/hiChat.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                String[] splitData = data.split(" ");
                if (splitData[0].equals("[T]")) {
                    String task = "";
                    for (int i = 3; i < splitData.length; i++) {
                        task += splitData[i] + " ";
                    }
                    Task newTask = new ToDo(task);
                    if (splitData[1].equals("[X]")) {
                        newTask.markAsDone();
                    }
                    // check if task is priority
                    if (splitData[2].equals("[P]")) {
                        newTask.setIsPriority(true);
                    }
                    listOfTasks.add(newTask);
                } else if (splitData[0].equals("[D]")) {
                    // Ensure splitData has enough elements
                    if (splitData.length < 6) {
                        throw new IllegalArgumentException("Invalid input format: " + String.join(" ", splitData));
                    }

                    // Extract task description (everything before "(by:")
                    StringBuilder taskBuilder = new StringBuilder();
                    int deadlineIndex = -1; // To locate "(by:"
                    for (int i = 5; i < splitData.length; i++) {
                        if (splitData[i].startsWith("(by:")) {
                            deadlineIndex = i;
                            break;
                        }
                        taskBuilder.append(splitData[i]).append(" ");
                    }

                    // Ensure deadline marker "(by:" exists
                    if (deadlineIndex == -1) {
                        throw new IllegalArgumentException("Missing deadline marker '(by:' in input: " + String.join(" ", splitData));
                    }

                    String task = taskBuilder.toString().trim();

                    String task0 = data.substring(12, data.indexOf("(by:") - 1);
                    // Extract raw deadline part (after "(by:")
                    StringBuilder ddlBuilder = new StringBuilder();
                    for (int i = deadlineIndex + 1; i < splitData.length; i++) {
                        ddlBuilder.append(splitData[i]).append(" ");
                    }

                    String ddl = ddlBuilder.toString().trim();

                    // Ensure deadline format is valid
                    if (ddl.endsWith(")")) {
                        ddl = ddl.substring(0, ddl.length() - 1); // Remove closing ")"
                    } else {
                        throw new IllegalArgumentException("Unexpected deadline format: " + ddl);
                    }



                    // Define date format
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

                    // Parse deadline
                    LocalDateTime deadline;
                    try {
                        deadline = LocalDateTime.parse(ddl, formatter);
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Failed to parse deadline: " + ddl, e);
                    }

                    // Create task object
                    Task newTask = new Deadline(task0, deadline);

                    // Check priority
                    if (splitData[1].equals("[P]")) {
                        newTask.setIsPriority(true);
                    }

                    // Check if marked as done
                    if (splitData[2].equals("[X]")) {
                        newTask.markAsDone();
                    }

                    listOfTasks.add(newTask);
                } else if (splitData[0].equals("[E]")) {
                    listOfTasks.add(new Event(data.substring(12, data.indexOf("(") - 1), data.substring(data.indexOf("(") + 7, data.indexOf("to") - 1), data.substring(data.indexOf("to") + 4, data.length() - 1)));
                    if (splitData[1].equals("[P]")) {
                        listOfTasks.get(listOfTasks.size() - 1).setIsPriority(true);
                    }
                    if (splitData[1].equals("[X]")) {
                        listOfTasks.get(listOfTasks.size() - 1).markAsDone();
                    }
                }
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
