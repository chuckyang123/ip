package crisp.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import crisp.task.Deadline;
import crisp.task.Event;
import crisp.task.TaskList;
import crisp.util.Storage;
import crisp.util.Ui;
/**
 * Represents a command to display all tasks occurring on a specific date.
 * The date is provided as a string in the format "yyyy-MM-dd".
 * When executed, this command lists all Deadline and Event tasks
 * that match the specified date and prints them to the console.
 * If the date format is invalid, an error message is displayed via Ui.
 */

public class ShowCommand extends Command {

    /** The date string provided by the user in "yyyy-MM-dd" format. */
    private final String dateStr;
    private String message;
    /**
     * Constructs a ShowCommand with the specified date string.
     *
     * @param dateStr the date to filter tasks, in "yyyy-MM-dd" format
     */
    public ShowCommand(String dateStr) {
        this.dateStr = dateStr;
    }

    /**
     * Executes the show command.
     * Lists all tasks occurring on the given date and prints them to the console.
     * If no tasks are found, a message indicating this is printed.
     * If the date string is invalid, an error message is displayed via Ui.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui for printing error messages
     * @param storage the Storage (unused in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Preconditions
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert dateStr != null && !dateStr.trim().isEmpty()
                : "Date string must not be null or empty";

        try {
            LocalDate queryDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            assert queryDate != null : "Parsed queryDate should not be null";

            String header = "Tasks occurring on " + queryDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":\n";

            java.util.List<String> matchingTasks = tasks.getAll().stream()
                    .filter(task -> {
                        if (task instanceof Deadline dl) {
                            assert dl.getBy() != null : "Deadline date must not be null";
                            return dl.getBy().isEqual(queryDate);
                        } else if (task instanceof Event ev) {
                            assert ev.getFrom() != null : "Event start date must not be null";
                            assert ev.getTo() != null : "Event end date must not be null";
                            return !queryDate.isBefore(ev.getFrom()) && !queryDate.isAfter(ev.getTo());
                        }
                        return false;
                    })
                    .map(task -> "  " + task)
                    .toList(); // Collect matching tasks as a list

            if (matchingTasks.isEmpty()) {
                this.message = header + "No tasks found on this date.\n";
            } else {
                this.message = header + String.join("\n", matchingTasks) + "\n";
            }

            // Postcondition
            assert this.message != null && !this.message.isEmpty()
                    : "ShowCommand must produce a non-empty message";

        } catch (DateTimeParseException e) {
            this.message = "Invalid date format. Use yyyy-MM-dd. Example: 2019-12-02";
            ui.showError(this.message);

            // Postcondition for error case
            assert this.message.startsWith("Invalid date format")
                    : "Error message should clearly indicate invalid date format";
        }
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

    @Override
    public String getMessage() {
        return message;
    }
}
