package crisp.command;

import crisp.task.Deadline;
import crisp.task.Task;
import crisp.task.TaskList;
import crisp.util.Storage;
import crisp.util.Ui;

/**
 * Represents a command to add a deadline task to the TaskList.
 * When executed, a new Deadline task is created from the given description and due date,
 * added to the TaskList, a confirmation message is shown to the user via the Ui,
 * and the updated list is saved to storage.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;
    private String message;

    /**
     * Constructs a DeadlineCommand with the given description and due date.
     *
     * @param description the description of the deadline task
     * @param by          the due date of the task in "yyyy-MM-dd" format
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command.
     * Creates a new Deadline task, adds it to the TaskList,
     * shows a confirmation message via Ui, and saves the updated TaskList.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui for printing messages
     * @param storage the Storage for persisting tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Preconditions: none of these should be null
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert description != null && !description.trim().isEmpty()
                : "Description must not be null or empty";
        assert by != null && !by.trim().isEmpty()
                : "Due date must not be null or empty";

        Task newTask = new Deadline(description, by);

        // Postcondition: task should be non-null
        assert newTask != null : "New Deadline task should have been created";

        int oldSize = tasks.size();
        tasks.add(newTask);

        // Postcondition: size should increase by exactly 1
        assert tasks.size() == oldSize + 1 : "TaskList size should increase by 1 after adding task";

        message = ui.showAddedTask(newTask, tasks.size());

        // Postcondition: message should not be null or empty
        assert message != null && !message.isEmpty()
                : "Ui.showAddedTask should return a non-empty message";

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
