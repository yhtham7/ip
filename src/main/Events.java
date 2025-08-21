package main;

public class Events extends Task {
    String startDate;
    String endDate;
    public Events(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
