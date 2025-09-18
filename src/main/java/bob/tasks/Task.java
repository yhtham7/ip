package bob.tasks;

import java.util.regex.Pattern;

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

    protected String getTaskType() {return "Task";
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns true if query provided is contained in description
     * @param query
     * @return
     */
    public boolean containsString(String query) {
        return this.description.matches("(?i).*" + Pattern.quote(query) + ".*");
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
