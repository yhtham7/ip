package main;

import java.util.Scanner;
import java.util.regex.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public class Bob {
    private static final String FILE_PATH = "./data/tasks.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Storer storer;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        TaskList tasks = new TaskList();

        Ui.printStart();

        Storer storer = new Storer(FILE_PATH);

        tasks = storer.load(tasks);

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                Ui.printEnd();
                storer.save(tasks);
                break;
            }

            String[] parts = input.split(" ", 2);
            String cmd = parts[0].toLowerCase();

            switch (cmd) {
            case "mark":
                if (parts.length < 2) {
                    Ui.printer("Usage: mark <index>\n");
                } else {
                    try {
                        int index = Integer.valueOf(parts[1]);
                        tasks.markItem(index);
                    } catch (NumberFormatException e) {
                        Ui.printer("Type in a number in the range\n");
                    }
                }
                break;
            case "unmark":
                if (parts.length < 2) {
                    Ui.printer("Usage: unmark <index>\n");
                } else {
                    try {
                        int index = Integer.valueOf(parts[1]);
                        tasks.unmarkItem(index);
                    } catch (NumberFormatException e) {
                        Ui.printer("Type in a number in the range\n");
                    }
                }
                break;
            case "list":
                tasks.printList();
                break;
            case "todo":
                if (parts.length < 2) {
                    Ui.printer("Usage: todo <task>\n");
                } else {
                    tasks.addItem(new ToDos(parts[1]));
                }
                break;
            case "deadline":
                if (parts.length < 2) {
                    Ui.printer("Usage: deadline <task> /by <time>\n");
                } else {
                    Pattern pattern = Pattern.compile("^deadline (.+) /by (.+)$");
                    Matcher m = pattern.matcher(input);
                    if (m.matches()) {
                        String desc = m.group(1).trim();
                        String byStr = m.group(2).trim();
                        try {
                            LocalDate byDate = LocalDate.parse(byStr, DATE_FORMAT);
                            tasks.addItem(new Deadlines(desc, byDate));
                        } catch (DateTimeParseException e) {
                            Ui.printer("Invalid date format, use yyyy-MM-dd\n");
                        }
                    } else {
                        Ui.printer("Usage: deadline <task> /by <time>\n");
                    }
                }
                break;
            case "event":
                if (parts.length < 2) {
                    Ui.printer("Usage: event <task> /from <startTime> /to <endTime>\n");
                } else {
                    Pattern pattern = Pattern.compile("^event (.+) /from (.+) /to (.+)$");
                    Matcher m = pattern.matcher(input);
                    if (m.matches()) {
                        String desc = m.group(1).trim();
                        String fromStr = m.group(2).trim();
                        String toStr = m.group(3).trim();
                        try {
                            LocalDate from = LocalDate.parse(fromStr, DATE_FORMAT);
                            LocalDate to = LocalDate.parse(toStr, DATE_FORMAT);
                            tasks.addItem(new Events(desc, from, to));
                        } catch (DateTimeParseException e) {
                            Ui.printer("Invalid date format, use yyyy-MM-dd\n");
                        }
                    } else {
                        Ui.printer("Usage: event <task> /from <startTime> /to <endTime>\n");
                    }
                }
                break;
            case "delete":
                if (parts.length < 2) {
                    Ui.printer("Usage: delete <index>\n");
                } else {
                    try {
                        int index = Integer.valueOf(parts[1]);
                        tasks.deleteItem(index);
                    } catch (NumberFormatException e) {
                        Ui.printer("Type in a number in the range\n");
                    }
                }
                break;
            default:
                Ui.printBack(input);
            }
        }
    }
}
