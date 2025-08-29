package crisp.command;

import crisp.task.Task;
import crisp.task.TaskList;
import crisp.util.Storage;
import crisp.util.Ui;

/**
 * Represents a command that searches for tasks containing specified keywords.
 * Supports searching with multiple keywords. The search is case-insensitive.
 */
public class SearchCommand extends Command {
    /** The keywords to search for in task descriptions. */
    private final String[] keywords;

    /**
     * Constructs a SearchCommand with the given keywords.
     *
     * @param keywords one or more keywords to search for
     * @throws IllegalArgumentException if no keywords are provided
     */
    public SearchCommand(String... keywords) {
        if (keywords == null || keywords.length == 0) {
            throw new IllegalArgumentException("You must provide at least one keyword.");
        }
        this.keywords = keywords;
    }

    /**
     * Executes the search command, printing all tasks that match any of the keywords.
     * The search is case-insensitive. Tasks are printed with numbering in the order
     * they appear in the TaskList. If no tasks match, a message is printed.
     *
     * @param tasks the TaskList containing all tasks
     * @param ui the Ui object for printing messages
     * @param storage the Storage object (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(" Here are the matching tasks in your list:");

        boolean found = false;
        int count = 1;

        for (Task task : tasks.getAll()) {
            for (String keyword : keywords) {
                if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println(" " + count + ". " + task);
                    found = true;
                    count++;
                    break; // Avoid printing the same task multiple times if multiple keywords match
                }
            }
        }

        if (!found) {
            System.out.println(" No tasks match your search.");
        }
    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false since search does not exit the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
