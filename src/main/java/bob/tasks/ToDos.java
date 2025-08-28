package bob.tasks;

/**
 * Task "to do"
 */
public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    public ToDos(String description, boolean complete) {
        super(description, complete);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    protected String taskType() {
        return "Todo";
    }
}
