package Controllers;

import Data.DataConnection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import org.tinylog.Logger;


public class MenuController {

    @FXML
    private TextField nameField;

    /**
     * Initialize Database
     */

    public void initialize(){
        DataConnection.openEmf();
        Logger.info("Database is running");
    }

    /**
     * Start game button.
     * Receive player name, if empty auto fill with name "Anonymous"
     */
    public void startGame(MouseEvent event) throws IOException {
        try {
            Logger.info("Start clicked");
            String name1 = nameField.getText();
            if (!name1.isEmpty()) {
                PageLoader.loadGame(event, name1);

            } else {
                PageLoader.loadGame(event, "Anonymous");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Score button.
     */
    public void switchToScore(MouseEvent event) throws IOException {
        Logger.info("Score clicked");
        PageLoader.loadScore(event);
    }
}
