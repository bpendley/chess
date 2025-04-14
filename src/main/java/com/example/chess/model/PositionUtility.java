package com.example.chess.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PositionUtility {

    private static final int[][] KING_OFFSETS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    private static final int[][] KNIGHT_OFFSETS = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    public static List<Move> getKingMoves(Piece piece, Position from) {
        return getMovesByOffsets(piece, from, KING_OFFSETS);
    }

    public static List<Move> getKnightMoves(Piece piece, Position from) {
        return getMovesByOffsets(piece, from, KNIGHT_OFFSETS);
    }

    public static List<Move> getQueenMoves(Piece piece, Position from) {
        List<Move> diagonals = getDiagonalMoves(piece, from);
        List<Move> horizontalsAndVerticals = getStraightMoves(piece, from);
        diagonals.addAll(horizontalsAndVerticals);
        return diagonals;
    }

    public static List<Move> getRookMoves(Piece piece, Position from) {
        return getStraightMoves(piece, from);
    }

    public static List<Move> getBishopsMoves(Piece piece, Position from) {
        return getDiagonalMoves(piece, from);
    }


    public static List<Move> getPawnMoves(Piece piece, Position from) {
        int row = from.getRow();
        int col = from.getCol();
        List<Position> positionList = new ArrayList<>();
        int direction = piece.getColor().equals(PieceColor.BLACK) ? 1 : -1;
        Position upOne = new Position(row + direction, col);
        Position upTwo = new Position(row + (2*direction), col);
        if (piece.getBoard().isEmpty(upOne)) {
            positionList.add(upOne);
            if (piece.isStartingPosition() && piece.getBoard().isEmpty(upTwo)) {
                positionList.add(upTwo);
            }
        }

        Position diagonalLeft = new Position(row+direction, col-1);
        Position diagonalRight = new Position(row+direction, col+1);
        Optional<Piece> potentialPieceLeft = piece.getBoard().getPieceAt(diagonalLeft);
        if (diagonalLeft.isValid() &&
                potentialPieceLeft.isPresent() &&
                !potentialPieceLeft.get().getColor().equals(piece.getColor())) {
            positionList.add(diagonalLeft);
        }
        Optional<Piece> potentialPieceRight = piece.getBoard().getPieceAt(diagonalRight);
        if (diagonalRight.isValid() &&
                potentialPieceRight.isPresent()
                && !potentialPieceRight.get().getColor().equals(piece.getColor())) {
            positionList.add(diagonalRight);
        }

        return getMovesFromPositions(piece, from, positionList);
    }


    private static List<Move> collectInDirection(Piece piece, Position from, int rowIncrement, int colIncrement) {
        List<Position> positions = new ArrayList<>();
        int row = from.getRow();
        int col = from.getCol();

        while (true) {
            row += rowIncrement;
            col += colIncrement;
            Position currentPos = new Position(row, col);
            if (!currentPos.isValid()) break;

            Optional<Piece> pieceAtPos = piece.getBoard().getPieceAt(currentPos);
            if (pieceAtPos.isPresent()) {
                if (!pieceAtPos.get().getColor().equals(piece.getColor())){
                    positions.add(currentPos);
                }
                break;
            }

            positions.add(currentPos);
        }

        return getMovesFromPositions(piece, from, positions);
    }

    private static List<Move> getStraightMoves(Piece piece, Position startingPos) {
        List<Move> res = new ArrayList<>();
        res.addAll(collectInDirection(piece, startingPos, 1, 0));   // down right
        res.addAll(collectInDirection(piece, startingPos, -1, 0));  // down left
        res.addAll(collectInDirection(piece, startingPos, 0, 1));  // up right
        res.addAll(collectInDirection(piece, startingPos, 0, -1)); // up left
        return res;
    }



    private static List<Move> getDiagonalMoves(Piece piece, Position startingPos) {
        List<Move> res = new ArrayList<>();
        res.addAll(collectInDirection(piece, startingPos, 1, 1));   // down right
        res.addAll(collectInDirection(piece, startingPos, 1, -1));  // down left
        res.addAll(collectInDirection(piece, startingPos, -1, 1));  // up right
        res.addAll(collectInDirection(piece, startingPos, -1, -1)); // up left
        return res;
    }


    private static List<Move> getMovesByOffsets(Piece piece, Position from, int[][] offsets) {
        List<Position> result = new ArrayList<>();
        int row = from.getRow();
        int col = from.getCol();

        for (int[] offset : offsets) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];
            Position pos = new Position(newRow, newCol);
            if (!pos.isValid()) continue;

            Optional<Piece> occupyingPiece = piece.getBoard().getPieceAt(pos);
            if (occupyingPiece.isEmpty() || occupyingPiece.get().getColor() != piece.getColor()) {
                result.add(pos);
            }
        }

        return getMovesFromPositions(piece, from, result);
    }

    private static List<Move> getEnPassantMoves(Piece piece, Position from) {
        List<Move> enPassantMoves = new ArrayList<>();
        Board board = piece.getBoard();
        Move lastMove = board.getLastMove();

        // Validate that the last move was a pawn moving two spaces
        if (lastMove == null || !lastMove.getPiece().getName().equals("pawn")) return enPassantMoves;

        Position lastFrom = lastMove.getFrom();
        Position lastTo = lastMove.getTo();

        if (Math.abs(lastFrom.getRow() - lastTo.getRow()) != 2) return enPassantMoves;

        // Check if the pawn that moved is horizontally adjacent to our pawn
        int direction = piece.getColor().equals(PieceColor.WHITE) ? -1 : 1;
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        if (lastTo.getRow() != fromRow) return enPassantMoves;
        if (Math.abs(lastTo.getCol() - fromCol) != 1) return enPassantMoves;

        // Determine capture square
        Position capturePos = new Position(fromRow + direction, lastTo.getCol()+1);

        if (!capturePos.isValid()) return enPassantMoves;

        Move enPassant = Move.builder()
                .piece(piece)
                .from(from)
                .to(capturePos)
                .specialMoveList(List.of(PositionMoveEnum.ENPASSANT))
                .build();

        enPassantMoves.add(enPassant);
        return enPassantMoves;
    }

    private static List<Move> getMovesFromPositions(Piece piece, Position from, List<Position> positions) {
        return positions.stream().map(position -> MoveFactory.fromPositions(piece, from, position)).collect(Collectors.toList());
    }


}


