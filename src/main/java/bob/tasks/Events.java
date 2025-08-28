package bob.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task event with associated start and end date
 */

public class Events extends Task {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    LocalDate startDate;
    LocalDate endDate;
    public Events(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Events(String description, boolean complete, LocalDate startDate, LocalDate endDate) {
        super(description, complete);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    protected String getTaskType() {
        return "Event";
    }

    /**
     * Returns String format of task for storage
     * @return taskString
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " /from " + this.startDate
                + " /to " + this.endDate;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " (from: " + this.startDate.format(DATE_PRINT_FORMAT)
                + " to: " + this.endDate.format(DATE_PRINT_FORMAT) + ")";
    }
}
