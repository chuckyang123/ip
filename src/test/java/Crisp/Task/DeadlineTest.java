package Crisp.Task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void testConstructorAndGetters() {
        Deadline deadline = new Deadline("Submit report", "2025-08-25");

        assertEquals("Submit report", deadline.getDescription());
        assertEquals(TaskType.DEADLINE, deadline.getType());
        assertEquals(Status.NOT_DONE, deadline.getStatus());
        assertEquals(LocalDate.of(2025, 8, 25), deadline.getBy());
    }

    @Test
    public void testConstructorWithStatus() {
        Deadline deadline = new Deadline("Finish homework", "2025-08-26", Status.DONE);
        assertEquals(Status.DONE, deadline.getStatus());
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Submit report", "2025-08-25");
        String expected = "[D][ ] Submit report (by: 8月 25 2025)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToFileFormat() {
        Deadline deadline = new Deadline("Submit report", "2025-08-25");
        String expected = "D | 0 | Submit report | 2025-08-25";
        assertEquals(expected, deadline.toFileFormat());
    }

    @Test
    public void testInvalidDateThrows() {
        // Clearly invalid strings to ensure DateTimeParseException
        assertThrows(DateTimeParseException.class,
                () -> new Deadline("Invalid Deadline", "not-a-date"));
    }
}
