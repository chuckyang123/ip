package Crisp.Command;
import Crisp.Task.*;
import Crisp.Util.*;
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return false;
    }
}
