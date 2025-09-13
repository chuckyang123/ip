package crisp.javafx;

import crisp.Crisp;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the main GUI window of the Crisp application.
 * <p>
 * Handles user input, displays dialog boxes for both the user and Crisp,
 * and initializes the scrollable dialog container.
 */
public class MainWindow extends AnchorPane {

    /** ScrollPane containing the dialog container, scrolls automatically. */
    @FXML
    private ScrollPane scrollPane;

    /** VBox that holds all dialog boxes in the conversation. */
    @FXML
    private VBox dialogContainer;

    /** TextField where the user types input commands. */
    @FXML
    private TextField userInput;

    /** Button for sending user input (optional, can trigger same as Enter key). */
    @FXML
    private Button sendButton;

    /** The Crisp instance handling command execution and responses. */
    private Crisp crisp;

    /** Image representing the user avatar in dialog boxes. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));

    /** Image representing Crisp's avatar in dialog boxes. */
    private Image crispImage = new Image(this.getClass().getResourceAsStream("/images/Crisp.jpg"));

    /**
     * Initializes the controller.
     * <p>
     * Binds the vertical scroll value of the ScrollPane to the height of the dialog container,
     * so the view automatically scrolls down as new messages are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the {@link Crisp} instance into this controller.
     * <p>
     * Also displays the initial greeting message from Crisp in the dialog container.
     *
     * @param c the Crisp instance to handle commands and responses
     */
    public void setCrisp(Crisp c) {
        this.crisp = c;
        showGreeting();
    }

    /**
     * Displays the initial greeting message from Crisp in a dialog box.
     */
    private void showGreeting() {
        String greeting = crisp.showWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getCrispDialog(greeting, crispImage)
        );
    }

    /**
     * Handles user input when Enter is pressed (or send button is clicked).
     * <p>
     * Creates two dialog boxes:
     * <ul>
     *     <li>One echoing the user's input.</li>
     *     <li>One containing Crisp's reply.</li>
     * </ul>
     * Appends them to the dialog container and clears the input field afterwards.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = crisp.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCrispDialog(response, crispImage)
        );
        userInput.clear();
        if (crisp.isExit()) {
            // Get the stage from any node in the scene, e.g., userInput
            Stage stage = (Stage) userInput.getScene().getWindow();

            // Optional: delay a little so the user sees the goodbye message
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }
}
