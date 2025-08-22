package Crisp.Command;
import Crisp.Task.*;
import Crisp.Util.*;
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        task.markDone();
        ui.showMarkedTask(task, true);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

