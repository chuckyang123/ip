package Crisp.Command;

import Crisp.Task.Deadline;
import Crisp.Task.Event;
import Crisp.Task.TaskList;
import Crisp.Util.Storage;
import Crisp.Util.Ui;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowCommandTest {

    // Custom Ui class for testing
    static class TestUi extends Ui {
        boolean lineShown = false;
        String errorMessage = null;

        @Override
        public void showLine() {
            lineShown = true;
        }

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

        // Capture System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Act
        cmd.execute(tasks, ui, storage);

        // Restore System.out
        System.setOut(originalOut);

        // Assert
        String output = outContent.toString();
        String normalized = output.replaceAll("\\s+", " ").trim();
        assertEquals("Tasks occurring on 8月 25 2025: [D][ ] Submit report (by: 8月 25 2025)",normalized);

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
