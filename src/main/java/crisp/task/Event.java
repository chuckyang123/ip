package crisp.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs over a specific date range.
 * An Event task has a description, a start date, an end date, and a status
 * indicating whether it is done or not.
 */
public class Event extends Task {
    /** Input date format used to parse date strings. */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Output date format used for displaying the event dates. */
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /** The start date of the event. */
    private LocalDate from;

    /** The end date of the event. */
    private LocalDate to;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     * @param fromStr     the start date in yyyy-MM-dd format
     * @param toStr       the end date in yyyy-MM-dd format
     * @throws DateTimeParseException if either date string is invalid
     */
    public Event(String description, String fromStr, String toStr) throws DateTimeParseException {
        super(description, TaskType.EVENT, Status.NOT_DONE);
        this.from = LocalDate.parse(fromStr, INPUT_FORMAT);
        this.to = LocalDate.parse(toStr, INPUT_FORMAT);
    }

    /**
     * Constructs an Event task with the specified description, start date, end date, and status.
     *
     * @param description the description of the task
     * @param fromStr     the start date in yyyy-MM-dd format
     * @param toStr       the end date in yyyy-MM-dd format
     * @param status      the status of the task
     * @throws DateTimeParseException if either date string is invalid
     */
    public Event(String description, String fromStr, String toStr, Status status) throws DateTimeParseException {
        super(description, TaskType.EVENT, status);
        this.from = LocalDate.parse(fromStr, INPUT_FORMAT);
        this.to = LocalDate.parse(toStr, INPUT_FORMAT);
    }

    /**
     * Returns the start date of the event.
     *
     * @return start date as a LocalDate
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Returns the end date of the event.
     *
     * @return end date as a LocalDate
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Returns the description of the event.
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a human-readable string representation of the event task.
     *
     * @return formatted string for display
     */
    @Override
    public String toString() {
        return "[" + type.getIcon() + "][" + status.getIcon() + "] " + description
               + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     *
     * @return formatted string for file storage
     */
    public String toFileFormat() {
        return type.getIcon() + " | " + (status == Status.DONE ? "1" : "0") + " | " + description
               + " | " + from.format(INPUT_FORMAT) + " | " + to.format(INPUT_FORMAT);
    }
    /**
     * Postpones the event by the specified number of days.
     * <p>
     * Both the start ({@code from}) and end ({@code to}) dates
     * of the event are shifted forward by the given number of days.
     *
     * @param days the number of days to postpone the event; must be positive
     * @throws AssertionError if {@code days} is not positive
     */
    public void postponeByDays(int days) {
        assert days > 0 : "Days to postpone must be positive";
        this.from = this.from.plusDays(days);
        this.to = this.to.plusDays(days);
    }
}
