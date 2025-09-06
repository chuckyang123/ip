package crisp.util;

import crisp.command.Command;
import crisp.command.DeadlineCommand;
import crisp.command.DeleteCommand;
import crisp.command.EventCommand;
import crisp.command.ExitCommand;
import crisp.command.ListCommand;
import crisp.command.MarkCommand;
import crisp.command.SearchCommand;
import crisp.command.ShowCommand;
import crisp.command.SnoozeCommand;
import crisp.command.TodoCommand;
import crisp.command.UnmarkCommand;

/**
 * The {@code Parser} class is responsible for interpreting user input
 * and converting it into the appropriate {@link Command} object.
 * It handles commands such as adding tasks, marking/unmarking tasks,
 * deleting tasks, listing tasks, showing tasks on a specific date,
 * and exiting the application.
 */
public class Parser {

    /**
     * Parses a given user input string and returns the corresponding {@link Command}.
     *
     * <p>Supported commands include:
     * <ul>
     *   <li>{@code bye} - exits the program</li>
     *   <li>{@code list} - lists all tasks</li>
     *   <li>{@code mark <index>} - marks a task as done</li>
     *   <li>{@code unmark <index>} - marks a task as not done</li>
     *   <li>{@code delete <index>} - deletes a task</li>
     *   <li>{@code show <yyyy-MM-dd>} - shows tasks occurring on a specific date</li>
     *   <li>{@code todo <description>} - adds a Todo task</li>
     *   <li>{@code deadline <description> /by <date>} - adds a Deadline task</li>
     *   <li>{@code event <description> /from <start> /to <end>} - adds an Event task</li>
     * </ul>
     *
     * @param input the raw user input
     * @return the corresponding {@link Command} object
     * @throws Exception if the input is invalid or cannot be parsed
     */
    public static Command parse(String input) throws Exception {
        assert input != null : "Input string should never be null";

        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            int num = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
            assert num >= 0 : "Task index for mark must be non-negative";
            return new MarkCommand(num);
        } else if (input.startsWith("search ")) {
            String arguments = input.substring(7).trim();
            assert arguments != null : "Search arguments should not be null";
            if (arguments.isEmpty()) {
                throw new Exception("You must provide at least one keyword. Example: search book");
            }
            String[] keywords = arguments.split("\\s+");
            assert keywords.length > 0 : "Search must have at least one keyword";
            return new SearchCommand(keywords);
        } else if (input.startsWith("unmark ")) {
            int num = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
            assert num >= 0 : "Task index for unmark must be non-negative";
            return new UnmarkCommand(num);
        } else if (input.startsWith("delete ")) {
            int index = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
            assert index >= 0 : "Task index for delete must be non-negative";
            return new DeleteCommand(index);
        } else if (input.startsWith("show ")) {
            String dateStr = input.substring(5).trim();
            assert !dateStr.isEmpty() : "Show command must have a date string";
            return new ShowCommand(dateStr);
        } else if (input.startsWith("todo")) {
            if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
                throw new Exception("Oops! You need to provide a description for your todo. Example: todo read book");
            }
            String description = input.substring(5).trim();
            assert !description.isEmpty() : "Todo description should not be empty";
            return new TodoCommand(description);
        } else if (input.startsWith("deadline")) {
            return getDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return getEventCommand(input);
        } else if (input.startsWith("snooze ")) {
            String[] parts = input.split("\\s+");
            if (parts.length != 3) {
                throw new Exception("Usage: snooze <taskIndex> <days>");
            }
            int index = Integer.parseInt(parts[1]) - 1;
            int days = Integer.parseInt(parts[2]);
            return new SnoozeCommand(index, days);
        } else {
            throw new Exception("I'm sorry, but I don't know what that means :-(");
        }
    }


    private static EventCommand getEventCommand(String input) throws Exception {
        if (input.length() <= 6) {
            throw new Exception(
                    "The description of an event cannot be empty. Example: event meeting /from 2pm /to 4pm");
        }
        String remaining = input.substring(6).trim();
        int fromIndex = remaining.indexOf("/from ");
        int toIndex = remaining.indexOf("/to ");
        if (fromIndex == -1 || toIndex == -1) {
            throw new Exception("Invalid event input. Ensure have both /from and /to are provided.");
        }
        if (fromIndex > toIndex) {
            throw new Exception("Invalid event input. Ensure /from comes before /to.");
        }
        if (remaining.indexOf("/from", fromIndex + 1) != -1 || remaining.indexOf("/to", toIndex + 1) != -1) {
            throw new Exception("Invalid event input: only one /from and one /to allowed.");
        }
        String description = remaining.substring(0, fromIndex).trim();
        String from = remaining.substring(fromIndex + 6, toIndex).trim();
        String to = remaining.substring(toIndex + 4).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new Exception("Invalid event input. Description, /from, and /to cannot be empty.");
        }
        return new EventCommand(description, from, to);
    }

    private static DeadlineCommand getDeadlineCommand(String input) throws Exception {
        if (input.length() <= 9) {
            throw new Exception(
                    "The description of a deadline cannot be empty. Example: deadline submit report /by Sunday");
        }
        String remaining = input.substring(9).trim();
        int byIndex = remaining.indexOf("/by ");
        if (byIndex == -1) {
            throw new Exception("A deadline requires a /by date/time. Example: deadline submit report /by Sunday");
        }
        String description = remaining.substring(0, byIndex).trim();
        String by = remaining.substring(byIndex + 4).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new Exception("Invalid deadline input. Ensure both description and /by date are provided.");
        }
        if (by.contains("/by")) {
            throw new Exception("Invalid deadline input: only one /by allowed.");
        }
        return new DeadlineCommand(description, by);
    }
}
