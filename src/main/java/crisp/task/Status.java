package crisp.task;

/**
 * Represents the completion status of a task.
 * A task can either be NOT_DONE or DONE. Each status has an associated
 * icon for display purposes: a space " " for NOT_DONE and "X" for DONE.
 */
public enum Status {

    /** Task has not been completed yet. */
    NOT_DONE(" "),

    /** Task has been completed. */
    DONE("X");

    /** The icon representing this status in task display. */
    private final String icon;

    /**
     * Constructs a Status enum with the specified icon.
     *
     * @param icon the string symbol representing this status
     */
    Status(String icon) {
        this.icon = icon;
    }

    /**
     * Returns the icon associated with this status.
     *
     * @return the string icon for display
     */
    public String getIcon() {
        return icon;
    }
}
