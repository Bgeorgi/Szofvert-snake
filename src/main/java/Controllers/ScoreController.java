package Controllers;

import Data.Result;
import Data.ResultRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.tinylog.Logger;

import java.io.IOException;

public class ScoreController {

    @FXML
    private TextArea DataScore;

    @FXML
    private TableView<Result> table;

    @FXML
    private TableColumn<Result, String> tablePlayer;

    @FXML
    private TableColumn<Result, Integer> tableScore;


    ResultRepository resultRepository = new ResultRepository();

    /**
     * Initialize scoreboard.
     */
    public void initialize(){
    getScores();

    }

    /**
     * Load in Database to fxml table.
     */
    public void getScores() {

        ObservableList <Result> data = FXCollections.observableArrayList(resultRepository.Query());
        table.setItems(data);
        tablePlayer.setCellValueFactory(new PropertyValueFactory<Result, String>("player"));
        tableScore.setCellValueFactory(new PropertyValueFactory<Result, Integer>("score"));

        }


    /**
     *
     * @param event Back button pressed.
     * @throws IOException
     */
    public void switchToMenu(MouseEvent event) throws IOException {
        Logger.info("Back clicked");
        PageLoader.loadMenu(event );
    }

}
