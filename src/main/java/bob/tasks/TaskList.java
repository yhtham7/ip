package bob.tasks;

import java.util.ArrayList;
import bob.util.*;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int numOfItems;

    public void addItem(Task item) {
        taskList.add(item);
        Ui.printer("Alright! Task added:\n"
                + item + "\n"
                + "There are now " + taskList.size() + " task(s) in the list.\n");
    }

    public void fileAddItem(Task item) {
        taskList.add(item);
    }

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

    public void printList() {
        Ui.printer(this.toString());
    }

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
