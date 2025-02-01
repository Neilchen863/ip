package hiChat.event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime deadline;

    public Deadline(String task, LocalDateTime deadline){
        super(task);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline(){
        return this.deadline;
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