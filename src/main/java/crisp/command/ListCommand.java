package crisp.command;

import crisp.task.TaskList;
import crisp.util.Storage;
import crisp.util.Ui;

/**
 * Represents a command to list all tasks in the TaskList.
 * When executed, it prints all tasks with their indices to the user via the Ui.
 */


public class ListCommand extends Command {
    private String message;
    /**
     * Executes the list command.
     * Iterates through the TaskList and prints each task with its index.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui for printing messages
     * @param storage the Storage for persisting tasks (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Preconditions
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        // storage is unused, but we can still check it’s not null
        assert storage != null : "Storage must not be null";

        message = "Here are the tasks in your list:\n";

        int size = tasks.size();
        assert size >= 0 : "TaskList size should never be negative";

        for (int i = 0; i < size; i++) {
            assert tasks.get(i) != null : "Task at index " + i + " should not be null";
            message = message + (i + 1) + ". " + tasks.get(i) + "\n";
        }

        // Postcondition: message should not be null or empty
        assert message != null && !message.isEmpty()
                : "ListCommand should always produce a non-empty message";
    }


    @Override
    public String getMessage() {
        return message;
    }
}
