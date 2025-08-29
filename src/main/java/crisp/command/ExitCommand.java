package crisp.command;

import crisp.task.TaskList;
import crisp.util.Storage;
import crisp.util.Ui;
/**
 * Represents a command to exit the application.
 * When executed, it shows a farewell message to the user.
 */


public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * Prints a goodbye message via the Ui.
     *
     * @param tasks   the TaskList containing all tasks (unused)
     * @param ui      the Ui for printing messages
     * @param storage the Storage for persisting tasks (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates that this command exits the application.
     *
     * @return true, since this command terminates the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
