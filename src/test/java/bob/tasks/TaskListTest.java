package bob.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskListTest {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void test1() {
        Deadlines test = new Deadlines("thing", LocalDate.parse("2025-12-25", DATE_FORMAT));
        TaskList testList = new TaskList();
        testList.fileAddItem(test);
        testList.markItem(1);
        assertTrue(test.getCompletionStatus());
    }
}
