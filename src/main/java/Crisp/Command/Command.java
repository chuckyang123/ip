package Crisp.Command;

import Crisp.Task.TaskList;
import Crisp.Util.Storage;
import Crisp.Util.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return false;
    }
}
