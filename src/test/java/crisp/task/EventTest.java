package crisp.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testConstructorAndGetters() {
        Event event = new Event("Conference", "2025-08-24", "2025-08-26");

        assertEquals(LocalDate.of(2025, 8, 24), event.getFrom());
        assertEquals(LocalDate.of(2025, 8, 26), event.getTo());
        assertEquals("Conference", event.getDescription());
        assertEquals(TaskType.EVENT, event.getType());
        assertEquals(Status.NOT_DONE, event.getStatus());
    }

    @Test
    public void testConstructorWithStatus() {
        Event event = new Event("Meeting", "2025-08-24", "2025-08-24", Status.DONE);

        assertEquals(Status.DONE, event.getStatus());
    }

    @Test
    public void testToString() {
        Event event = new Event("Conference", "2025-08-24", "2025-08-26");
        String expected = "[E][ ] Conference (from: 8月 24 2025 to: 8月 26 2025)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToFileFormat() {
        Event event = new Event("Conference", "2025-08-24", "2025-08-26");
        String expected = "E | 0 | Conference | 2025-08-24 | 2025-08-26";
        assertEquals(expected, event.toFileFormat());
    }

    @Test
    public void testInvalidDateThrows() {
        assertThrows(DateTimeParseException.class, (
        ) -> new Event("Invalid Event", "notadate", "2025-08-26"));
        assertThrows(DateTimeParseException.class, (
        ) -> new Event("Invalid Event", "2025-08-01", "notadate"));
    }
}
