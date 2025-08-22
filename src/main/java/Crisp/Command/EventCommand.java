package Crisp.Command;

import Crisp.Task.*;
import Crisp.Util.*;

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
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks.size());
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
}
