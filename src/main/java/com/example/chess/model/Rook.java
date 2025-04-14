package com.example.chess.model;

import java.util.Collections;
import java.util.List;

public class Rook extends Piece {

    public Rook(PieceColor color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }

    @Override
    public List<Move> getPossibleMoves(Position startingPosition) {
        return PositionUtility.getRookMoves(this, startingPosition);
    }
}
