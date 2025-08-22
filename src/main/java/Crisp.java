import Crisp.Command.Command;
import Crisp.Task.TaskList;
import Crisp.Util.Storage;
import Crisp.Util.Ui;
import Crisp.Util.Parser;


/**
 * The {@code Crisp} class represents the main application for the Crisp task manager.
 * It handles initialization of storage, tasks, and user interface, and runs the main
 * event loop to process user commands.
 * 
 * <p>Example usage:
 * <pre>
 *     public static void main(String[] args) {
 *         new Crisp().run();
 *     }
 * </pre>
 */

public class Crisp {
    private Storage storage = new Storage("./data/crisp.txt");
    private TaskList tasks;
    private Ui ui = new Ui();

    /**
     * Constructs a new {@code Crisp} application.
     * Attempts to load tasks from storage; if loading fails, initializes an empty task list.
     */
    public Crisp() {
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Failed to load tasks. Starting with empty list.");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main event loop of the application.
     * <p>
     * Displays the welcome message, reads user input, parses commands, executes them,
     * and displays appropriate messages. The loop continues until the user issues
     * an exit command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * The entry point of the Crisp application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Crisp().run();
    }
}
