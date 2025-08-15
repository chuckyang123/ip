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
            String input = sc.nextLine().trim();

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
                try {
                    int taskNum = Integer.parseInt(input.substring(5)) - 1;
                    if (taskNum < 0 || taskNum >= taskCount) {
                        throw new Exception("That task number does not exist!");
                    }
                    tasks[taskNum].markDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks[taskNum]);
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }

            } else if (input.startsWith("unmark ")) {
                try {
                    int taskNum = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNum < 0 || taskNum >= taskCount) {
                        throw new Exception("That task number does not exist!");
                    }
                    tasks[taskNum].markUndone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks[taskNum]);
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }

            } else if (input.startsWith("todo")) {
                if (input.length() <= 5) { // only "todo" or "todo "
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                String description = input.substring(5);
                Task newTask = new Todo(description);
                tasks[taskCount++] = newTask;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newTask);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");

            } else if (input.startsWith("deadline")) {
                try {
                    if (input.length() <= 9) {
                        throw new Exception("The description of a deadline cannot be empty.");
                    }
                    String[] parts = input.substring(9).split(" /by ", 2);
                    String description = parts[0].trim();
                    String by = parts.length > 1 ? parts[1].trim() : "";
                    Task newTask = new Deadline(description, by);
                    tasks[taskCount++] = newTask;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newTask);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }

            } else if (input.startsWith("event")) {
                try {
                    if (input.length() <= 6) {
                        throw new Exception("The description of an event cannot be empty.");
                    }
                    String[] parts = input.substring(6).split(" /from | /to ", 3);
                    String description = parts[0].trim();
                    String from = parts.length > 1 ? parts[1].trim() : "";
                    String to = parts.length > 2 ? parts[2].trim() : "";
                    Task newTask = new Event(description, from, to);
                    tasks[taskCount++] = newTask;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newTask);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }

            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-( Can use todo deadline event to add tasks");
                System.out.println("____________________________________________________________");
            }
        }

        sc.close();
    }
}
