package hiChat.event;

public  class Task {
    private String task;
    private boolean isDone;

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
        if (this.isDone){
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }

}