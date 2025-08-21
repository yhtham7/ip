package main;

public class Deadlines extends Task {
    String dueDate;
    public Deadlines(String description, String duedate) {
        super(description);
        this.dueDate = duedate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
