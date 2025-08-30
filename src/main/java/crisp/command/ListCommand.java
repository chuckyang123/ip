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
        message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message = message + (i + 1) + ". " + tasks.get(i) + "\n";
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
