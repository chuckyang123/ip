package crisp.command;

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
    private String message;
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
        // Preconditions
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert keywords != null && keywords.length > 0 : "Keywords must not be null or empty";

        java.util.concurrent.atomic.AtomicInteger count = new java.util.concurrent.atomic.AtomicInteger(1);

        // Build message with matching tasks
        String matchedTasks = tasks.getAll().stream()
                .peek(task -> {
                    assert task != null : "Task in TaskList should not be null";
                    assert task.getDescription() != null : "Task description should not be null";
                })
                .filter(task -> java.util.Arrays.stream(keywords)
                        .peek(kw -> {
                            assert kw != null && !kw.trim().isEmpty() : "Keyword must not be null or empty";
                        })
                    .anyMatch(kw -> task.getDescription().toLowerCase().contains(kw.toLowerCase())))
                .map(task -> " " + count.getAndIncrement() + ". " + task)
                .reduce("", (acc, t) -> acc + t + "\n");

        message = " Here are the matching tasks in your list:\n"
                + (matchedTasks.isEmpty() ? " No tasks match your search." : matchedTasks);

        // Postcondition: message should always exist
        assert message != null && !message.isEmpty() : "SearchCommand should always produce a non-empty message";
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

    @Override
    public String getMessage() {
        return message;
    }
}
