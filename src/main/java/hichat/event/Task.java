package hichat.event;

public  class Task {
    private String task;
    private boolean isDone;
    private boolean isPriority;

    public Task(String task){
        this.task = task;
        this.isDone = false;
    }

    // Mark task as done
    public void markAsDone(){
        this.isDone = true;
    }

    // Getter for isDone
    public boolean getIsDone(){
        return this.isDone;
    }

    // Getter for isPriority
    public boolean getIsPriority(){
        return this.isPriority;
    }

    // Setter for isPriority
    public void setIsPriority(boolean isPriority){
        this.isPriority = isPriority;
    }

    // Mark task as undone
    public void markAsUndone(){
        this.isDone = false;
    }

    // Getter for task
    public String getTask(){
        return this.task;
    }

    // Setter for task
    public String toString(){
        //consider if isdone and if is priority
        if (this.isPriority){
            if (this.isDone){
                return "[P] " + "[X] " + this.task;
            } else {
                return "[P] " + "[ ] " + this.task;
            }
        } else {
            if (this.isDone){
                return "[ ] " + "[X] " + this.task;
            } else {
                return "[ ] " + "[ ] " + this.task;
            }
        }
    }

}