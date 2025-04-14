package com.example.chess.model;

import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public abstract class Piece {
    private final PieceColor color;
    private final Board board;
    private boolean startingPosition;

    public Piece(PieceColor pieceColor, Board board) {
        this.color = pieceColor;
        this.board = board;
        startingPosition = true;
    }

    public abstract PieceType getType();

    public String getName() {
        return getType().getName(); // centralizes naming
    }

    public String getImagePath() {
        return String.format("/pieces/%s_%s.png", color.name().toLowerCase(), getName());
    }

    //public abstract List<Position> getPossiblePositions(Position startingPosition);

    public abstract List<Move> getPossibleMoves(Position startingPosition);

    public Map<Position, Move> getMoveMap(Position startingPosition) {
        List<Move> moveList = this.getPossibleMoves(startingPosition);
        return moveList.stream()
                .collect(Collectors.toMap(Move::getTo, move -> move));
    }

    public void moveOffInitial() {
        startingPosition = false;
    }
}