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

    public Bob(String filePath) {
        this.storer = new Storer(filePath);
        this.tasks = storer.load(new TaskList());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        Ui.printStart();

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                Ui.printEnd();
                this.storer.save(this.tasks);
                break;
            }

            Parser.parseInput(input, this.tasks);
        }
    }

    public static void main(String[] args) {
        Bob current = new Bob(FILE_PATH);
        current.run();
    }
}
