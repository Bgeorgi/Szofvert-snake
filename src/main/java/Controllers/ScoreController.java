package Controllers;

import Data.Result;
import Data.ResultRepository;
import javafx.beans.Observable;
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
import java.sql.*;

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

    public void initialize(){
    getScores();

    }

    public void getScores() {

        ObservableList <Result> data = FXCollections.observableArrayList(resultRepository.Query());
        table.setItems(data);
        tablePlayer.setCellValueFactory(new PropertyValueFactory<Result, String>("player"));
        tableScore.setCellValueFactory(new PropertyValueFactory<Result, Integer>("score"));

        }


    public void switchToMenu(MouseEvent event) throws IOException {
        PageLoader.loadMenu(event );
    }

}
