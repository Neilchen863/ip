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
            task newTask = new task(command);
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

            listOfTasks.add(newTask);
            System.out.println("____________________________________________________________\n" +
                    " added: " +
                    command + "\n" +
                    "____________________________________________________________\n");
        }

        scanner.close();
    }
}
