package main;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Storer {
    private final File file;

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
                Bob.printer("Error creating file: " + e.getMessage() + "\n");
            }
        }
    }

    public void save(List taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
            ArrayList<Task> taskArrayList = taskList.getTaskList();
            for (Task things : taskArrayList) {
                writer.write(things.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            Bob.printer("Error accessing data: " + e.getMessage() + "\n");
        }
    }

    public List load(List taskList) {
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
                    }
                } else if (line.startsWith("Todo")) {
                    Pattern p = Pattern.compile("^Todo /done (\\d) /des (.+)$");
                    Matcher m = p.matcher(line);
                    if (m.matches()) {
                        boolean isDone = m.group(1).equals("1");
                        String desc = m.group(2);
                        taskList.fileAddItem(new ToDos(desc, isDone));
                    }
                } else if (line.startsWith("Deadline")) {
                    Pattern p = Pattern.compile("^Deadline /done (\\d) /des (.+) /by (.+)$");
                    Matcher m = p.matcher(line);
                    if (m.matches()) {
                        boolean isDone = m.group(1).equals("1");
                        String desc = m.group(2);
                        String by = m.group(3);
                        taskList.fileAddItem(new Deadlines(desc, isDone, by));
                    }
                } else if (line.startsWith("Event")) {
                    Pattern p = Pattern.compile("^Event /done (\\d) /des (.+) /from (.+) /to (.+)$");
                    Matcher m = p.matcher(line);
                    if (m.matches()) {
                        boolean isDone = m.group(1).equals("1");
                        String desc = m.group(2);
                        String from = m.group(3);
                        String to = m.group(4);
                        taskList.fileAddItem(new Events(desc, isDone, from, to));
                    }
                }
            }
        } catch (IOException e) {
            Bob.printer("Error accessing data: " + e.getMessage() + "\n");
        }

        return taskList;
    }
}
