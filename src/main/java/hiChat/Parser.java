package hiChat;

public class Parser {
    public Parser(){
    }

    /**
     * Parse the input string
     * @param input
     * @return input
     */
    public String parse(String input){
        return input;
    }

    /**
     * Get the first word of the input string
     * @param input
     * @return first word of the input string
     */
    public static String firstWord(String input){
        return input.split(" ")[0];
    }

    /**
     * Get the second word of the input string
     * @param input
     * @return second word of the input string
     */
    public static boolean isBye(String input){
        return input.equals("bye");
    }

    /**
     * Check if the input string is "list"
     * @param input
     * @return true if the input string is "list"
     */
    public static boolean isList(String input){
        return input.equals("list");
    }

    /**
     * Check if the input string is "done"
     * @param input
     * @return true if the input string is "done"
     */
    public static boolean isMark(String input){
        return firstWord(input).equals("mark");
    }

    /**
     * Check if the input string is "undone"
     * @param input
     * @return true if the input string is "undone"
     */
    public static boolean isUnmark(String input){
        return firstWord(input).equals("unmark");
    }

    /**
     * Check if the input string is "delete"
     * @param input
     * @return true if the input string is "delete"
     */
    public static boolean isDelete(String input){
        return firstWord(input).equals("delete");
    }

    /**
     * Check if the input string is "todo"
     * @param input
     * @return true if the input string is "todo"
     */
    public static boolean isToDoTask(String input){
        return firstWord(input).equals("todo");
    }

    /**
     * Check if the input string is "deadline"
     * @param input
     * @return true if the input string is "deadline"
     */
    public static boolean isDeadlineTask(String input){
        return firstWord(input).equals("deadline");
    }

    /**
     * Check if the input string is "event"
     * @param input
     * @return true if the input string is "event"
     */
    public static boolean isEventTask(String input){
        return firstWord(input).equals("event");
    }

    /**
     * Check if the input string is "find"
     * @param input
     * @return true if the input string is "find"
     */
    public static boolean isFindTask(String input){
        return firstWord(input).equals("find");
    }
}
