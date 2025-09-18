package bob.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    LocalDate dueDate;
    private static final DateTimeFormatter DATE_PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Deadline(String description, boolean complete, LocalDate dueDate) {
        super(description, complete);
        this.dueDate = dueDate;
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    public void setDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
    }

    /**
     * Returns String format of task for storage
     * @return taskString
     */
    @Override
    public String toFileString() {
        return String.format("%s /done %d /des %s /by %s",
                getTaskType(),
                isComplete() ? 1 : 0,
                getDescription(),
                dueDate
        );
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                dueDate.format(DATE_PRINT_FORMAT)
        );
    }
}
