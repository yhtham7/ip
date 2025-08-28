package bob.util;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import bob.tasks.*;
import bob.misc.*;

/**
 * Storer class that is accesses data to save or load tasks
 */
public class Storer {
    private final File file;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * Constructor for Storer class
     * @param filePath path to access data file
     */
    public Storer(String filePath) {
        this.file = new File(filePath);

        File parentFolder = this.file.getParentFile(); // gets "data" if filePath = "data/tasks.txt"
        if (parentFolder != null && !parentFolder.exists()) {
            boolean created = parentFolder.mkdirs();
        }

        if (!this.file.exists()) {
            try {
                boolean fileCreated = this.file.createNewFile();
            } catch (IOException e) {
                Ui.printer("Error creating file: " + e.getMessage() + "\n");
            }
        }
    }

    /**
     * Saves the associated TaskList into data file
     * @param taskList taskList to be saved
     */
    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
            ArrayList<Task> taskArrayList = taskList.getTaskList();
            for (Task things : taskArrayList) {
                writer.write(things.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            Ui.printer("Error accessing data: " + e.getMessage() + "\n");
        }
    }

    /**
     * Loads data from data file into associated taskList
     * @param taskList taskList to be modified
     * @return taskList
     */
    public TaskList load(TaskList taskList) {
        if (!this.file.exists()){
            return taskList;
        }

        try (Scanner sc = new Scanner(this.file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.startsWith("Task")) {
                    Pattern p = Pattern.compile("^Task /done (\\d) /des (.+)$");
                    Matcher m = p.matcher(line);
                    if (m.matches()) {
                        boolean isDone = m.group(1).equals("1");
                        String desc = m.group(2);
                        taskList.fileAddItem(new Task(desc, isDone));
                    } else {
                        throw new InvalidDataFormatException("Invalid Task format: " + line);
                    }
                } else if (line.startsWith("Todo")) {
                    Pattern p = Pattern.compile("^Todo /done (\\d) /des (.+)$");
                    Matcher m = p.matcher(line);
                    if (m.matches()) {
                        boolean isDone = m.group(1).equals("1");
                        String desc = m.group(2);
                        taskList.fileAddItem(new ToDos(desc, isDone));
                    } else {
                        throw new InvalidDataFormatException("Invalid Task format: " + line);
                    }
                } else if (line.startsWith("Deadline")) {
                    Pattern p = Pattern.compile("^Deadline /done (\\d) /des (.+) /by (.+)$");
                    Matcher m = p.matcher(line);
                    if (m.matches()) {
                        boolean isDone = m.group(1).equals("1");
                        String desc = m.group(2);
                        String byStr = m.group(3);
                        try {
                            LocalDate byDate = LocalDate.parse(byStr, DATE_FORMAT);
                            taskList.fileAddItem(new Deadlines(desc, isDone, byDate));
                        } catch (DateTimeParseException e) {
                            Ui.printer("Invalid date format, use yyyy-MM-dd\n");
                        }
                    } else {
                        throw new InvalidDataFormatException("Invalid Task format: " + line);
                    }
                } else if (line.startsWith("Event")) {
                    Pattern p = Pattern.compile("^Event /done (\\d) /des (.+) /from (.+) /to (.+)$");
                    Matcher m = p.matcher(line);
                    if (m.matches()) {
                        boolean isDone = m.group(1).equals("1");
                        String desc = m.group(2);
                        String fromStr = m.group(3);
                        String toStr = m.group(4);
                        try {
                            LocalDate from = LocalDate.parse(fromStr, DATE_FORMAT);
                            LocalDate to = LocalDate.parse(toStr, DATE_FORMAT);
                            taskList.fileAddItem(new Events(desc, isDone, from, to));
                        } catch (DateTimeParseException e) {
                            Ui.printer("Invalid date format, use yyyy-MM-dd\n");
                        }
                    } else {
                        throw new InvalidDataFormatException("Invalid Task format: " + line);
                    }
                } else {
                    throw new InvalidDataFormatException("Invalid Task format: " + line);
                }
            }
        } catch (IOException e) {
            Ui.printer("Error accessing data: " + e.getMessage() + "\n");
        } catch (InvalidDataFormatException e) {
            Ui.printer(e.getMessage());
        }

        return taskList;
    }
}
