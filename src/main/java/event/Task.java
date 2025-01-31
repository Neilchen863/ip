package event;

public  class Task {
    private String task;
    private boolean isDone;

    public Task(String task){
        this.task = task;
        this.isDone = false;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

    public String getTask(){
        return this.task;
    }

    public String toString(){
        if (this.isDone){
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }

}