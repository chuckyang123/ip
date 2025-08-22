package Crisp.Task;

/**
 * Represents a simple "to-do" task without a specific deadline or event duration.
 * A Todo can be marked as done or not done.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the given description.
     * The task status is set to NOT_DONE by default.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Constructs a Todo with the given description and status.
     *
     * @param description the description of the task
     * @param status the initial status of the task (DONE or NOT_DONE)
     */
    public Todo(String description, Status status) {
        super(description, TaskType.TODO, status);
    }

    /**
     * Returns the string representation of this task for file storage.
     * Format: "T | 0 | description" or "T | 1 | description" depending on status.
     *
     * @return the formatted string for saving to a file
     */
    @Override
    public String toFileFormat() {
        return type.getIcon() + " | " + (status == Status.DONE ? "1" : "0") + " | " + description;
    }
}
