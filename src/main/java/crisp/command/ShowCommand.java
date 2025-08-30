package crisp.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import crisp.task.Deadline;
import crisp.task.Event;
import crisp.task.Task;
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
        StringBuilder message = new StringBuilder();
        try {
            LocalDate queryDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            message.append("Tasks occurring on ")
                    .append(queryDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                    .append(":\n");

            boolean found = false;
            for (Task task : tasks.getAll()) {
                if (task instanceof Deadline dl && dl.getBy().isEqual(queryDate)) {
                    message.append("  ").append(dl).append("\n");
                    found = true;
                } else if (task instanceof Event ev && !queryDate.isBefore(ev.getFrom())
                        && !queryDate.isAfter(ev.getTo())) {
                    message.append("  ").append(ev).append("\n");
                    found = true;
                }
            }

            if (!found) {
                message.append("No tasks found on this date.\n");
            }

            this.message = message.toString(); // store in a field for getMessage()

        } catch (DateTimeParseException e) {
            this.message = "Invalid date format. Use yyyy-MM-dd. Example: 2019-12-02";
            ui.showError(this.message);
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
