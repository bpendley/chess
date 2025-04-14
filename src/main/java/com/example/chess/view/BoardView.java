package com.example.chess.view;

import com.example.chess.controller.BoardController;
import com.example.chess.model.*;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class BoardView {
    private final GridPane boardGrid = new GridPane();
    private final Map<Position, SquareView> squareMap = new HashMap<>();

    private BoardController controller;

    public BoardView(Board board) {
        buildBoardUI(board);
    }

    public void setController(BoardController controller) {
        this.controller = controller;
    }

    private void buildBoardUI(Board board) {
        boardGrid.getChildren().clear();
        squareMap.clear();

        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                boolean isLight = (row + col) % 2 == 0;
                Position pos = new Position(row, col);
                SquareView square = new SquareView(pos, isLight);
                squareMap.put(pos, square);
                board.getPieceAt(pos).ifPresent(square::setPiece);
                square.setOnMouseClicked(e -> {
                    if (controller != null) {
                        controller.onSquareClicked(pos);
                    }
                });

                boardGrid.add(square, col, row);
            }
        }
    }

    public void update(Board board) {
        buildBoardUI(board); // simple full redraw for now
    }

    public Node getBoardNode() {
        return boardGrid;
    }

    public void highlightSquare(Position pos) {
        SquareView square = squareMap.get(pos);
        if (square != null) {
            square.setHighlighted(true);
        }
    }

    public void clearHighlights() {
        for (SquareView square : squareMap.values()) {
            square.setHighlighted(false);
        }
    }

}


