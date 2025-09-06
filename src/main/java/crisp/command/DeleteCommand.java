package crisp.command;

import crisp.task.Task;
import crisp.task.TaskList;
import crisp.util.Storage;
import crisp.util.Ui;


/**
 * Represents a command to delete a task from the TaskList.
 * When executed, the task at the specified index is removed from the TaskList,
 * a confirmation message is shown to the user via the Ui,
 * and the updated list is saved to storage.
 */
public class DeleteCommand extends Command {
    private final int index; // index of task to delete (0-based internally)
    private String message;
    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index the index of the task to delete (0-based)
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     * Removes the task at the specified index from the TaskList,
     * shows a confirmation message via Ui, and saves the updated TaskList.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui for printing messages
     * @param storage the Storage for persisting tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Preconditions
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert index >= 0 && index < tasks.size()
                : "Index must be within TaskList bounds";

        int oldSize = tasks.size();

        Task removedTask = tasks.delete(index);

        // Postconditions
        assert removedTask != null : "Deleted task should not be null";
        assert tasks.size() == oldSize - 1
                : "TaskList size should decrease by 1 after deletion";

        message = ui.showDeletedTask(removedTask, tasks.size());

        assert message != null && !message.isEmpty()
                : "Ui.showDeletedTask should return a non-empty message";

        storage.save(tasks);
    }


    /**
     * Indicates that this command does not exit the application.
     *
     * @return false, as this command does not terminate the program
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
