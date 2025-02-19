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
import java.util.List;
import java.util.Scanner;

public class Storage {
    /**
     * Writes the list of tasks to a file.
     *
     * @param listOfTasks List of tasks to be written to file.
     */
    public static void writeListToFile(List<Task> listOfTasks) {
        try {
            File file = new File("./data/hiChat.txt");
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : listOfTasks) {
                fileWriter.write(task.toString() + "\n");
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
    public static void readListFromFile(List<Task> listOfTasks) {
        try {
            // 创建 data 目录，如果不存在
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();  // 创建 data 目录
            }

            File file = new File("data/hiChat.txt");

            // 如果文件不存在，创建该文件
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
                    listOfTasks.add(newTask);
                } else if (splitData[0].equals("[D]")) {
                    String task = "";
                    String ddl = "";
                    int positionOfBy = 0;
                    for (int i = 3; i < splitData.length - 1; i++) {
                        if (!splitData[i].equals("(by:")) {
                            task += splitData[i] + " ";
                            positionOfBy = i;
                        } else {
                            break;
                        }
                    }
                    for (int i = positionOfBy + 2; i < splitData.length; i++) {
                        ddl += splitData[i] + " ";
                    }
                    ddl = ddl.replace("(", "").replace(")", "").trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
                    LocalDateTime deadline = LocalDateTime.parse(ddl, formatter);
                    Task newTask = new Deadline(task, deadline);
                    if (splitData[1].equals("[X]")) {
                        newTask.markAsDone();
                    }
                    listOfTasks.add(newTask);
                } else if (splitData[0].equals("[E]")) {
                    if (splitData[1].equals("[X]")) {
                        listOfTasks.add(new Event(data.substring(8, data.indexOf("(") - 1), data.substring(data.indexOf("(") + 6, data.indexOf("to") - 1), data.substring(data.indexOf("to") + 4, data.length() - 1)));
                        listOfTasks.get(listOfTasks.size() - 1).markAsDone();
                    } else {
                        listOfTasks.add(new Event(data.substring(8, data.indexOf("(") - 1), data.substring(data.indexOf("(") + 6, data.indexOf("to") - 1), data.substring(data.indexOf("to") + 4, data.length() - 1)));
                    }
                }
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            //create the file if it does not exist

        }
    }

}
