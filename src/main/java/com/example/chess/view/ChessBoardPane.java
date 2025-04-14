package com.example.chess.view;

import com.example.chess.model.Board;
import com.example.chess.model.Position;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ChessBoardPane extends GridPane {
    private final Map<Position, SquareView> squareMap = new HashMap<>();

    public ChessBoardPane(Board board, Consumer<Position> onClickHandler) {
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                boolean isLight = (row + col) % 2 == 0;
                Position pos = new Position(row, col);
                SquareView square = new SquareView(pos, isLight);

                board.getPieceAt(pos).ifPresent(square::setPiece);

                square.setOnMouseClicked(e -> onClickHandler.accept(pos));

                squareMap.put(pos, square);
                add(square, col, row);
            }
        }
    }

    public SquareView getSquare(Position pos) {
        return squareMap.get(pos);
    }

    public void update(Board board) {
        for (Map.Entry<Position, SquareView> entry : squareMap.entrySet()) {
            SquareView square = entry.getValue();
            Position pos = entry.getKey();
            square.setPiece(board.getPieceAt(pos).orElse(null));
        }
    }
}
