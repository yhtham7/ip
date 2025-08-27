package bob.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    LocalDate dueDate;
    public Deadlines(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Deadlines(String description, boolean complete, LocalDate dueDate) {
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
        return "[D]" + super.toString() + " (by: " + this.dueDate.format(DATE_PRINT_FORMAT) + ")";
    }
}
