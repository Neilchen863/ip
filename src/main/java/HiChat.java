import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import event.*;
import java.io.File;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class HiChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " __    __   __    ______  __    __       ___   .___________.\n"
                + "|  |  |  | |  |  /      ||  |  |  |     /   \\  |           |\n"
                + "|  |__|  | |  | |  ,----'|  |__|  |    /  ^  \\ `---|  |----`\n"
                + "|   __   | |  | |  |     |   __   |   /  /_\\  \\    |  |     \n"
                + "|  |  |  | |  | |  `----.|  |  |  |  /  _____  \\   |  |     \n"
                + "|__|  |__| |__|  \\______||__|  |__| /__/     \\__\\  |__|     \n";
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm\n" + logo +
                " What can I do for you?\n" +
                "____________________________________________________________\n");



        List<Task> listOfTasks = new ArrayList<>();
        readListFromFile(listOfTasks);


        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                // Farewell message for "bye"
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break; // Exit the loop
            }

            if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < listOfTasks.size(); i++) {
                    System.out.println((i + 1) + ". " + listOfTasks.get(i));
                }
                System.out.println(
                        "____________________________________________________________");
                continue;
            }

            if (command.contains("mark") && !command.contains("unmark")) {
                String[] splitCommand = command.split(" ");
                int taskNumber = Integer.parseInt(splitCommand[1]);
                listOfTasks.get(taskNumber - 1).markAsDone();
                System.out.println("____________________________________________________________\n" +
                        " Nice! I've marked this task as done:\n" +
                        "   " + listOfTasks.get(taskNumber - 1) + "\n" +
                        "____________________________________________________________\n");
                writeListToFile(listOfTasks);
                continue;
            }

            if (command.contains("unmark")) {
                String[] splitCommand = command.split(" ");
                int taskNumber = Integer.parseInt(splitCommand[1]);
                listOfTasks.get(taskNumber - 1).markAsUndone();
                System.out.println("____________________________________________________________\n" +
                        " OK, I've marked this task as not done yet:\n" +
                        "   " + listOfTasks.get(taskNumber - 1) + "\n" +
                        "____________________________________________________________\n");
                writeListToFile(listOfTasks);
                continue;
            }

            if (command.contains("delete")) {
                String[] splitCommand = command.split(" ");
                int taskNumber = Integer.parseInt(splitCommand[1]);
                System.out.println("____________________________________________________________\n" +
                        " Noted. I've removed this task:\n" +
                        "   " + listOfTasks.get(taskNumber - 1) + "\n" +
                        " Now you have " + (listOfTasks.size() - 1) + " tasks in the list.\n" +
                        "____________________________________________________________\n");
                listOfTasks.remove(taskNumber - 1);
                writeListToFile(listOfTasks);
                continue;
            }


            if (command.contains("todo")) {
                String[] splitCommand = command.split(" ");
                int len = splitCommand.length;
                String errorMsg = "____________________________________________________________\n" +
                        "☹ OOPS!!! The description of a todo cannot be empty." + "\n" +
                        "____________________________________________________________\n";
                try {
                    if (len == 1) {
                        throw new Exception(errorMsg);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                String task = "";
                for (int i = 1; i < splitCommand.length; i++) {
                    task += splitCommand[i] + " ";
                }

                listOfTasks.add(new ToDo(task));
                writeListToFile(listOfTasks);
            }

            else if (command.contains("deadline")) {
                String[] splitCommand = command.split(" ");
                String task = "";
                String ddl = "";
                boolean isTask = true;
                boolean isDdl = false;

                for (int i = 1; i < splitCommand.length; i++) {
                    if (splitCommand[i].equals("/by")) {
                        isTask = false;
                        isDdl = true;
                        continue;
                    }

                    if (isTask) {
                        task += splitCommand[i] + " ";
                    } else if (isDdl) {
                        ddl += splitCommand[i] + " ";
                    }
                }

                // Ensure correct date format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime deadline = LocalDateTime.parse(ddl.trim(), formatter);

                Task newTask = new Deadline(task, deadline);
                listOfTasks.add(newTask);
                writeListToFile(listOfTasks);
            }

            else if (command.contains("event")) {
                String[] splitCommand = command.split(" ");
                String task = "";
                String startTime = "";
                String endTime = "";
                boolean isTask = true;
                boolean isStartTime = false;
                boolean isEndTime = false;

                for (int i = 1; i < splitCommand.length; i++) {
                    if (splitCommand[i].equals("/from")) {
                        isTask = false;
                        isStartTime = true;
                        continue;
                    }

                    if (splitCommand[i].equals("/to")) {
                        isStartTime = false;
                        isEndTime = true;
                        continue;
                    }

                    if (isTask) {
                        task += splitCommand[i] + " ";
                    } else if (isStartTime) {
                        startTime += splitCommand[i] + " ";
                    } else if (isEndTime) {
                        endTime += splitCommand[i] + " ";
                    }
                }
                Task newTask = new Event(task, startTime, endTime);
                listOfTasks.add(newTask);
                writeListToFile(listOfTasks);
            }

            else {
                System.out.println("____________________________________________________________\n" +
                        " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________\n");
                continue;
            }

            System.out.println("____________________________________________________________\n" +
                    " OKAY. I have added this task: " +
                    command + "\n" +
                    " Now you have " + listOfTasks.size() + " tasks in the list.\n" +
                    "____________________________________________________________\n");
        }

        scanner.close();
    }

    private static void writeListToFile(List<Task> listOfTasks) {
        try {
            File file = new File("data/hiChat.txt");
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

    private static void readListFromFile(List<Task> listOfTasks) {
        try {
            File file = new File("data/hiChat.txt");
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
        }
    }







}
