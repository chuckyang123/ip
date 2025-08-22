package Crisp.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadline extends Task {
    private LocalDate by;  // store the deadline as a LocalDate
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, LocalDate by, Status status) {
        super(description, TaskType.DEADLINE, status);
        this.by = by;
    }

    public Deadline(String description, String byStr) throws DateTimeParseException {
        super(description, TaskType.DEADLINE, Status.NOT_DONE);
        this.by = LocalDate.parse(byStr, INPUT_FORMAT);
    }

    public Deadline(String description, String byStr, Status status) throws DateTimeParseException {
        super(description, TaskType.DEADLINE, status);
        this.by = LocalDate.parse(byStr, INPUT_FORMAT);
    }

    @Override
    public String toFileFormat() {
        return type.getIcon() + " | " + (status == Status.DONE ? "1" : "0") + " | " + description + " | " + by;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + type.getIcon() + "][" + status.getIcon() + "] " + description +
                " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
