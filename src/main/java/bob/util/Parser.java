package bob.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bob.tasks.*;

public class Parser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void parseInput(String input, TaskList tasks) {
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
        case "find":
            if (parts.length < 2) {
                Ui.printer("Usage: delete <index>\n");
            } else {
                tasks.findTask(parts[1]);
            }
            break;
        default:
            Ui.printBack(input);
        }
    }
}
