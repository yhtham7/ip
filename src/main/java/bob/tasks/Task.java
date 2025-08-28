package bob.tasks;

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

    public boolean containsString(String query) {
        return this.description.contains(query);
    }

    protected String taskType() {
        return "Task";
    }

    public String toFileString() {
        return this.taskType() + " /done " + (this.complete ? 1 : 0) + " /des " + description;
    }

    @Override
    public String toString() {
        return "[" + (complete ? "X" : " ") + "] " + description;
    }
}
