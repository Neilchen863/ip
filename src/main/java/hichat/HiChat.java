package hichat;

import java.util.ArrayList;
import java.util.List;

import hichat.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HiChat {
    private static List<Task> listOfTasks;

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

        if (Parser.isMark(command)) {
            String[] splitCommand = command.split(" ");
            int taskNumber = Integer.parseInt(splitCommand[1]) - 1;
            if (taskNumber < 0 || taskNumber >= listOfTasks.size()) {
                return "☹ OOPS!!! Task number out of range.";
            }
            listOfTasks.get(taskNumber).markAsDone();
            Storage.writeListToFile(listOfTasks);
            return Ui.getMarkedAsDoneMessage(listOfTasks.get(taskNumber));
        }

        if (Parser.isUnmark(command)) {
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
        new HiChat();
    }
}
