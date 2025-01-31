public class Parser {
    public Parser(){
    }

    public String parse(String input){
        return input;
    }

    public static String firstWord(String input){
        return input.split(" ")[0];
    }

    public static boolean isBye(String input){
        return input.equals("bye");
    }

    public static boolean isList(String input){
        return input.equals("list");
    }

    public static boolean isMark(String input){
        return firstWord(input).equals("mark");
    }

    public static boolean isUnmark(String input){
        return firstWord(input).equals("unmark");
    }

    public static boolean isDelete(String input){
        return firstWord(input).equals("delete");
    }

    public static boolean isToDoTask(String input){
        return firstWord(input).equals("todo");
    }

    public static boolean isDeadlineTask(String input){
        return firstWord(input).equals("deadline");
    }

    public static boolean isEventTask(String input){
        return firstWord(input).equals("event");
    }
}
