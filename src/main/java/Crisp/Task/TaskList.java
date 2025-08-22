package Crisp.Task;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 * 
 * This class supports basic task operations such as adding, deleting, 
 * retrieving, and filtering tasks by date. It encapsulates an internal 
 * {@link List} to store tasks and ensures safe access via copies where appropriate.
 */
public class TaskList {

    /** The internal list that stores tasks. */
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the provided list of tasks.
     * 
     * @param tasks the initial list of tasks to populate the TaskList
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list.
     * 
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     * 
     * @param index the index of the task to delete (0-based)
     * @return the task that was removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task delete(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     * 
     * @param index the index of the task to retrieve (0-based)
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a copy of all tasks in the list.
     * 
     * @return a list containing all tasks
     */
    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns a list of tasks that occur on the specified date.
     * 
     * This includes:
     * <ul>
     *   <li>Deadlines with a due date equal to the specified date</li>
     *   <li>Events whose duration includes the specified date</li>
     * </ul>
     * 
     * @param date the date to filter tasks by
     * @return a list of tasks occurring on the given date
     */
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
