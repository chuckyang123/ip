package crisp.javafx;

import java.io.IOException;

import crisp.Crisp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code Main} class is the JavaFX application entry point for the Crisp GUI.
 * <p>
 * It loads the {@link MainWindow} FXML layout, injects a {@link Crisp} instance into
 * the controller, and displays the main application window.
 * <p>
 * Example usage:
 * <pre>
 *     java crisp.javafx.Launcher
 * </pre>
 */
public class Main extends Application {

    private Crisp crisp = new Crisp();

    /**
     * Starts the JavaFX application.
     * <p>
     * Loads the FXML layout, sets the scene on the stage, injects the Crisp instance
     * into the controller, and displays the stage.
     *
     * @param stage the primary stage provided by JavaFX
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            // Inject the Crisp instance into the controller
            fxmlLoader.<MainWindow>getController().setCrisp(crisp);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
