public abstract class Task {
    protected String description;
    protected Status status;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.status = Status.NOT_DONE;
    }

    public Task(String description, TaskType type, Status status) {
        this.description = description;
        this.type = type;
        this.status = status;
    }

    public void markDone() { status = Status.DONE; }
    public void markUndone() { status = Status.NOT_DONE; }
    public Status getStatus() { return status; }
    public TaskType getType() { return type; }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return "[" + type.getIcon() + "][" + status.getIcon() + "] " + description;
    }
}
