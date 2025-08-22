package Crisp.Task;

/**
 * Represents a generic task with a description, type, and status.
 * 
 * This is an abstract base class for specific types of tasks such as
 * {@link Deadline}, {@link Event}, and {@link Todo}. Each task has:
 * <ul>
 *   <li>a textual description</li>
 *   <li>a completion status ({@link Status})</li>
 *   <li>a task type ({@link TaskType})</li>
 * </ul>
 */
public abstract class Task {

    /** The description of the task. */
    protected String description;

    /** The current completion status of the task. */
    protected Status status;

    /** The type of the task (TODO, DEADLINE, EVENT). */
    protected TaskType type;

    /**
     * Constructs a new Task with the specified description and type.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     * @param type the type of the task
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.status = Status.NOT_DONE;
    }

    /**
     * Constructs a new Task with the specified description, type, and status.
     *
     * @param description the description of the task
     * @param type the type of the task
     * @param status the initial completion status of the task
     */
    public Task(String description, TaskType type, Status status) {
        this.description = description;
        this.type = type;
        this.status = status;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        status = Status.DONE;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        status = Status.NOT_DONE;
    }

    /**
     * Returns the current completion status of the task.
     *
     * @return the task's status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Returns the type of the task.
     *
     * @return the task's type
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Returns a string representation of the task suitable for saving to file.
     * Subclasses must implement this method to provide their specific file format.
     *
     * @return the task as a formatted string for file storage
     */
    public abstract String toFileFormat();

    /**
     * Returns a human-readable string representation of the task,
     * including its type icon, status icon, and description.
     *
     * @return a string representing the task
     */
    @Override
    public String toString() {
        return "[" + type.getIcon() + "][" + status.getIcon() + "] " + description;
    }
}
