package event;

public class Deadline extends Task{
    private String deadline;

    public Deadline(String task, String deadline){
        super(task);
        this.deadline = deadline;
    }

    public String getDeadline(){
        return this.deadline;
    }

    @Override
    public String toString(){
        if (super.getIsDone()){
            return "[D] "  + "[X] " + super.getTask() + " (by: " + this.deadline + ")";
        } else {
            return "[D] "  + "[ ] " + super.getTask() + " (by: " + this.deadline + ")";
        }
    }
}