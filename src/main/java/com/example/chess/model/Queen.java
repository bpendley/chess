package com.example.chess.model;

import java.util.Collections;
import java.util.List;

public class Queen extends Piece {

    public Queen(PieceColor color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }

    @Override
    public List<Move> getPossibleMoves(Position startingPosition) {
        return PositionUtility.getQueenMoves(this, startingPosition);
    }
}
