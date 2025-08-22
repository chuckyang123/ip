package Crisp.util;

import Crisp.Task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Crisp");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showAddedTask(Task task, int count) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.out.println(" OOPS!!! " + error);
    }

    public void showDeletedTask(Task task, int remaining) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + remaining + " tasks in the list.");
    }

    public void showMarkedTask(Task task, boolean isDone) {
        if (isDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task);
    }

    public void close() {
        sc.close();
    }
}
