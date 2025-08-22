package Crisp.Command;

import Crisp.Task.*;
import Crisp.Util.*;

/**
 * Represents a command to add a Todo task to the task list.
 * 
 * When executed, this command creates a new Todo task with the given description,
 * adds it to the TaskList, displays a confirmation message via Ui, and saves
 * the updated task list using Storage.
 */
public class TodoCommand extends Command {

    /** The description of the Todo task. */
    private final String description;

    /**
     * Constructs a TodoCommand with the specified task description.
     *
     * @param description the description of the Todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a Todo task.
     *
     * @param tasks   the TaskList to which the new task is added
     * @param ui      the Ui for displaying confirmation messages
     * @param storage the Storage used to save the updated task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage ) {
        Task newTask = new Todo(description);
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks.size());
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
