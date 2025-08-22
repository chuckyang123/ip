package Crisp.Command;

import Crisp.Task.*;
import Crisp.Util.*;

/**
 * Represents a generic command in the Crisp application.
 * All specific commands (e.g., AddCommand, DeleteCommand) extend this class.
 * Each command can be executed on a TaskList with a Ui and Storage.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui for printing messages
     * @param storage the Storage for persisting changes
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether this command exits the application.
     * By default, commands do not exit, so this returns false.
     *
     * @return true if this command exits the application, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
