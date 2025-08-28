package bob;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import bob.tasks.*;
import bob.util.*;

/**
 * Bob is a simple chatbot
 *
 * @author yhtham7
 * @version 0.1
 */

public class Bob {
    private static final String FILE_PATH = "./data/tasks.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Storer storer;
    private TaskList tasks;


    /**
     * Constructor for bob which accepts the filepath for data storage
     * @param filePath path to data
     */
    public Bob(String filePath) {
        this.storer = new Storer(filePath);
        this.tasks = storer.load(new TaskList());
    }


    /**
     * This method calls upon other utils to run the Ui and Storer
     */
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

    /**
     * Main method that creates a new Bob instance and runs it
     * @param args Unused.
     */
    public static void main(String[] args) {
        Bob current = new Bob(FILE_PATH);
        current.run();
    }
}
