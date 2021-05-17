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

    public void initialize(){
        DataConnection.openEmf();
    }


    public void startGame(MouseEvent event) throws IOException {
        try {
            //    log.info("Play button is clicked.");
            Logger.info("Start clicked");
            String name1 = nameField.getText();
            if (!name1.isEmpty()) {
                PageLoader.loadGame(event, name1);

            } else {
                PageLoader.loadGame(event, "Anonymus");
                // log.info("Username is set to {}, loading {} scene.","Anonymus");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void switchToScore(MouseEvent event) throws IOException {
        PageLoader.loadScore(event);
    }
}
