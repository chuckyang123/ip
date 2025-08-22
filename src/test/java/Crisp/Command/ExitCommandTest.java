package Crisp.Command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {

    @Test
    public void testExitReturnsTrue() {
        // Example: suppose TodoCommand has an execute() method
        ExitCommand cmd = new ExitCommand();

        // Replace with your expected behavior
        boolean result = cmd.isExit();

        assertTrue(result, "isExit() should return true");
    }
}
