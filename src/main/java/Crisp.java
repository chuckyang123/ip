import java.util.Scanner;

public class Crisp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Crisp");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");

            } else if (input.startsWith("mark ")) {
                int taskNum = Integer.parseInt(input.substring(5)) - 1;
                tasks[taskNum].markDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskNum]);
                System.out.println("____________________________________________________________");

            } else if (input.startsWith("unmark ")) {
                int taskNum = Integer.parseInt(input.substring(7)) - 1;
                tasks[taskNum].markUndone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[taskNum]);
                System.out.println("____________________________________________________________");

            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                Task newTask = new Todo(description);
                tasks[taskCount] = newTask;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newTask);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");

            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ", 2);
                String description = parts[0];
                String by = parts.length > 1 ? parts[1] : "";
                Task newTask = new Deadline(description, by);
                tasks[taskCount] = newTask;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newTask);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");

            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ", 3);
                // parts: [description, from, to]
                String description = parts[0];
                String from = parts.length > 1 ? parts[1] : "";
                String to = parts.length > 2 ? parts[2] : "";
                Task newTask = new Event(description, from, to);
                tasks[taskCount] = newTask;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newTask);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");

            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! I don't understand that command.");
                System.out.println("____________________________________________________________");
            }
        }

        sc.close();
    }
}
