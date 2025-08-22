import Crisp.Command.Command;
import Crisp.Task.TaskList;
import Crisp.Util.Storage;
import Crisp.Util.Ui;
import Crisp.Util.Parser;
public class Crisp {
    private Storage storage = new Storage("./data/crisp.txt");
    private TaskList tasks;
    private Ui ui = new Ui();

    public Crisp() {
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Failed to load tasks. Starting with empty list.");
            tasks = new TaskList();
        }
    }

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

    public static void main(String[] args) {
        new Crisp().run();
    }
}
