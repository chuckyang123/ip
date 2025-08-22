package Crisp.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setup() {
        taskList = new TaskList();
    }

    @Test
    public void testAddAndSize() {
        assertEquals(0, taskList.size(), "TaskList should be empty initially");

        Deadline deadline = new Deadline("Submit report", "2025-08-25");
        taskList.add(deadline);

        assertEquals(1, taskList.size(), "TaskList size should be 1 after adding a task");
        assertSame(deadline, taskList.get(0), "Added task should be retrievable by get()");
    }

    @Test
    public void testDelete() {
        Deadline deadline = new Deadline("Submit report", "2025-08-25");
        taskList.add(deadline);

        Task deleted = taskList.delete(0);
        assertSame(deadline, deleted, "Deleted task should be the same as added task");
        assertEquals(0, taskList.size(), "TaskList should be empty after deletion");
    }

    @Test
    public void testGetAllReturnsCopy() {
        Deadline deadline = new Deadline("Submit report", "2025-08-25");
        taskList.add(deadline);

        List<Task> allTasks = taskList.getAll();
        assertEquals(1, allTasks.size(), "getAll() should return a list with 1 task");

        // Modify returned list should not affect original taskList
        allTasks.clear();
        assertEquals(1, taskList.size(), "Original TaskList should not be affected by changes to getAll()");
    }

    @Test
    public void testGetTasksOnDateDeadline() {
        Deadline deadline1 = new Deadline("Submit report", "2025-08-25");
        Deadline deadline2 = new Deadline("Finish homework", "2025-08-26");
        taskList.add(deadline1);
        taskList.add(deadline2);

        List<Task> tasksOn25 = taskList.getTasksOnDate(LocalDate.of(2025, 8, 25));
        assertEquals(1, tasksOn25.size(), "Should return 1 task on 2025-08-25");
        assertSame(deadline1, tasksOn25.get(0));
    }

    @Test
    public void testGetTasksOnDateEvent() {
        Event event = new Event("Conference","2025-08-24", "2025-08-26");
        taskList.add(event);

        List<Task> tasksOn25 = taskList.getTasksOnDate(LocalDate.of(2025, 8, 25));
        assertEquals(1, tasksOn25.size(), "Should include event spanning the date");
        assertSame(event, tasksOn25.get(0));

        List<Task> tasksOn27 = taskList.getTasksOnDate(LocalDate.of(2025, 8, 27));
        assertEquals(0, tasksOn27.size(), "No tasks on this date");
    }

    @Test
    public void testDeleteInvalidIndexThrows() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.delete(0));
    }

    @Test
    public void testGetInvalidIndexThrows() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(0));
    }
}
