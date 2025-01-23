import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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



        List<String> listOfTasks = new ArrayList<String>();
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


            listOfTasks.add(command);
            System.out.println("____________________________________________________________\n" +
                    " added: " +
                    command + "\n" +
                    "____________________________________________________________\n");
        }

        scanner.close();
    }
}
