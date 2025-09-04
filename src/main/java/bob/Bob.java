package bob;

import bob.tasks.*;
import bob.util.*;

import java.time.format.DateTimeFormatter;

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
    private boolean isFinished = false;


    /**
     * Constructor for bob which accepts the filepath for data storage
     * @param filePath path to data
     */
    public Bob(String filePath) {
        this.storer = new Storer(filePath);
        this.tasks = storer.load(new TaskList());
    }


    /**
     * Sends input to parser for appropriate action
     * @param input
     * @return
     */
    public String getResponse(String input) {
        String res = Parser.parseInput(input, this.tasks);
        if (res.equalsIgnoreCase("bye")) {
            this.storer.save(this.tasks);
            this.isFinished = true;
        }
        return res;
    }

    /**
     * returns if
     * @return
     */
    public boolean shouldExit(){
        return isFinished;
    }
}
