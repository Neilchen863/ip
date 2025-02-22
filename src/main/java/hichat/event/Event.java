package hichat.event;

public class Event extends Task{
    private String startTime;
    private String endTime;

    public Event(String task, String startTime, String endTime){
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString(){
        //consider if isdone and if is priority and is event
        if (super.getIsPriority()){
            if (super.getIsDone()){
                return "[E] " + "[P] "  + "[X] " + super.getTask() + " (from: " + this.startTime + " to: " + this.endTime + ")";
            } else {
                return "[E] " + "[P] " + "[ ] " + super.getTask() + " (from: " + this.startTime + " to: " + this.endTime + ")";
            }
        } else {
            if (super.getIsDone()){
                return "[E] " + "[ ] " + "[X] " + super.getTask() + " (from: " + this.startTime + " to: " + this.endTime + ")";
            } else {
                return "[E] " + "[ ] " + "[ ] " + super.getTask() + " (from: " + this.startTime + " to: " + this.endTime + ")";
            }
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}