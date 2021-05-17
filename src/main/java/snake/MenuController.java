package snake;

import Data.Result;
import Data.ResultDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import org.tinylog.Logger;

import javax.swing.*;


public class MenuController {

    @FXML
    private TextField nameField;


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
