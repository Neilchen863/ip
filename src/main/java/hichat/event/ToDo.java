package hichat.event;

public  class ToDo extends Task{
    public ToDo (String task){
        super(task);
    }

    // Setter for task
    @Override
    public String toString(){
        //consider if isdone and if is priority
        if (super.getIsPriority()){
            if (super.getIsDone()){
                return "[T] " + "[P] " + "[X] " + super.getTask();
            } else {
                return "[T] " + "[P] " + "[ ] " + super.getTask();
            }
        } else {
            if (super.getIsDone()){
                return "[T] " + "[ ] " + "[X] " + super.getTask();
            } else {
                return "[T] " + "[ ] " + "[ ] " + super.getTask();
            }
        }
    }
}