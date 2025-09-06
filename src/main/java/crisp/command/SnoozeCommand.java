package crisp.command;

import crisp.task.Deadline;
import crisp.task.Event;
import crisp.task.Task;
import crisp.task.TaskList;
import crisp.util.Storage;
import crisp.util.Ui;

/**
 * Represents a command to snooze (postpone) a task in the TaskList.
 * <p>
 * This command supports {@link Deadline} and {@link Event} tasks.
 * When executed, it postpones the task's date(s) by a specified number of days.
 * The updated task is saved to storage, and a confirmation message is generated.
 * </p>
 *
 * Usage example:
 * <pre>
 *     SnoozeCommand cmd = new SnoozeCommand(2, 3); // snooze task at index 2 by 3 days
 *     cmd.execute(tasks, ui, storage);
 *     System.out.println(cmd.getMessage());
 * </pre>
 */
public class SnoozeCommand extends Command {

    /** The index of the task to snooze (0-based). */
    private final int index;

    /** The number of days to postpone the task. */
    private final int days;

    /** Message describing the result of executing this command. */
    private String message;

    /**
     * Constructs a SnoozeCommand for a given task index and number of days.
     *
     * @param index the index of the task in the TaskList (0-based)
     * @param days  the number of days to postpone the task
     */
    public SnoozeCommand(int index, int days) {
        this.index = index;
        this.days = days;
    }

    /**
     * Executes the snooze command.
     * <p>
     * If the task is a {@link Deadline}, its due date is postponed.
     * If the task is an {@link Event}, both the start and end dates are postponed.
     * Updates the {@link Storage} and generates a confirmation message.
     * If the task is not a Deadline or Event, a message indicates it cannot be snoozed.
     * </p>
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui for printing messages
     * @param storage the Storage for persisting changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index); // assume valid index
        assert task != null : "Task should not be null";

        if (task instanceof Deadline dl) {
            dl.postponeByDays(days);
        } else if (task instanceof Event ev) {
            ev.postponeByDays(days);
        } else {
            message = "Cannot snooze this type of task.";
            return;
        }

        message = "Task has been snoozed by " + days + " day(s): " + task;
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

    /**
     * Returns the message generated after executing the command.
     *
     * @return confirmation or error message
     */
    @Override
    public String getMessage() {
        return message;
    }
}
