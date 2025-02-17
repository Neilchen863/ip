package hichat.event;

public class Event extends Task{
    private final String startTime;
    private final String endTime;

    public Event(String task, String startTime, String endTime){
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString(){
        if (super.getIsDone()){
            return "[E] "  + "[X] " + super.getTask() + " (from: " + this.startTime + " to: " + this.endTime + ")";
        } else {
            return "[E] "  + "[ ] " + super.getTask() + " (from: " + this.startTime + " to: " + this.endTime + ")";
        }
    }

}