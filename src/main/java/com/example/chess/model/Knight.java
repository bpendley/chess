package com.example.chess.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Knight extends Piece {
    public Knight(PieceColor color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    @Override
    public List<Move> getPossibleMoves(Position startingPosition) {
        return PositionUtility.getKnightMoves(this, startingPosition);
    }
}
