package com.example.chess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pawn extends Piece {
    public Pawn(PieceColor color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public List<Move> getPossibleMoves(Position startingPosition) {
        List<Move> basicPawnMoves = PositionUtility.getPawnMoves(this, startingPosition);
        basicPawnMoves.addAll(this.getEnPassantMoves(startingPosition));
        return basicPawnMoves;
    }

    public List<Move> getEnPassantMoves(Position startingPosition) {
        List<Move> enPassantMoves = new ArrayList<>();
        int direction = this.getColor().equals(PieceColor.BLACK) ? 1 : -1;
        Move lastMove = this.getBoard().getLastMove();
        if (lastMove == null
                || !lastMove.getPiece().getType().equals(PieceType.PAWN)
                || Math.abs(lastMove.getFrom().getRow() - lastMove.getTo().getRow()) != 2
                || Math.abs(lastMove.getTo().getCol() - startingPosition.getCol()) != 1) {
            return enPassantMoves;
        }
        Optional<Piece> pieceToLeft = this.getBoard().getPieceAt(new Position(startingPosition.getRow(), startingPosition.getCol()-1));
        Optional<Piece> pieceToRight = this.getBoard().getPieceAt(new Position(startingPosition.getRow(), startingPosition.getCol()+1));
        if (pieceToLeft.isPresent() && pieceToLeft.get().getType().equals(PieceType.PAWN) && pieceToLeft.get().equals(lastMove.getPiece())) {
            Move enPassantLeft = MoveFactory.fromPositions(this, startingPosition, new Position(startingPosition.getRow()+direction, startingPosition.getCol()-1));
            enPassantLeft.addSpecialMove(PositionMoveEnum.ENPASSANT);
            enPassantMoves.add(enPassantLeft);
        }
        if (pieceToRight.isPresent() && pieceToRight.get().getType().equals(PieceType.PAWN) && pieceToRight.get().equals(lastMove.getPiece())) {
            Move enPassantRight = MoveFactory.fromPositions(this, startingPosition, new Position(startingPosition.getRow()+direction, startingPosition.getCol()+1));
            enPassantRight.addSpecialMove(PositionMoveEnum.ENPASSANT);
            enPassantMoves.add(enPassantRight);
        }
        return enPassantMoves;
    }


}
