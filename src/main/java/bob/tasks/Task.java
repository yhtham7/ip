package bob.tasks;

/**
 * Generic Task
 */
public class Task {
    protected String description;
    private boolean isComplete = false;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean complete) {
        this.description = description;
        this.isComplete = complete;
    }

    public boolean getCompletionStatus() {
        return this.isComplete;
    }

    public void mark() {
        this.isComplete = true;
    }

    public void unmark() {
        this.isComplete = false;
    }

    protected String getTaskType() {
        return "Task";
    }

    /**
     * Returns String format of task for storage
     * @return taskString
     */
    public String toFileString() {
        return this.getTaskType() + " /done " + (this.isComplete ? 1 : 0) + " /des " + description;
    }

    @Override
    public String toString() {
        return "[" + (isComplete ? "X" : " ") + "] " + description;
    }
}
