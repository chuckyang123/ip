class Task {
    protected String description;
    protected Status status;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.status = Status.NOT_DONE;
    }

    public void markDone() {
        this.status = Status.DONE;
    }

    public void markUndone() {
        this.status = Status.NOT_DONE;
    }

    public String getStatusIcon() {
        return status.getIcon();
    }

    public String getTypeIcon() {
        return type.getIcon();
    }

    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description;
    }
}
