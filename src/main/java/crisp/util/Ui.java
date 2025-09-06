package crisp.util;

import java.util.Scanner;

import crisp.task.Task;


/**
 * The {@code Ui} class handles all user interactions in the Crisp application.
 * It provides methods to display messages, errors, task updates, and prompts
 * for user input.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Displays the welcome message when the program starts.
     */
    public String showWelcome() {
        return (" Hello! I'm Crisp\n" + " What can I do for you?");
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task  the task that was added
     * @param count the total number of tasks in the list after addition
     */
    public String showAddedTask(Task task, int count) {
        return (" Got it. I've added this task:\n" + "   " + task + "\n"
                + " Now you have " + count + " tasks in the list.");
    }

    /**
     * Displays a generic message to the user.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param error the error message to display
     */
    public void showError(String error) {
        System.out.println(" OOPS!!! " + error);
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task      the task that was removed
     * @param remaining the total number of tasks remaining in the list
     */
    public String showDeletedTask(Task task, int remaining) {
        return (" Noted. I've removed this task:\n" + "   " + task + "\n"
                + " Now you have " + remaining + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been marked or unmarked as done.
     *
     * @param task   the task that was marked or unmarked
     * @param isDone {@code true} if the task is marked done, {@code false} if unmarked
     */
    public String showMarkedTask(Task task, boolean isDone) {
        String notice;
        if (isDone) {
            notice = " Nice! I've marked this task as done:\n";
        } else {
            notice = " OK, I've marked this task as not done yet:\n";
        }
        return (notice + "   " + task);
    }

    /**
     * Closes the {@link Scanner} used for reading user input.
     * Should be called when the program exits.
     */
    public void close() {
        sc.close();
    }
}
