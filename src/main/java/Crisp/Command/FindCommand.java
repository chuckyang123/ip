package Crisp.Command;

import Crisp.Task.Task;
import Crisp.Task.TaskList;
import Crisp.Util.Storage;
import Crisp.Util.Ui;

import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> allTasks = tasks.getAll();
        List<Task> matchingTasks = allTasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No tasks match your search keyword: " + keyword);
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
