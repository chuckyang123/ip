package crisp.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import crisp.task.Deadline;
import crisp.task.TaskList;
import crisp.util.Storage;
import crisp.util.Ui;

public class ShowCommandTest {

    // Custom Ui class for testing
    static class TestUi extends Ui {
        @SuppressWarnings("checkstyle:VisibilityModifier")
        private boolean lineShown = false;
        @SuppressWarnings("checkstyle:VisibilityModifier")
        private String errorMessage = null;
        @Override
        public void showError(String message) {
            errorMessage = message;
        }
    }

    @Test
    public void testExecuteShowsDeadlineTask() {
        // Arrange
        TaskList tasks = new TaskList();
        tasks.add(new Deadline("Submit report", "2025-08-25"));

        TestUi ui = new TestUi();
        Storage storage = new Storage("./data/Crsip,txt"); // Assuming Storage can be instantiated

        ShowCommand cmd = new ShowCommand("2025-08-25");

        // Act
        cmd.execute(tasks, ui, storage);

        // Assert
        String output = cmd.getMessage();
        String normalized = output.replaceAll("\\s+", " ").trim();
        assertEquals("Tasks occurring on Aug 25 2025: [D][ ] Submit report (by: Aug 25 2025)", normalized);

    }

    @Test
    public void testExecuteInvalidDateShowsError() {
        TaskList tasks = new TaskList();
        TestUi ui = new TestUi();
        Storage storage = new Storage("./data/Crsip,txt");

        ShowCommand cmd = new ShowCommand("invalid-date");

        cmd.execute(tasks, ui, storage);

        assertTrue(ui.errorMessage != null);
        assertTrue(ui.errorMessage.contains("Invalid date format"));
    }
}
