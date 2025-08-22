package Crisp.Task;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }

    public List<Task> getTasksOnDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> {
                    if (task instanceof Deadline) {
                        return ((Deadline) task).getBy().isEqual(date);
                    } else if (task instanceof Event) {
                        LocalDate from = ((Event) task).getFrom();
                        LocalDate to = ((Event) task).getTo();
                        return !date.isBefore(from) && !date.isAfter(to);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
