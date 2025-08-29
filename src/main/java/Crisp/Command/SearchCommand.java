package Crisp.Command;

import Crisp.Task.Task;
import Crisp.Task.TaskList;
import Crisp.Util.Storage;
import Crisp.Util.Ui;

public class SearchCommand extends Command {
    private final String[] keywords;

    // Accept multiple keywords
    public SearchCommand(String... keywords) {
        this.keywords = keywords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(" Here are the matching tasks in your list:");

        boolean found = false;
        int count = 1;

        for (Task task : tasks.getAll()) {
            for (String keyword : keywords) {
                if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println(" " + count + ". " + task);
                    found = true;
                    count++;
                    break; // Avoid printing the same task multiple times if multiple keywords match
                }
            }
        }

        if (!found) {
            System.out.println(" No tasks match your search.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

