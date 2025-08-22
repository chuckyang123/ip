package Crisp.Command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Crisp.Task.*;
import Crisp.Util.*;
public class ShowCommand extends Command {
    private final String dateStr;

    public ShowCommand(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            LocalDate queryDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println(" Tasks occurring on " + queryDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");

            boolean found = false;
            for (Task task : tasks.getAll()) {
                if (task instanceof Deadline dl && dl.getBy().isEqual(queryDate)) {
                    System.out.println(" " + dl);
                    found = true;
                } else if (task instanceof Event ev && !queryDate.isBefore(ev.getFrom()) && !queryDate.isAfter(ev.getTo())) {
                    System.out.println(" " + ev);
                    found = true;
                }
            }

            if (!found) {
                System.out.println(" No tasks found on this date.");
            }

        } catch (DateTimeParseException e) {
            ui.showError("Invalid date format. Use yyyy-MM-dd. Example: 2019-12-02");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
