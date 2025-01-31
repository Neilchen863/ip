package hiChat;

import hiChat.event.Task;

import java.util.List;

public class Ui {
    public static void printGreeting() {
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
    }

    public static void printFarewell() {
        // Farewell message for "bye"
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    public static void printList(List<Task> listOfTasks) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + ". " + listOfTasks.get(i));
        }
        System.out.println(
                "____________________________________________________________");
    }

    public static void printMarkedAsDone(Task task) {
        System.out.println("____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n" +
                "   " + task + "\n" +
                "____________________________________________________________\n");
    }

    public static void printMarkedAsUndone(Task task) {
        System.out.println("____________________________________________________________\n" +
                " OK, I've marked this task as not done yet:\n" +
                "   " + task + "\n" +
                "____________________________________________________________\n");
    }

    public static void printSorry() {
        System.out.println("____________________________________________________________\n" +
                " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "____________________________________________________________\n");
    }

    public static void printAddedTask(Task task, List<Task> listOfTasks) {
        System.out.println("____________________________________________________________\n" +
                " Got it. I've added this task:\n" +
                "   " + task + "\n" +
                " Now you have " + listOfTasks.size() + " tasks in the list.\n" +
                "____________________________________________________________\n");
    }
}
