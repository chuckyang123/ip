package Crisp.Util;

import Crisp.Command.Command;
import Crisp.Command.DeadlineCommand;
import Crisp.Command.DeleteCommand;
import Crisp.Command.EventCommand;
import Crisp.Command.ExitCommand;
import Crisp.Command.FindCommand;
import Crisp.Command.ListCommand;
import Crisp.Command.MarkCommand;
import Crisp.Command.ShowCommand;
import Crisp.Command.TodoCommand;
import Crisp.Command.UnmarkCommand;


public class Parser {
    public static Command parse(String input) throws Exception {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
           return new ListCommand();
        } else if (input.startsWith("mark ")) {
            int num = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
            return new MarkCommand(num);
        } else if (input.startsWith("find ")) {
                String keyword = input.substring(5).trim();
                if (keyword.isEmpty()) {
                    throw new Exception("You must provide a keyword to search for. Example: find book");
                }
                return new FindCommand(keyword);
        } else if (input.startsWith("unmark ")) {
            int num = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
            return new UnmarkCommand(num);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(Integer.parseInt(input.replaceAll("\\D+", "")) - 1);
        } else if (input.startsWith("show ")) {
            String dateStr = input.substring(5).trim();
            return new ShowCommand(dateStr);
        } else if (input.startsWith("todo")) {
            if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
                throw new Exception(" Oops! You need to provide a description for your todo. Example: todo read book");
            }
            String description = input.substring(5).trim();
            return new TodoCommand(description);
        } else if (input.startsWith("deadline")) {
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
                return new DeadlineCommand(description,by);
        } else if (input.startsWith("event")) {
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

            if (fromIndex > toIndex ) {
                throw new Exception("An event requires first /from then /to. Example: event meeting /from 2pm /to 4pm");
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
            return new EventCommand(description, from, to);
        } else {
            throw new Exception(" I'm sorry, but I don't know what that means :-(");
        }
    }
}
