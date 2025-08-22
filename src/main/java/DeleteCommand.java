public class DeleteCommand  extends Command {
    private final int index; // index of task to delete (1-based)

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        Task removedTask = tasks.delete(index);
        ui.showDeletedTask(removedTask, tasks.size());
        storage.save(tasks); // automatically save after deletion
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
