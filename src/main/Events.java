package main;

public class Events extends Task {
    String startDate;
    String endDate;
    public Events(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Events(String description, boolean complete, String startDate, String endDate) {
        super(description, complete);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    protected String taskType() {
        return "Event";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " /from " + this.startDate + " /to " + this.endDate;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
