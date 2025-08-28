package bob.tasks;

import bob.util.*;
import java.util.ArrayList;

/**
 * Class containing a list of Tasks to be noted
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int numOfItems;

    /**
     * Adds Task item to list
     * Prints confirmation message
     * @param item
     */
    public void addItem(Task item) {
        taskList.add(item);
        Ui.printer("Alright! Task added:\n"
                + item + "\n"
                + "There are now " + taskList.size() + " task(s) in the list.\n");
    }

    /**
     * Adds Task item to list
     * No message printed
     * @param item
     */
    public void fileAddItem(Task item) {
        taskList.add(item);
    }

    /**
     * Marks item at index as complete
     * @param index
     */
    public void markItem(int index) {
        if (index > 0 && index <= taskList.size()) {
            Task thing = this.taskList.get(index - 1);
            thing.mark();
            Ui.printer("Ok the task is marked as complete!\n"
                    + thing + "\n");
        } else {
            Ui.printer("Invalid index!\n");
        }
    }

    /**
     * Marks item at index as not complete
     * @param index
     */
    public void unmarkItem(int index) {
        if (index > 0 && index <= taskList.size()) {
            Task thing = this.taskList.get(index - 1);
            thing.unmark();
            Ui.printer("Ok the task is no longer complete!\n"
                    + thing + "\n");
        } else {
            Ui.printer("Invalid index!\n");
        }
    }

    /**
     * Removes Task item at index form list
     * @param index
     */
    public void deleteItem(int index) {
        if (index > 0 && index <= taskList.size()) {
            Task item = this.taskList.get(index - 1);
            this.taskList.remove(index - 1);
            Ui.printer("Alright! Task removed:\n"
                    + item + "\n"
                    + "There are now " + taskList.size() + " task(s) in the list.\n");
        } else {
            Ui.printer("Invalid index!\n");
        }
    }

    /**
     * Prints a list of tasks that contain provided query
     * @param query
     */
    public void findTask(String query) {
        int length = taskList.size();
        String out = "";
        if (length < 1) {
            out += "No items on the list!\n";
        } else {
            for (int i = 0; i < length; i++) {
                Task currTask = taskList.get(i);
                if (currTask.containsString(query)) {
                    out += (i + 1) + "." + taskList.get(i) + "\n";
                }
            }
        }
        Ui.printer(out.isEmpty() ? "No items match query" : out);
    }

    /**
     * Pretty print function
     */
    public void printList() {
        Ui.printer(this.toString());
    }

    /**
     * Getter for arraylist
     * @return taskList
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    @Override
    public String toString() {
        int length = taskList.size();
        String out = "";
        if (length < 1) {
            out += "No items on the list!\n";
        } else {
            for (int i = 0; i < length; i++) {
                out += (i + 1) + "." + taskList.get(i) + "\n";
            }
        }
        return out;
    }
}
