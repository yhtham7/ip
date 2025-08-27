package bob.util;

import bob.tasks.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void test1() {
        TaskList baseList = new TaskList();
        TaskList testList = new TaskList();
        baseList.addItem(new ToDos("thing"));
        Parser.parseInput("todo thing", testList);

        String base = baseList.getTaskList().get(0).toString();
        String test = testList.getTaskList().get(0).toString();
        assertEquals(base, test);
    }
}
