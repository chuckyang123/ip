public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    public Deadline(String description, String by, Status status) {
        super(description, TaskType.DEADLINE, status);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return type.getIcon() + " | " + (status == Status.DONE ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
