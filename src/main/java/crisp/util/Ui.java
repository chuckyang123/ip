package Crisp.Util;

import Crisp.Task.Task;
import java.util.Scanner;

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
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Crisp");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads a line of input from the user and trims leading/trailing whitespace.
     *
     * @return the user input as a trimmed {@code String}
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Displays a separator line in the console.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task  the task that was added
     * @param count the total number of tasks in the list after addition
     */
    public void showAddedTask(Task task, int count) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
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
    public void showDeletedTask(Task task, int remaining) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + remaining + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been marked or unmarked as done.
     *
     * @param task   the task that was marked or unmarked
     * @param isDone {@code true} if the task is marked done, {@code false} if unmarked
     */
    public void showMarkedTask(Task task, boolean isDone) {
        if (isDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task);
    }

    /**
     * Closes the {@link Scanner} used for reading user input.
     * Should be called when the program exits.
     */
    public void close() {
        sc.close();
    }
}
