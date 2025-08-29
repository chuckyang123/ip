package crisp.command;

import crisp.task.Task;
import crisp.task.TaskList;
import crisp.util.Storage;
import crisp.util.Ui;

/**
 * Represents a command to add a task to the TaskList.
 * When executed, the task is added to the TaskList, a confirmation
 * message is shown to the user via the Ui, and the updated list is saved
 * to storage.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the given task.
     *
     * @param task the task to be added to the TaskList
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command.
     * Adds the task to the TaskList, prints a confirmation message via Ui,
     * and saves the updated list to storage.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui for printing messages
     * @param storage the Storage for persisting tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showMessage("Got it. I've added this task:\n   " + task
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }
}
