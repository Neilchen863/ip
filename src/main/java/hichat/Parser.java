package hichat;

public class Parser {
    public Parser(){
    }

    /**
     * Get the first word of the input string
     * @return first word of the input string
     */
    public static String firstWord(String input){
        return input.split(" ")[0];
    }

    /**
     * Get the second word of the input string
     * @return second word of the input string
     */
    public static boolean isBye(String input){
        return input.equals("bye");
    }

    /**
     * Check if the input string is "list"
     * @return true if the input string is "list"
     */
    public static boolean isList(String input){
        return input.equals("list");
    }

    /**
     * Check if the input string is "done"
     * @return true if the input string is "done"
     */
    public static boolean isMark(String input){
        return firstWord(input).equals("mark");
    }

    /**
     * Check if the input string is "undone"
     * @return true if the input string is "undone"
     */
    public static boolean isUnmark(String input){
        return firstWord(input).equals("unmark");
    }

    /**
     * Check if the input string is "delete"
     * @return true if the input string is "delete"
     */
    public static boolean isDelete(String input){
        return firstWord(input).equals("delete");
    }

    /**
     * Check if the input string is "todo"
     * @return true if the input string is "todo"
     */
    public static boolean isToDoTask(String input){
        return firstWord(input).equals("todo");
    }

    /**
     * Check if the input string is "deadline"
     * @return true if the input string is "deadline"
     */
    public static boolean isDeadlineTask(String input){
        return firstWord(input).equals("deadline");
    }

    /**
     * Check if the input string is "event"
     * @return true if the input string is "event"
     */
    public static boolean isEventTask(String input){
        return firstWord(input).equals("event");
    }

    public static boolean isPrioritizeTask(String input){
        return firstWord(input).equals("prioritize");
    }

    public static boolean isUnPrioritizeTask(String input){
        return firstWord(input).equals("unprioritize");
    }
}
