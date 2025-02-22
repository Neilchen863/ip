package hichat.event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime deadline;

    public Deadline(String task, LocalDateTime deadline){
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        //consider if isdone and if is priority and is deadline
        if (super.getIsPriority()){
            if (super.getIsDone()){
                return "[D] " + "[P] " + "[X] " + super.getTask() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
            } else {
                return "[D] " + "[P] " + "[ ] " + super.getTask() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
            }
        } else {
            if (super.getIsDone()){
                return "[D] " + "[ ] " + "[X] " + super.getTask() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
            } else {
                return "[D] " + "[ ] " + "[ ] " + super.getTask() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
            }
        }
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}