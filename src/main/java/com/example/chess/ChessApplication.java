package com.example.chess;

import com.example.chess.controller.BoardController;
import com.example.chess.model.Board;
import com.example.chess.model.BoardFactory;
import com.example.chess.view.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ChessApplication extends Application {
    @Override
    public void start(Stage stage) {
        System.out.println("Starting game");

        Board board = new BoardFactory().createInitialBoard();
        BoardView boardView = new BoardView(board);
        BoardController controller = new BoardController(board, boardView);
        boardView.setController(controller);


        StackPane root = new StackPane(boardView.getBoardNode());

        Scene scene = new Scene(root, 640, 640); // square window
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
