package Crisp.Command;

import Crisp.Task.Task;
import Crisp.Task.TaskList;
import Crisp.Util.Storage;
import Crisp.Util.Ui;

/**
 * Represents a command to mark a task as done in the TaskList.
 * When executed, it updates the task's status, displays a message via Ui, 
 * and saves the updated TaskList to Storage.
 */

public class MarkCommand extends Command {

    /** The index of the task to mark as done (0-based). */
    private final int index;

    /**
     * Constructs a MarkCommand for the task at the specified index.
     *
     * @param index the 0-based index of the task to mark as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command.
     * Marks the task at the specified index as done, displays a confirmation message,
     * and saves the updated TaskList to Storage.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui for printing messages
     * @param storage the Storage for persisting the updated TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        task.markDone();
        ui.showMarkedTask(task, true);
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
