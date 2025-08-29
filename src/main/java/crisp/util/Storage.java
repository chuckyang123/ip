package crisp.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import crisp.task.Deadline;
import crisp.task.Event;
import crisp.task.Status;
import crisp.task.Task;
import crisp.task.TaskList;
import crisp.task.Todo;

/**
 * The {@code Storage} class handles reading from and writing to the file
 * system for persisting the tasks of the Crisp application.
 * <p>
 * It supports loading tasks from a file, saving tasks to a file,
 * and parsing task data from the file format.
 * The storage file is automatically created if it does not exist.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a {@code Storage} object with a specified relative file path.
     * If the file or its parent directories do not exist, they are created.
     *
     * @param relativePath the relative path to the storage file (e.g., "./data/crisp.txt")
     */
    public Storage(String relativePath) {
        this.filePath = Paths.get(relativePath);
        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Failed to initialize storage: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     * Each line in the file represents a task in a specific format.
     * Corrupted lines are skipped with a warning message.
     *
     * @return a list of tasks loaded from the file
     */
    public List<Task> load() {
        List<Task> loadedTasks = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    loadedTasks.add(parseTask(line));
                } catch (Exception e) {
                    System.out.println("Skipped corrupted line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to read tasks: " + e.getMessage());
        }
        return loadedTasks;
    }

    /**
     * Saves all tasks from the given {@link TaskList} to the storage file.
     * Each task is converted to its file format before writing.
     *
     * @param taskList the list of tasks to save
     */
    public void save(TaskList taskList) {
        try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
            for (Task task : taskList.getAll()) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the storage file and converts it into a {@link Task}.
     * Supports {@link Todo}, {@link Deadline}, and {@link Event} tasks.
     *
     * @param line the line from the file
     * @return the corresponding {@link Task} object
     * @throws IllegalArgumentException if the task type is unknown
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Status status = parts[1].equals("1") ? Status.DONE : Status.NOT_DONE;
        switch (parts[0]) {
        case "T": return new Todo(parts[2], status);
        case "D": return new Deadline(parts[2], parts[3], status);
        case "E": return new Event(parts[2], parts[3], parts[4], status);
        default: throw new IllegalArgumentException("Unknown task type: " + parts[0]);
        }
    }
}
