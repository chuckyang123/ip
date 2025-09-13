package crisp.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline date.
 * A Deadline task has a description, a due date, and a status indicating
 * whether it is done or not.
 */
public class Deadline extends Task {
    /** Input date format used to parse date strings. */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Output date format used for displaying the deadline. */
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /** The due date of the deadline task. */
    private LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     * @param byStr       the due date in yyyy-MM-dd format
     * @throws DateTimeParseException if the date string is invalid
     */
    public Deadline(String description, String byStr) throws DateTimeParseException {
        super(description, TaskType.DEADLINE, Status.NOT_DONE);
        this.by = LocalDate.parse(byStr, INPUT_FORMAT);
    }

    /**
     * Constructs a Deadline task with the specified description, due date, and status.
     *
     * @param description the description of the task
     * @param byStr       the due date in yyyy-MM-dd format
     * @param status      the status of the task
     * @throws DateTimeParseException if the date string is invalid
     */
    public Deadline(String description, String byStr, Status status) throws DateTimeParseException {
        super(description, TaskType.DEADLINE, status);
        this.by = LocalDate.parse(byStr, INPUT_FORMAT);
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     *
     * @return formatted string for file storage
     */
    @Override
    public String toFileFormat() {
        return type.getIcon() + " | " + (status == Status.DONE ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Returns the description of the task.
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Postpones the deadline by the specified number of days.
     * <p>
     * The {@code by} date is shifted forward by the given number of days.
     *
     * @param days the number of days to postpone; must be positive
     * @throws AssertionError if {@code days} is not positive
     */
    public void postponeByDays(int days) {
        assert days > 0 : "Days to postpone must be positive";
        this.by = this.by.plusDays(days);
    }
    /**
     * Returns the due date of the task.
     *
     * @return due date as a LocalDate
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns a human-readable string representation of the task.
     *
     * @return formatted string for display
     */
    @Override
    public String toString() {
        return "[" + type.getIcon() + "][" + status.getIcon() + "] " + description
                + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
