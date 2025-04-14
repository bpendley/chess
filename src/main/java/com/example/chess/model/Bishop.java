package com.example.chess.model;

import java.util.Collections;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }


    @Override
    public List<Move> getPossibleMoves(Position startingPosition) {
        return PositionUtility.getBishopsMoves(this, startingPosition);

    }


}
