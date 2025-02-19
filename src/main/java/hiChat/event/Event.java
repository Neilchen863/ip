package hiChat.event;

public class Event extends Task{
    private String startTime;
    private String endTime;

    public Event(String task, String startTime, String endTime){
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime(){
        return this.startTime;
    }

    public String getEndTime(){
        return this.endTime;
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