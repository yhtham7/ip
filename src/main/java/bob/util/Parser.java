package bob.util;


import bob.tasks.Deadline;
import bob.tasks.Event;
import bob.tasks.TaskList;
import bob.tasks.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Parser class for parser method
 */
public class Parser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Takes in a String input and parses it to execute the correct action
     * @param input String to be parsed
     * @param tasks TaskList to be modified
     */
    public static String parseInput(String input, TaskList tasks) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
        case "mark":
            if (parts.length < 2) {
                return("Usage: mark <index>");
            }
            try {
                int index = Integer.parseInt(parts[1]);
                return(tasks.markItem(index));
            } catch (NumberFormatException e) {
                return("Type in a number in the range");
            }

        case "unmark":
            if (parts.length < 2) {
                return("Usage: unmark <index>");
            }
            try {
                int index = Integer.parseInt(parts[1]);
                return(tasks.unmarkItem(index));
            } catch (NumberFormatException e) {
                return("Type in a number in the range");
            }

        case "list":
            return(tasks.toString());

        case "todo":
            if (parts.length < 2) {
                return("Usage: todo <task>");
            }
            return(tasks.addItem(new ToDo(parts[1])));

        case "deadline": {
            if (parts.length < 2) {
                return("Usage: deadline <task> /by <time>");
            }

            Pattern pattern = Pattern.compile("^deadline (.+) /by (.+)$");
            Matcher m = pattern.matcher(input);
            if (!m.matches()) {
                return("Usage: deadline <task> /by <time>");
            }

            String desc = m.group(1).trim();
            String byStr = m.group(2).trim();
            try {
                LocalDate dueDate = LocalDate.parse(byStr, DATE_FORMAT);
                return(tasks.addItem(new Deadline(desc, dueDate)));
            } catch (DateTimeParseException e) {
                return("Invalid date format, use yyyy-MM-dd");
            }
        }

        case "event": {
            if (parts.length < 2) {
                return ("Usage: event <task> /from <startTime> /to <endTime>");
            }

            Pattern pattern = Pattern.compile("^event (.+) /from (.+) /to (.+)$");
            Matcher m = pattern.matcher(input);
            if (!m.matches()) {
                return ("Usage: event <task> /from <startTime> /to <endTime>");
            }

            String desc = m.group(1).trim();
            String fromStr = m.group(2).trim();
            String toStr = m.group(3).trim();
            try {
                LocalDate from = LocalDate.parse(fromStr, DATE_FORMAT);
                LocalDate to = LocalDate.parse(toStr, DATE_FORMAT);
                return (tasks.addItem(new Event(desc, from, to)));
            } catch (DateTimeParseException e) {
                return ("Invalid date format, use yyyy-MM-dd");
            }
        }

        case "delete": {
            if (parts.length < 2) {
                return("Usage: delete <index>");
            }
            try {
                int index = Integer.parseInt(parts[1]);
                return(tasks.deleteItem(index));
            } catch (NumberFormatException e) {
                return("Type in a number in the range");
            }
        }

        case "find":
            if (parts.length < 2) {
                return("Usage: find <query>");
            }
            return(tasks.findTask(parts[1]));

        case "update":
            if (parts.length < 2) {
                return("Usage: update <index> /*");
            }
            return(Updater.handleUpdate(input, tasks));

        case "help":
            if (parts.length == 1) {
                return Help.getHelp(null);
            } else {
                return Help.getHelp(parts[1]);
            }

        default:
            return(input);
        }
    }
}
