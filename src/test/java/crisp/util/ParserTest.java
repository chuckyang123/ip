package Crisp.Util;

import Crisp.Command.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseExitCommand() throws Exception {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void testParseListCommand() throws Exception {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }


    @Test
    public void testParseShowCommand() throws Exception {
        Command cmd = Parser.parse("show 2025-08-25");
        assertTrue(cmd instanceof ShowCommand);
    }

    @Test
    public void testParseTodoCommand() throws Exception {
        Command cmd = Parser.parse("todo read book");
        assertTrue(cmd instanceof TodoCommand);
    }

    @Test
    public void testParseTodoEmptyDescriptionThrows() {
        Exception e = assertThrows(Exception.class, () -> Parser.parse("todo"));
        assertTrue(e.getMessage().contains("provide a description"));
    }

    @Test
    public void testParseDeadlineCommand() throws Exception {
        Command cmd = Parser.parse("deadline submit report /by 2025-08-25");
        assertTrue(cmd instanceof DeadlineCommand);
    }

    @Test
    public void testParseDeadlineMissingByThrows() {
        Exception e = assertThrows(Exception.class, () -> Parser.parse("deadline submit report"));
        assertTrue(e.getMessage().contains("/by date/time"));
    }

    @Test
    public void testParseEventCommand() throws Exception {
        Command cmd = Parser.parse("event meeting /from 2025-08-24 /to 2025-08-25");
        assertTrue(cmd instanceof EventCommand);
    }

    @Test
    public void testParseEventMissingFromOrToThrows() {
        Exception e1 = assertThrows(Exception.class, () -> Parser.parse("event meeting /to 2025-08-25"));
        assertTrue(e1.getMessage().contains("both /from and /to"));

        Exception e2 = assertThrows(Exception.class, () -> Parser.parse("event meeting /from 2025-08-24"));
        assertTrue(e2.getMessage().contains("both /from and /to"));
    }

    @Test
    public void testParseUnknownCommandThrows() {
        Exception e = assertThrows(Exception.class, () -> Parser.parse("random command"));
        assertTrue(e.getMessage().contains("don't know what that means"));
    }
}
