package hiChat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import hiChat.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HiChat {
    private static List<Task> listOfTasks;

    // 构造方法，初始化任务列表
    public HiChat() {
        listOfTasks = new ArrayList<>();
        Storage.readListFromFile(listOfTasks);
    }


    public String getResponse(String command) {
        if (Parser.isBye(command)) {
            return Ui.getFarewellMessage();
        }

        if (Parser.isList(command)) {
            return Ui.getListString(listOfTasks);
        }

        if (Parser.firstWord(command).equals("done")) {
            String[] splitCommand = command.split(" ");
            int taskNumber = Integer.parseInt(splitCommand[1]) - 1;
            if (taskNumber < 0 || taskNumber >= listOfTasks.size()) {
                return "☹ OOPS!!! Task number out of range.";
            }
            listOfTasks.get(taskNumber).markAsDone();
            Storage.writeListToFile(listOfTasks);
            return Ui.getMarkedAsDoneMessage(listOfTasks.get(taskNumber));
        }

        if (Parser.firstWord(command).equals("undone")) {
            String[] splitCommand = command.split(" ");
            int taskNumber = Integer.parseInt(splitCommand[1]) - 1;
            if (taskNumber < 0 || taskNumber >= listOfTasks.size()) {
                return "☹ OOPS!!! Task number out of range.";
            }
            listOfTasks.get(taskNumber).markAsUndone();
            Storage.writeListToFile(listOfTasks);
            return Ui.getMarkedAsUndoneMessage(listOfTasks.get(taskNumber));
        }

        if (Parser.firstWord(command).equals("delete")) {
            String[] splitCommand = command.split(" ");
            int taskNumber = Integer.parseInt(splitCommand[1]) - 1;
            if (taskNumber < 0 || taskNumber >= listOfTasks.size()) {
                return "☹ OOPS!!! Task number out of range.";
            }
            Task removedTask = listOfTasks.remove(taskNumber);
            Storage.writeListToFile(listOfTasks);
            return "Noted. I've removed this task:\n" +
                    "   " + removedTask + "\n" +
                    "Now you have " + listOfTasks.size() + " tasks in the list.";
        }

        if (Parser.isToDoTask(command)) {
            String[] splitCommand = command.split(" ");
            int len = splitCommand.length;
            String errorMsg = "☹ OOPS!!! The description of a todo cannot be empty.";
            try {
                if (len == 1) {
                    throw new Exception(errorMsg);
                }
            } catch (Exception e) {
                return e.getMessage();
            }
            String task = "";
            for (int i = 1; i < splitCommand.length; i++) {
                task += splitCommand[i] + " ";
            }

            listOfTasks.add(new ToDo(task));
            Storage.writeListToFile(listOfTasks);
            return "Got it. I've added this task:\n" +
                    "   " + listOfTasks.get(listOfTasks.size() - 1) + "\n" +
                    "Now you have " + listOfTasks.size() + " tasks in the list.";
        }

        if (Parser.isDeadlineTask(command)) {
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
            Storage.writeListToFile(listOfTasks);
            return "Got it. I've added this task:\n" +
                    "   " + listOfTasks.get(listOfTasks.size() - 1) + "\n" +
                    "Now you have " + listOfTasks.size() + " tasks in the list.";
        }

        if (Parser.isEventTask(command)) {
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
            Storage.writeListToFile(listOfTasks);
            return "Got it. I've added this task:\n" +
                    "   " + listOfTasks.get(listOfTasks.size() - 1) + "\n" +
                    "Now you have " + listOfTasks.size() + " tasks in the list.";
        }

        return "Sorry, I don't understand that command.";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ui.printGreeting();

        while (true) {
            String command = scanner.nextLine();
            if (Parser.isBye(command)) {
                Ui.printFarewell();
                break;
            }

            if (Parser.isList(command)) {
                Ui.printList(listOfTasks);
                continue;
            }

            if (Parser.firstWord(command).equals("done")) {
                String[] splitCommand = command.split(" ");
                int taskNumber = Integer.parseInt(splitCommand[1]);
                listOfTasks.get(taskNumber - 1).markAsDone();
                Ui.printMarkedAsDone(listOfTasks.get(taskNumber - 1));
                Storage.writeListToFile(listOfTasks);
                continue;
            }

            if (Parser.firstWord(command).equals("undone")) {
                String[] splitCommand = command.split(" ");
                int taskNumber = Integer.parseInt(splitCommand[1]);
                listOfTasks.get(taskNumber - 1).markAsUndone();
                Ui.printMarkedAsUndone(listOfTasks.get(taskNumber - 1));
                Storage.writeListToFile(listOfTasks);
                continue;
            }

            if (Parser.firstWord(command).equals("delete")) {
                String[] splitCommand = command.split(" ");
                int taskNumber = Integer.parseInt(splitCommand[1]);
                System.out.println("____________________________________________________________\n" +
                        " Noted. I've removed this task:\n" +
                        "   " + listOfTasks.get(taskNumber - 1) + "\n" +
                        " Now you have " + (listOfTasks.size() - 1) + " tasks in the list.\n" +
                        "____________________________________________________________\n");
                listOfTasks.remove(taskNumber - 1);
                Storage.writeListToFile(listOfTasks);
                continue;
            }


            if (Parser.isToDoTask(command)) {
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
                Storage.writeListToFile(listOfTasks);
                Ui.printAddedTask(listOfTasks.get(listOfTasks.size() - 1), listOfTasks);
            }

            else if (Parser.isDeadlineTask(command)) {
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
                Storage.writeListToFile(listOfTasks);
                Ui.printAddedTask(listOfTasks.get(listOfTasks.size() - 1), listOfTasks);
            }

            else if (Parser.isEventTask(command)) {
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
                Storage.writeListToFile(listOfTasks);
                Ui.printAddedTask(listOfTasks.get(listOfTasks.size() - 1), listOfTasks);
            }

            else if (Parser.isFindTask(command)) {
                String[] splitCommand = command.split(" ");
                String keyword = splitCommand[1];
                List<Task> foundTasks = new ArrayList<>();
                for (Task task : listOfTasks) {
                    if (task.getTask().contains(keyword)) {
                        foundTasks.add(task);
                    }
                }
                Ui.printFoundTasks(foundTasks);
            }

            else if (Parser.isReshedule(command)) {
                String[] splitCommand = command.split(" ");
                int taskNumber = Integer.parseInt(splitCommand[1]);
                String newTime = splitCommand[2];
                listOfTasks.get(taskNumber - 1).reshedule(newTime);
                Ui.printResheduledTask(listOfTasks.get(taskNumber - 1));
                Storage.writeListToFile(listOfTasks);
            }

            else {
                Ui.printSorry();
                continue;
            }


        }

        scanner.close();
    }
}
