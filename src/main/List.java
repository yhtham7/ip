package main;

import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int numOfItems;

    public void addItem(String item) {
        taskList.add(new Task(item));
        Bob.printer("added: " + item + "\n");
    }

    public void markItem(int index) {
        if (index > 0 && index <= taskList.size()) {
            this.taskList.get(index - 1).mark();
            Bob.printer("Ok the task is marked as complete!\n");
        } else {
            Bob.printer("Invalid index!\n");
        }
    }

    public void unMarkItem(int index) {
        if (index > 0 && index <= taskList.size()) {
            this.taskList.get(index - 1).unmark();
            Bob.printer("Ok the task is no longer complete!\n");
        } else {
            Bob.printer("Invalid index!\n");
        }
    }

    public void printList() {
        Bob.printer(this.toString());
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
