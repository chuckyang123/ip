package Crisp.Command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoCommandTest {

    @Test
    public void testExitReturnsFalse() {
        // Example: suppose TodoCommand has an execute() method
        TodoCommand cmd = new TodoCommand("Sample Task");

        // Replace with your expected behavior
        boolean result = cmd.isExit();

        assertFalse(result, "isExit() should return false");
    }
}
