import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Storage {
    private final Path filePath;

    // relativePath example: "./data/crisp.txt"
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

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    tasks.add(parseTask(line));
                } catch (Exception e) {
                    System.out.println("Skipped corrupted line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to read tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void save(List<Task> tasks) {
        try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

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
