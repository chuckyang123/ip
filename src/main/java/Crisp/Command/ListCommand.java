package Crisp.Command;

import Crisp.Task.*;
import Crisp.Util.*;

/**
 * Represents a command to list all tasks in the TaskList.
 * When executed, it prints all tasks with their indices to the user via the Ui.
 */
public class ListCommand extends Command {

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
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + ". " + tasks.get(i));
        }
    }
}
