package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

import org.tinylog.Logger;


public class PageLoader {


    public static void loadGame(MouseEvent mouseEvent, String name) throws IOException {
        Logger.info("Snake is loading");
        FXMLLoader fxmlLoader= new FXMLLoader(PageLoader.class.getResource("/snake/snake.fxml"));
        Parent root = fxmlLoader.load();
        SnakeController controller = fxmlLoader.<SnakeController>getController();
        controller.setPlayerName(name);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    public static void loadMenu(MouseEvent mouseEvent) throws  IOException{
        Logger.info("Menu is loading");
        Parent root= FXMLLoader.load(PageLoader.class.getResource("/snake/menu.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public static void loadScore(MouseEvent mouseEvent) throws IOException{
        Logger.info("ScoreBoard is loading");
        Parent root = FXMLLoader.load(Objects.requireNonNull(PageLoader.class.getResource("/snake/scoreboard.fxml")));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

}
