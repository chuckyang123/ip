package crisp.javafx;

import javafx.application.Application;

/**
 * The {@code Launcher} class serves as the entry point for the Crisp JavaFX application.
 * <p>
 * This class exists as a workaround for certain classpath issues when launching JavaFX applications
 * from some IDEs or build tools.
 * <p>
 * It simply delegates to {@link Main}, which is the actual JavaFX application class.
 *
 * <pre>
 *     java crisp.javafx.Launcher
 * </pre>
 */
public class Launcher {

    /**
     * The entry point of the Crisp JavaFX application.
     * <p>
     * This method launches the JavaFX runtime and starts the application.
     *
     * @param args the command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
