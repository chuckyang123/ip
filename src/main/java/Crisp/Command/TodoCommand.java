package Crisp.Command;

import Crisp.Task.Task;
import Crisp.Task.TaskList;
import Crisp.Task.Todo;
import Crisp.Util.Storage;
import Crisp.Util.Ui;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage ) {
        Task newTask = new Todo(description);
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks.size());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

