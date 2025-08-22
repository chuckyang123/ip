public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    public Todo(String description, Status status) {
        super(description, TaskType.TODO, status);
    }

    @Override
    public String toFileFormat() {
        return type.getIcon() + " | " + (status == Status.DONE ? "1" : "0") + " | " + description;
    }
}
