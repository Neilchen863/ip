package event;

public  class ToDo extends Task{
    public ToDo (String task){
        super(task);
    }

    @Override
    public String toString(){
        if (super.getIsDone()){
            return "[T] "  + "[X] " + super.getTask();
        } else {
            return "[T] "  + "[ ] " + super.getTask();
        }
    }
}