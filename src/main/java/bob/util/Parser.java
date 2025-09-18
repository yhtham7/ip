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
            return(Parser.updater(input, tasks));
        default:
            return(input);
        }
    }

    /**
     * Parses update keywords to allow for the editing of tasks
     * @param input update string to be parsed
     * @param tasks tasklist in which to update
     * @return result of update attempt
     */
    public static String updater(String input, TaskList tasks) {
        Pattern desPattern = Pattern.compile("^update (\\d+) /des (.+)$");
        Pattern byPattern = Pattern.compile("^update (\\d+) /by (.+)$");
        Pattern eventPattern = Pattern.compile("^update (\\d+) /from (.+) /to (.+)$");

        Matcher m;

        if ((m = desPattern.matcher(input)).matches()) {
            int index = Integer.parseInt(m.group(1));
            String newDesc = m.group(2);
            return(tasks.updateDescription(index, newDesc));

        } else if ((m = byPattern.matcher(input)).matches()) {
            int index = Integer.parseInt(m.group(1));
            String newDueDateString = m.group(2);
            try {
                LocalDate newDueDate = LocalDate.parse(newDueDateString, DATE_FORMAT);
                return(tasks.updateDeadline(index, newDueDate));
            } catch (DateTimeParseException e) {
                return("Invalid date format, use yyyy-MM-dd");
            }

        } else if ((m = eventPattern.matcher(input)).matches()) {
            int index = Integer.parseInt(m.group(1));
            String newFromString = m.group(2);
            String newToString = m.group(3);
            try {
                LocalDate newFrom = LocalDate.parse(newFromString, DATE_FORMAT);
                LocalDate newTo = LocalDate.parse(newToString, DATE_FORMAT);
                return(tasks.updateEvent(index, newFrom, newTo));
            } catch (DateTimeParseException e) {
                return ("Invalid date format, use yyyy-MM-dd");
            }

        } else {
            return("""
                    Usage:
                    update <index> /des <new description>
                    update <index> /by <new deadline>
                    update <index> /from <new start> /to <new end>
                    """);
        }
    }
}
