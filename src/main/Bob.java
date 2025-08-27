package main;

import java.util.Scanner;
import java.util.regex.*;

public class Bob {
    private static final String FILE_PATH = "./data/tasks.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        List tasks = new List();

        Bob.printStart();

        Storer storer = new Storer(FILE_PATH);

        tasks = storer.load(tasks);

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                Bob.printEnd();
                storer.save(tasks);
                break;
            }

            String[] parts = input.split(" ", 2);
            String cmd = parts[0].toLowerCase();

            switch (cmd) {
            case "mark":
                if (parts.length < 2) {
                    Bob.printer("Usage: mark <index>\n");
                } else {
                    try {
                        int index = Integer.valueOf(parts[1]);
                        tasks.markItem(index);
                    } catch (NumberFormatException e) {
                        Bob.printer("Type in a number in the range\n");
                    }
                }
                break;
            case "unmark":
                if (parts.length < 2) {
                    Bob.printer("Usage: unmark <index>\n");
                } else {
                    try {
                        int index = Integer.valueOf(parts[1]);
                        tasks.unmarkItem(index);
                    } catch (NumberFormatException e) {
                        Bob.printer("Type in a number in the range\n");
                    }
                }
                break;
            case "list":
                tasks.printList();
                break;
            case "todo":
                if (parts.length < 2) {
                    Bob.printer("Usage: todo <task>\n");
                } else {
                    tasks.addItem(new ToDos(parts[1]));
                }
                break;
            case "deadline":
                if (parts.length < 2) {
                    Bob.printer("Usage: deadline <task> /by <time>\n");
                } else {
                    Pattern pattern = Pattern.compile("^deadline (.+) /by (.+)$");
                    Matcher m = pattern.matcher(input);
                    if (m.matches()) {
                        tasks.addItem(new Deadlines(m.group(1).trim(), m.group(2).trim()));
                    } else {
                        Bob.printer("Usage: deadline <task> /by <time>\n");
                    }
                }
                break;
            case "event":
                if (parts.length < 2) {
                    Bob.printer("Usage: event <task> /from <startTime> /to <endTime>\n");
                } else {
                    Pattern pattern = Pattern.compile("^event (.+) /from (.+) /to (.+)$");
                    Matcher m = pattern.matcher(input);
                    if (m.matches()) {
                        tasks.addItem(new Events(m.group(1).trim(), m.group(2).trim(), m.group(3).trim()));
                    } else {
                        Bob.printer("Usage: event <task> /from <startTime> /to <endTime>\n");
                    }
                }
                break;
            case "delete":
                if (parts.length < 2) {
                    Bob.printer("Usage: delete <index>\n");
                } else {
                    try {
                        int index = Integer.valueOf(parts[1]);
                        tasks.deleteItem(index);
                    } catch (NumberFormatException e) {
                        Bob.printer("Type in a number in the range\n");
                    }
                }
                break;
            default:
                printBack(input);
            }
        }
    }

    protected static void printBack(String input) {
        Bob.printer( "\"" + input + "\" What does that mean? \n");
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
