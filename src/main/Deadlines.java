package main;

public class Deadlines extends Task {
    String dueDate;
    public Deadlines(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Deadlines(String description, boolean complete, String dueDate) {
        super(description, complete);
        this.dueDate = dueDate;
    }

    @Override
    protected String taskType() {
        return "Deadline";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " /by " + this.dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
