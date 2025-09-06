package crisp.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crisp.task.Deadline;
import crisp.task.Status;



public class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Ui ui;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        ui = new Ui();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testShowWelcome() {
        ui.showWelcome();
        String output = outContent.toString();
        assertTrue(output.contains("Hello! I'm Crisp"));
        assertTrue(output.contains("What can I do for you?"));
    }


    @Test
    public void testShowError() {
        ui.showError("Something went wrong");
        assertTrue(outContent.toString().contains("OOPS!!! Something went wrong"));
    }

    @Test
    public void testShowAddedTask() {
        Deadline task = new Deadline("Submit report", "2025-08-25", Status.NOT_DONE);
        String output = ui.showAddedTask(task, 1);

        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains("Submit report"));
        assertTrue(output.contains("Now you have 1 tasks in the list."));
    }

    @Test
    public void testShowDeletedTask() {
        Deadline task = new Deadline("Submit report", "2025-08-25", Status.NOT_DONE);
        String output = ui.showDeletedTask(task, 0);

        assertTrue(output.contains("Noted. I've removed this task:"));
        assertTrue(output.contains("Submit report"));
        assertTrue(output.contains("Now you have 0 tasks in the list."));
    }

    @Test
    public void testShowMarkedTask() {
        Deadline task = new Deadline("Submit report", "2025-08-25", Status.NOT_DONE);

        String output = ui.showMarkedTask(task, true);
        assertTrue(output.contains("Nice! I've marked this task as done:"));
        assertTrue(output.contains("Submit report"));


        output = ui.showMarkedTask(task, false);
        assertTrue(output.contains("OK, I've marked this task as not done yet:"));
    }
}
