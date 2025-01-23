import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HiChat {
    public static class task{
        private String task;
        private boolean isDone;


        public task(String task){
            this.task = task;
            this.isDone = false;
        }

        public void markAsDone(){
            this.isDone = true;
        }

        public String getTask(){
            return this.task;
        }

        public boolean getIsDone(){
            return this.isDone;
        }

        public String toString(){
            if (this.isDone){
                return "[X] " + this.task;
            } else {
                return "[ ] " + this.task;
            }
        }
    }


    public static class ToDo extends task{
        public ToDo (String task){
            super(task);
        }

        @Override
        public String toString(){
            if (super.isDone){
                return "[T] "  + "[X] " + super.task;
            } else {
                return "[T] "  + "[ ] " + super.task;
            }
        }
    }

    public static class Deadline extends task{
        private String deadline;

        public Deadline(String task, String deadline){
            super(task);
            this.deadline = deadline;
        }

        public String getDeadline(){
            return this.deadline;
        }

        @Override
        public String toString(){
            if (super.isDone){
                return "[D] "  + "[X] " + super.task + " (by: " + this.deadline + ")";
            } else {
                return "[D] "  + "[ ] " + super.task + " (by: " + this.deadline + ")";
            }
        }
    }

    public static class Event extends task{
        private String startTime;
        private String endTime;

        public Event(String task, String startTime, String endTime){
            super(task);
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getStartTime(){
            return this.startTime;
        }

        public String getEndTime(){
            return this.endTime;
        }

        @Override
        public String toString(){
            if (super.isDone){
                return "[E] "  + "[X] " + super.task + " (from: " + this.startTime + " to: " + this.endTime + ")";
            } else {
                return "[E] "  + "[ ] " + super.task + " (from: " + this.startTime + " to: " + this.endTime + ")";
            }
        }

    }

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



        List<task> listOfTasks = new ArrayList<task>();
//        String[] listOfTasks = new String[100];

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
                continue;
            }

            if (command.contains("unmark")) {
                String[] splitCommand = command.split(" ");
                int taskNumber = Integer.parseInt(splitCommand[1]);
                listOfTasks.get(taskNumber - 1).isDone = false;
                System.out.println("____________________________________________________________\n" +
                        " OK, I've marked this task as not done yet:\n" +
                        "   " + listOfTasks.get(taskNumber - 1) + "\n" +
                        "____________________________________________________________\n");
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
            }

            else if (command.contains("deadline")) {
                String[] splitCommand = command.split(" ");
                String task = "";
                String deadline = "";
                boolean isTask = true;
                for (int i = 1; i < splitCommand.length; i++) {
                    if (splitCommand[i].equals("/by")) {
                        isTask = false;
                        continue;
                    }
                    if (isTask) {
                        task += splitCommand[i] + " ";
                    } else {
                        deadline += splitCommand[i] + " ";
                    }
                }
                task newTask = new Deadline(task, deadline);
                listOfTasks.add(newTask);
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
                task newTask = new Event(task, startTime, endTime);
                listOfTasks.add(newTask);
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
}
