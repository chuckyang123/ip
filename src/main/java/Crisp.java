import java.util.ArrayList;
import java.util.Scanner;

public class Crisp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

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
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasks.get(i));
                }
                System.out.println("____________________________________________________________");

            } else if (input.startsWith("mark ")) {
                handleMarkUnmark(tasks, input, true);

            } else if (input.startsWith("unmark ")) {
                handleMarkUnmark(tasks, input, false);

            } else if (input.startsWith("delete ")) {
                handleDelete(tasks, input);

            } else if (input.startsWith("todo")) {
                if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Oops! You need to provide a description for your todo.");
                    System.out.println(" Example: todo read book");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                String description = input.substring(5).trim();
                Task newTask = new Todo(description);
                tasks.add(newTask);
                printAddedTask(newTask, tasks.size());

            } else if (input.startsWith("deadline")) {
                try {
                    if (input.length() <= 9) {
                        throw new Exception("The description of a deadline cannot be empty. Example: deadline submit report /by Sunday");
                    }
                    String remaining = input.substring(9).trim();
                    // Check if /by exists
                    int byIndex = remaining.indexOf("/by ");
                    if (byIndex == -1) {
                        throw new Exception("A deadline requires a /by date/time. Example: deadline submit report /by Sunday");
                    }

                    String description = remaining.substring(0, byIndex).trim();
                    String by = remaining.substring(byIndex + 4).trim();

                    // Validate description and by
                    if (description.isEmpty()) {
                        throw new Exception("The description of a deadline cannot be empty. Example: deadline submit report /by Sunday");
                    }
                    if (by.isEmpty()) {
                        throw new Exception("The /by date/time cannot be empty. Example: deadline submit report /by Sunday");
                    }

                    // Optional: check for multiple /by
                    if (by.contains("/by")) {
                        throw new Exception("Invalid deadline input: only one /by allowed.");
                    }

                    Task newTask = new Deadline(description, by);
                    tasks.add(newTask);
                    printAddedTask(newTask, tasks.size());

                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("event")) {
                try {
                    if (input.length() <= 6) {
                        throw new Exception("The description of an event cannot be empty. Example: event meeting /from 2pm /to 4pm");
                    }
                    String remaining = input.substring(6).trim();
                    // Check for /from and /to
                    int fromIndex = remaining.indexOf("/from ");
                    int toIndex = remaining.indexOf("/to ");

                    if (fromIndex == -1 || toIndex == -1) {
                        throw new Exception("An event requires both /from and /to. Example: event meeting /from 2pm /to 4pm");
                    }

                    if (remaining.indexOf("/from", fromIndex + 1) != -1 || remaining.indexOf("/to", toIndex + 1) != -1) {
                        throw new Exception("Invalid event input: only one /from and one /to allowed.");
                    }

                    String description = remaining.substring(0, fromIndex).trim();
                    String from = remaining.substring(fromIndex + 6, toIndex).trim();
                    String to = remaining.substring(toIndex + 4).trim();

                    if (description.isEmpty()) {
                        throw new Exception("The description of an event cannot be empty. Example: event meeting /from 2pm /to 4pm");
                    }
                    if (from.isEmpty() || to.isEmpty()) {
                        throw new Exception("The /from and /to times cannot be empty. Example: event meeting /from 2pm /to 4pm");
                    }

                    Task newTask = new Event(description, from, to);
                    tasks.add(newTask);
                    printAddedTask(newTask, tasks.size());

                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
        }

        sc.close();
    }

    private static void handleMarkUnmark(ArrayList<Task> tasks, String input, boolean mark) {
        try {
            int num = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
            if (tasks.isEmpty()) {
                throw new Exception("No task in list now");
            }
            if (num < 0 || num >= tasks.size()) {
                throw new Exception("Task number " + (num + 1) + " does not exist. Please choose a number between 1 and " + tasks.size());
            }
            if (mark) tasks.get(num).markDone();
            else tasks.get(num).markUndone();

            System.out.println("____________________________________________________________");
            System.out.println(mark ? " Nice! I've marked this task as done:" : " OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(num));
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" OOPS!!! Please provide a valid task number.");
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            System.out.println("____________________________________________________________");
            System.out.println(" OOPS!!! " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }

    private static void handleDelete(ArrayList<Task> tasks, String input) {
        try {
            int num = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
            if (tasks.isEmpty()) {
                throw new Exception("No task in list now");
            }
            if (num < 0 || num >= tasks.size()) {
                throw new Exception("Task number " + (num + 1) + " does not exist. Please choose a number between 1 and " + tasks.size());
            }
            Task removed = tasks.remove(num);
            System.out.println("____________________________________________________________");
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removed);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" OOPS!!! Please provide a valid task number.");
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            System.out.println("____________________________________________________________");
            System.out.println(" OOPS!!! " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }

    private static void printAddedTask(Task task, int count) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
