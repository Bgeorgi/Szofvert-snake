package snake;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ScoreController {

    @FXML
    private TextArea DataScore;




    public void switchToMenu(MouseEvent event) throws IOException {
        PageLoader.loadMenu(event );
    }

}
