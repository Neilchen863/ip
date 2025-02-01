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

    /**
     * Prints farewell message when user types "bye"
     */
    public static void printFarewell() {
        // Farewell message for "bye"
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    /**
     * Prints the list of tasks
     *
     * @param listOfTasks List of tasks
     */
    public static void printList(List<Task> listOfTasks) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + ". " + listOfTasks.get(i));
        }
        System.out.println(
                "____________________________________________________________");
    }

    /**
     * Prints the message when a task is marked as done
     *
     * @param task Task that is marked as done
     */
    public static void printMarkedAsDone(Task task) {
        System.out.println("____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n" +
                "   " + task + "\n" +
                "____________________________________________________________\n");
    }

    /**
     * Prints the message when a task is marked as undone
     *
     * @param task Task that is marked as undone
     */
    public static void printMarkedAsUndone(Task task) {
        System.out.println("____________________________________________________________\n" +
                " OK, I've marked this task as not done yet:\n" +
                "   " + task + "\n" +
                "____________________________________________________________\n");
    }

    /**
     * Prints the message when the user types an invalid command
     */
    public static void printSorry() {
        System.out.println("____________________________________________________________\n" +
                " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "____________________________________________________________\n");
    }

    /**
     * Prints the message when a task is added
     *
     * @param task Task that is added
     * @param listOfTasks List of tasks
     */
    public static void printAddedTask(Task task, List<Task> listOfTasks) {
        System.out.println("____________________________________________________________\n" +
                " Got it. I've added this task:\n" +
                "   " + task + "\n" +
                " Now you have " + listOfTasks.size() + " tasks in the list.\n" +
                "____________________________________________________________\n");
    }
    

    public static void printFoundTasks(List<Task> foundTasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println((i + 1) + ". " + foundTasks.get(i));
        }
        System.out.println(
                "____________________________________________________________");
    }
}
