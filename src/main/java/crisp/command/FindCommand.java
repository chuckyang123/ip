package Crisp.Command;

import Crisp.Task.Task;
import Crisp.Task.TaskList;
import Crisp.Util.Storage;
import Crisp.Util.Ui;

import java.util.List;

/**
 * Represents a command that searches for tasks in the task list
 * whose descriptions contain a given keyword. The search is
 * case-insensitive.
 *
 * <p>Example usage:</p>
 * <pre>
 *     find homework
 * </pre>
 * This will display all tasks with "homework" in their description.
 */
public class FindCommand extends Command {
    /** The keyword to search for. */
    private final String keyword;

    /**
     * Creates a new {@code FindCommand} with the given search keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by filtering tasks in the provided {@link TaskList}
     * whose descriptions contain the search keyword. Results are displayed through
     * the {@link Ui}. If no tasks match, an appropriate message is shown.
     *
     * @param tasks   the task list to search
     * @param ui      the user interface for displaying results
     * @param storage the storage system (not used in this command, but required by interface)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> allTasks = tasks.getAll();
        List<Task> matchingTasks = allTasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No tasks match your search keyword: " + keyword);
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return {@code false} since the find command does not exit the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
