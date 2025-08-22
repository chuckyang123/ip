package Crisp.Task;

/**
 * Represents the type of a task.
 * 
 * Each task type has a corresponding icon that can be used
 * for display or file storage purposes.
 */
public enum TaskType {
    /** A simple to-do task. */
    TODO("T"),

    /** A task with a specific deadline. */
    DEADLINE("D"),

    /** An event with a start and end date/time. */
    EVENT("E");

    /** The icon associated with the task type. */
    private final String icon;

    /**
     * Constructs a TaskType with the given icon.
     * 
     * @param icon the string representation of the task type
     */
    TaskType(String icon) {
        this.icon = icon;
    }

    /**
     * Returns the icon representing the task type.
     * 
     * @return the task type icon as a string
     */
    public String getIcon() {
        return icon;
    }
}
