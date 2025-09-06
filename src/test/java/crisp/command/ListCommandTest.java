package crisp.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import crisp.task.TaskList;
import crisp.task.Todo;
import crisp.util.Storage;
import crisp.util.Ui;


public class ListCommandTest {

    // Custom Ui class for testing
    static class TestUi extends Ui {
        @SuppressWarnings("checkstyle:VisibilityModifier")
        private List<String> messages = new ArrayList<>();
    }

    @Test
    public void testExecuteShowsAllTasks() {
        // Prepare test data
        TaskList tasks = new TaskList();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));

        TestUi ui = new TestUi();
        Storage storage = new Storage("./data/crisp.txt"); // Assuming Storage can be instantiated

        ListCommand cmd = new ListCommand();
        cmd.execute(tasks, ui, storage);
        String actual = cmd.getMessage();
        // Expected output
        String expected = "Here are the tasks in your list:\n"
                        + "1. [T][ ] Task 1\n" + "2. [T][ ] Task 2\n";

        // Verify that the messages match
        assertEquals(expected, actual);
    }
}
