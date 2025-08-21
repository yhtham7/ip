package main;

public class Task {
    protected String description;
    private boolean complete = false;

    public Task(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "[" + (complete ? "X" : " ") + "] " + description;
    }
}
