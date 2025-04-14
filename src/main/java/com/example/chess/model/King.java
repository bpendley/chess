package com.example.chess.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class King extends Piece {
    public King(PieceColor color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public List<Move> getPossibleMoves(Position startingPosition) {
        return PositionUtility.getKingMoves(this, startingPosition);
    }

    private List<Move> checkForCastling (Position startingPosition){
        List<Piece> pieces 
    }



}
