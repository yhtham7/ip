package main;

import java.util.Scanner;
import java.util.regex.*;

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
                case "todo":
                    if (parts.length < 2) {
                        System.out.println("Usage: todo <task>");
                    } else {
                        tasks.addItem(new ToDos(parts[1]));
                    }
                    break;
                case "deadline":
                    if (parts.length < 2) {
                        System.out.println("Usage: deadline <task> /by <time>");
                    } else {
                        Pattern pattern = Pattern.compile("^deadline (.+) /by (.+)$");
                        Matcher m = pattern.matcher(input);
                        if (m.matches()) {
                            tasks.addItem(new Deadlines(m.group(1).trim(), m.group(2).trim()));
                        } else {
                            System.out.println("Usage: deadline <task> /by <time>");
                        }
                    }
                    break;
                case "event":
                    if (parts.length < 2) {
                        System.out.println("Usage: event <task> /from <startTime> /to <endTime>");
                    } else {
                        Pattern pattern = Pattern.compile("^event (.+) /from (.+) /to (.+)$");
                        Matcher m = pattern.matcher(input);
                        if (m.matches()) {
                            tasks.addItem(new Events(m.group(1).trim(), m.group(2).trim(), m.group(3).trim()));
                        } else {
                            System.out.println("Usage: event <task> /from <startTime> /to <endTime>");
                        }
                    }
                    break;
                default:
                    printBack(input);
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
