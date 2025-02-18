package hichat.event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDateTime deadline;

    public Deadline(String task, LocalDateTime deadline){
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        if (super.getIsDone()){
            return "[D] "  + "[X] " + super.getTask() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
        } else {
            return "[D] "  + "[ ] " + super.getTask() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
        }
    }
}