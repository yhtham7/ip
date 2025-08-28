package bob.tasks;

/**
 * Generic Task
 */
public class Task {
    protected String description;
    private boolean complete = false;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean complete) {
        this.description = description;
        this.complete = complete;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public void mark() {
        this.complete = true;
    }

    public void unmark() {
        this.complete = false;
    }

    protected String taskType() {
        return "Task";
    }

    /**
     * Returns String format of task for storage
     * @return taskString
     */
    public String toFileString() {
        return this.taskType() + " /done " + (this.complete ? 1 : 0) + " /des " + description;
    }

    @Override
    public String toString() {
        return "[" + (complete ? "X" : " ") + "] " + description;
    }
}
