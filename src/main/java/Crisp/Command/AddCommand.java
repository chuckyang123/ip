package Crisp.Command;

import Crisp.Task.Task;
import Crisp.Task.TaskList;
import Crisp.Util.Storage;
import Crisp.Util.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showMessage("Got it. I've added this task:\n   " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }
}
