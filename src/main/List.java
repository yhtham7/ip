package main;

import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int numOfItems;

    public void addItem(String item) {
        taskList.add(new Task(item));
        String out = "____________________________________________________________\n"
                + "added: " + item + "\n"
                + "____________________________________________________________";
        System.out.println(out);
    }

    public void printList() {
        String out = "____________________________________________________________\n"
                + this
                + "____________________________________________________________";
        System.out.println(out);
    }

    @Override
    public String toString() {
        int length = taskList.size();
        String out = "";
        if (length < 1) {
            out += "No items on the list!\n";
        } else {
            for (int i = 0; i < length; i++) {
                out += (i + 1) + ". " + taskList.get(i) + "\n";
            }
        }
        return out;
    }
}
