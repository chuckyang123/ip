package crisp.command;

import crisp.task.Event;
import crisp.task.Task;
import crisp.task.TaskList;
import crisp.util.Storage;
import crisp.util.Ui;
/**
 * Represents a command to add an event task to the TaskList.
 * When executed, a new Event task is created from the given description, start date, and end date,
 * added to the TaskList, a confirmation message is shown to the user via the Ui,
 * and the updated list is saved to storage.
 */


public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;
    private String message;
    /**
     * Constructs an EventCommand with the given description, start date, and end date.
     *
     * @param description the description of the event task
     * @param from        the start date of the event in "yyyy-MM-dd" format
     * @param to          the end date of the event in "yyyy-MM-dd" format
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command.
     * Creates a new Event task, adds it to the TaskList,
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
        assert description != null && !description.trim().isEmpty()
                : "Description must not be null or empty";
        assert from != null && !from.trim().isEmpty()
                : "Start date must not be null or empty";
        assert to != null && !to.trim().isEmpty()
                : "End date must not be null or empty";

        Task newTask = new Event(description, from, to);

        // Postcondition: task created
        assert newTask != null : "New Event task should have been created";

        int oldSize = tasks.size();
        tasks.add(newTask);

        // Postcondition: size should increase by 1
        assert tasks.size() == oldSize + 1
                : "TaskList size should increase by 1 after adding an Event";

        message = ui.showAddedTask(newTask, tasks.size());

        // Postcondition: UI message should be valid
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
