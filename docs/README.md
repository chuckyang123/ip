# Crisp User Guide

Crisp is a **task management chatbot** that helps you organize your todos, deadlines, and events through a conversational interface. You can add, delete, mark/unmark, snooze, and search for tasks quickly using simple text commands. The application also features a user-friendly, asymmetric chat GUI with clear error highlighting.

---

## Table of Contents

1. [Deadline](#adding-deadlines)
2. [Todo](#adding-todos)
3. [Event](#adding-events)
4. [List](#listing-tasks)
5. [Mark and Unmark](#marking-and-unmarking-tasks)
6. [Delete](#deleting-tasks)
7. [Snooze](#snoozing-tasks)
8. [Search](#searching-for-tasks)
9. [Show](#showing-tasks)
10. [Exit](#exiting-the-app)
11. [GUI Overview](#gui-overview)


---

## Adding Deadlines

Use the `deadline` command to add a task that must be completed by a specific date or time.

**Syntax:**

deadline <description> /by <date>

**Example:**

deadline submit report /by 2025-09-20

**Expected Outcome:**

Got it. I've added this task:
[D][ ] submit report (by: 2025-09-20)
Now you have 3 tasks in the list.


---

## Adding Todos

Use the `todo` command to add a task without a deadline.

**Syntax:**

todo <description>


**Example:**

todo read book

**Expected Outcome:**

Got it. I've added this task:
[T][ ] read book
Now you have 4 tasks in the list.


---

## Adding Events

Use the `event` command to add a task that occurs within a specific time range.

**Syntax:**

event <description> /from <start> /to <end>


**Example:**

event team meeting /from 2pm /to 4pm

**Expected Outcome:**

Got it. I've added this task:
[E][ ] team meeting (from: 2pm to: 4pm)
Now you have 5 tasks in the list.

---

## Listing Tasks

Use the `list` command to display all tasks in your list.

**Syntax:**

list

**Expected Outcome:**

Here are your tasks:

[T][ ] read book

[D][ ] submit report (by: 2025-09-20)

[E][ ] team meeting (from: 2pm to: 4pm)

---

## Marking and Unmarking Tasks

**Mark a task as done:**

mark <task_index>


**Unmark a task:**

unmark <task_index>


**Example:**

mark 1

**Expected Outcome:**

Nice! I've marked this task as done:
[T][X] read book

---

## Deleting Tasks

Use the `delete` command to remove a task from your list.

delete <task_index>

**Example:**

delete 2

**Expected Outcome:**

Noted. I've removed this task:
[D][ ] submit report (by: 2025-09-20)
Now you have 4 tasks in the list.

---

## Snoozing Tasks

Postpone a task’s deadline or event by a number of days.

snooze <task_index> <days>

**Example:**

snooze 2 3

**Expected Outcome:**

Got it. I've postponed the task by 3 days.

---

## Searching for Tasks

Find tasks by keywords using the `search` command.

search <keyword1> <keyword2> ...

**Example:**

search book report

**Expected Outcome:**

Here are the matching tasks:

[T][ ] read book

[D][ ] submit report (by: 2025-09-20)

---
## Showing tasks
Use the `show` command to display all tasks scheduled for a particular date. This is useful for quickly checking what you have planned on a given day.

**Syntax:**

show <yyyy-MM-dd>

**Example:**

show 2025-09-20

**Expected Outcome:**

Tasks scheduled on 2025-09-20:

[D][ ] submit report (by: 2025-09-20)

**Notes:**

- Only tasks whose **deadline (`/by`)** or **event date (`/from` or `/to`)** matches the specified date are shown.
- If no tasks exist on that date, the app displays:

No tasks scheduled on 2025-09-20.

---

## Exiting the App

Use the `bye` command to close Crisp safely.

---

## Exiting the App

Use the `bye` command to close Crisp safely.

bye

**Expected Outcome:**

Bye! Hope to see you again soon!

---

## GUI Overview

- **Asymmetric chat bubbles**
    - User messages appear on one side (greenish bubble).
    - Bot messages appear on the opposite side (white bubble).
- **Error highlighting**
    - Invalid commands or input are displayed in **red error bubbles**.
- **Profile pictures**
    - Optional circular avatars with drop shadows for clarity.
- **Responsive design**
    - Chat window and content resize gracefully.
- **Visual style**
    - Subtle backgrounds, rounded corners, readable fonts, and padding improve user experience.

**Example of error message bubble:**

Oops! You need to provide a description for your todo. Example: todo read book

---

## Notes

- All task indices start at **1** (not 0).
- Commands are **case-sensitive**.
- For date input, follow the format: `yyyy-MM-dd`.

---