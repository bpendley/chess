package com.example.chess.controller;

import com.example.chess.model.Board;
import com.example.chess.model.Move;
import com.example.chess.model.Piece;
import com.example.chess.model.Position;
import com.example.chess.view.BoardView;

import java.util.Optional;

public class BoardController {
    private final Board board;
    private final BoardView boardView;
    private Optional<Position> selectedPosition = Optional.empty();

    public BoardController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;
    }

    public void onSquareClicked(Position clickedPosition) {
        Optional<Piece> clickedPiece = board.getPieceAt(clickedPosition);

        if (selectedPosition.isEmpty()) {
            if (clickedPiece.isPresent()) {
                selectedPosition = Optional.of(clickedPosition);
                boardView.highlightSquare(clickedPosition); // optional
                board.getPieceAt(clickedPosition).ifPresent(piece -> {
                    piece.getPossibleMoves(clickedPosition).stream().map(Move::getTo).forEach(boardView::highlightSquare);
                });

            }
        } else {
            Position from = selectedPosition.get();
            board.handleMove(from, clickedPosition);
            selectedPosition = Optional.empty();
            //boardView.clearHighlights(); // optional
            boardView.update(board); // full refresh
        }
    }
}
