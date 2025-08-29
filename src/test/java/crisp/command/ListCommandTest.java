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
        List<String> messages = new ArrayList<>();

        @Override
        public void showMessage(String message) {
            messages.add(message);
        }
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

        // Expected output
        List<String> expected = List.of(
                "Here are the tasks in your list:",
                "1. [T][ ] Task 1",
                "2. [T][ ] Task 2"
        );

        // Verify that the messages match
        assertEquals(expected, ui.messages);
    }
}
