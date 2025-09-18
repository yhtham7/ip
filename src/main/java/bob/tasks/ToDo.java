package bob.tasks;

/**
 * Task "to do"
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean complete) {
        super(description, complete);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    protected String getTaskType() {
        return "Todo";
    }
}
