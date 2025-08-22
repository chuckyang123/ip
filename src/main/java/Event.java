import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Event(String description, LocalDate from, LocalDate to, Status status) {
        super(description, TaskType.EVENT, status);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String fromStr, String toStr) throws DateTimeParseException {
        super(description, TaskType.EVENT, Status.NOT_DONE);
        this.from = LocalDate.parse(fromStr, INPUT_FORMAT);
        this.to = LocalDate.parse(toStr, INPUT_FORMAT);
    }

    public Event(String description, String fromStr, String toStr, Status status) throws DateTimeParseException {
        super(description, TaskType.EVENT, status);
        this.from = LocalDate.parse(fromStr, INPUT_FORMAT);
        this.to = LocalDate.parse(toStr, INPUT_FORMAT);
    }
    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
    @Override
    public String toString() {
        return "[" + type.getIcon() + "][" + status.getIcon() + "] " + description +
                " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    public String toFileFormat() {
        return type.getIcon() + " | " + (status == Status.DONE ? "1" : "0") + " | " + description +
                " | " + from.format(INPUT_FORMAT) + " | " + to.format(INPUT_FORMAT);
    }
}
