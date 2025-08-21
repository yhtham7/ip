package main;

import java.util.Scanner;

public class Bob {
    enum Action {
        ADD,
        MARK,
        UNMARK
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        List tasks = new List();

        Bob.printStart();

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                Bob.printEnd();
                break;
            }

            String[] parts = input.split(" ", 2);
            String cmd = parts[0].toLowerCase();

            switch (cmd) {
                case "mark":
                    if (parts.length < 2) {
                        System.out.println("Usage: mark <index>");
                    } else {
                        int index = Integer.valueOf(parts[1]);
                        tasks.markItem(index);
                    }
                    break;
                case "unmark":
                    if (parts.length < 2) {
                        System.out.println("Usage: unmark <index>");
                    } else {
                        int index = Integer.valueOf(parts[1]);
                        tasks.unMarkItem(index);
                    }
                    break;
                case "list":
                    tasks.printList();
                    break;
                default:
                    tasks.addItem(input);
            }

            /*
            } else if (input.equalsIgnoreCase("list")) {
                tasks.printList();
            } else {
                tasks.addItem(input);
            }

             */
        }
    }

    protected static void actions(String input) {

    }

    protected static void printBack(String input) {
        Bob.printer(input + "\n");
    }

    protected static void printStart() {
        Bob.printer("Hello! I'm Bob!\n"
                + "What do you want today?\n");
    }

    protected static void printEnd() {
        Bob.printer("Bye!!!! seeee you soooon!!!!!\n");
    }

    public static void printer(String text) {
        String out = "____________________________________________________________\n"
                + text
                + "____________________________________________________________";
        System.out.println(out);
    }
}
