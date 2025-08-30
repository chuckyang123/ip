package crisp.javafx;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an {@link ImageView} to represent the speaker's face
 * and a {@link Label} containing text from the speaker.
 * <p>
 * This class can create two types of dialog boxes:
 * <ul>
 *     <li>User dialog box (image on the right, text on the left)</li>
 *     <li>Crisp dialog box (image on the left, text on the right)</li>
 * </ul>
 * The appearance of the dialog can be customized via CSS and FXML.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the given text and image.
     * <p>
     * Loads the FXML layout, sets the controller, and initializes the dialog text and image.
     *
     * @param text the text message to display
     * @param img  the speaker's avatar image
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box so that the image is displayed on the left and the text on the right.
     * <p>
     * This is used for Crisp's messages to differentiate them from user messages.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label"); // Apply CSS style for replies
    }

    /**
     * Creates a dialog box for the user message.
     * <p>
     * The image will appear on the right side of the box.
     *
     * @param text the text message from the user
     * @param img  the user's avatar image
     * @return a {@code DialogBox} representing the user's message
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for Crisp's message.
     * <p>
     * The image will appear on the left side of the box.
     *
     * @param text the text message from Crisp
     * @param img  Crisp's avatar image
     * @return a {@code DialogBox} representing Crisp's message
     */
    public static DialogBox getCrispDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
