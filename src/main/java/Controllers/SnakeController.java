package Controllers;


import Data.Result;
import Data.ResultRepository;
import snake.Direction;
import snake.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.tinylog.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class SnakeController  {

    @FXML
    private Label scoreLabel;

    @FXML
    private Label highScoreLabel;

    @FXML
    private Pane grid;

    @FXML
    private Label gameOver;

    @FXML
    private Label PlayerName;



    private GridPane gridPane;
    private GameState gameState;
    private Direction direction;
    private Timeline timeline;


    private static int statichighscore=0;


    /**
     *
     * @param name Load player name into fxml layer.
     */
    public void setPlayerName(String name) {
         this.PlayerName.setText(name);

    }

    ResultRepository resultRepository = new ResultRepository();


    /**
     * Initialize Snake-game
     */
    @FXML
    public void initialize() {
        generateGridPane();
        fromData();
    }


    /**
     * Start button pressed , start logic.
     * @throws InterruptedException
     */
    public void startGame() throws InterruptedException {
        fromData();
        clearSnakeSpeed();
        this.gameState = new GameState();
        gameOver.setVisible(false);
        renderSnake();
        SnakeMoveManager();
        direction = new Direction("D", "A");


        Logger.info("Start button is clicked");
    }


    /**
     *  Move snake to direction.
     *  Set KeyFrame
     */
    private void SnakeMoveManager() {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(130), new EventHandler<ActionEvent>() {



            @Override
            public void handle(ActionEvent event) {
                if (!gameState.isOver()) {
                    gameState.moveSnake(direction.getDirection());
                    renderSnake();
                }
            }
        }));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }


    private void clearSnakeSpeed() {
        if (this.timeline != null) {
            this.timeline.stop();
            this.timeline.getKeyFrames().clear();
            this.timeline = null;
        }
    }

    /**
     * Generating grid
     */
    private void generateGridPane() {
        gridPane = new GridPane();
        gridPane.setPrefWidth(700);
        gridPane.setPrefHeight(500);
        gridPane.setGridLinesVisible(true);
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THICK)));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 26; j++) {
                Label label = new Label();
                label.setId(createID(i, j));
                label.setPrefHeight(500 / 20.0);
                label.setPrefWidth(700 / 26.0);
                gridPane.addRow(i, label);
            }
        }
        grid.getChildren().add(gridPane);
    }

    public String createID(int col, int row) {
        return String.valueOf(row) + "_" + String.valueOf(col);
    }

    /**
     * Renders in snake
     */
    private void renderSnake() {
        setScoreLabel();
        Node label;
        String id;
        int[][] state = gameState.getGameState();
        clearCells();
        try {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 26; j++) {
                    if (state[i][j] != 0) {
                        id = createID(i, j);
                        String finalId = id;
                        label = (Label) gridPane.getChildren().stream()
                                .filter(x -> x.getId() != null)
                                .filter(x -> x.getId().equals(finalId))
                                .findFirst()
                                .get();
                        if (state[i][j] != -1) label.setStyle("-fx-background-color: black;");
                        else label.setStyle("-fx-background-color: red;");
                    }
                }
            }
        } catch (Exception e) {
            gameOver.setVisible(true);
            toData();
        }

     }

    /**
     * Sets the opposite of key event.
     * @param keyEvent  gets key event.
     */
    public void snakeMove(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        String opposite = null;
        String dir = code.toString();
        if (direction.getOpposite() != dir) {
            switch (dir) {
                case "W":
                    opposite = "S";
                    break;
                case "A":
                    opposite = "D";
                    break;
                case "S":
                    opposite = "W";
                    break;
                case "D":
                    opposite = "A";
                    break;
            }
            direction = new Direction(dir, opposite);
        }
    }

    public void clearCells() {
        for (Node label : gridPane.getChildren()) {
            label.setStyle("");
        }
    }

    /**
     * Updating Score
     */
    public void setScoreLabel() {
        scoreLabel.setText(String.valueOf(gameState.getScore()));
    }

    /**
     * Saves Data to Database.
     */
    public void toData(){

        Result newResult = new Result();

        newResult.setPlayer(PlayerName.getText());
        newResult.setScore(gameState.getScore());
        resultRepository.AddNewResult(newResult);


    }

    /**
     * Set high score from Database
     */
    public void fromData(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/ylm1Jk7dKv", "ylm1Jk7dKv", "XL6vysANrr");
            String sql = "select max(score) from Result";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int max = 0;
            if (resultSet.next()){
                 max = resultSet.getInt(1);
            }

            highScoreLabel.setText(String.valueOf(max));
            Logger.trace(max );
        } catch (Exception e) {
            Logger.error("fromData error");
        }
    }

    /**
     * Back button.
     */
    public void switchToMenu(MouseEvent event) throws Exception {
        PageLoader.loadMenu(event);
        Logger.info("Back button is clicked.");
    }


}
