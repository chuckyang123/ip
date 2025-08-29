package crisp.command;


import crisp.task.Task;
import crisp.task.TaskList;
import crisp.util.Storage;
import crisp.util.Ui;

/**
 * Represents a command to mark a task as not done (unmark) in the task list.
 * When executed, this command retrieves the task at the specified index from
 * the TaskList, marks it as not done, displays a confirmation message via Ui,
 * and saves the updated task list using Storage.
 */


public class UnmarkCommand extends Command {

    /** The index of the task to unmark (0-based). */
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index the index of the task to mark as not done
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to unmark a task.
     *
     * @param tasks   the TaskList containing the task
     * @param ui      the Ui for displaying confirmation messages
     * @param storage the Storage used to save the updated task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        task.markUndone();
        ui.showMarkedTask(task, false);
        storage.save(tasks);
    }

    /**
     * Indicates that this command does not exit the application.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
