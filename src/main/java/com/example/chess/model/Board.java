package com.example.chess.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;


public class Board {
    private final Map<Position, Piece> positionToPiece = new HashMap<>();
    @Getter
    private PieceColor playerToMove;
    private Deque<Move> moveHistory;

    public static final int SIZE = 8;

    public Board() {
        playerToMove = PieceColor.WHITE;
        moveHistory = new ArrayDeque<>();
    }

    public Optional<Piece> getPieceAt(Position pos) {
        return Optional.ofNullable(positionToPiece.get(pos));
    }

    public void placePiece(Position pos, Piece piece) {
        positionToPiece.put(pos, piece);
    }

    public void movePiece(Position from, Position to) {
        Piece piece = positionToPiece.remove(from);
        if (piece != null) {
            piece.moveOffInitial();
            positionToPiece.put(to, piece);
            moveHistory.addLast(MoveFactory.fromPositions(piece, from, to));
        }
    }

    public void movePiece2(Move move) {
        Piece piece = positionToPiece.remove(move.getFrom());
        if (piece != null) {
            piece.moveOffInitial();
            positionToPiece.put(move.getTo(), piece);
            moveHistory.addLast(MoveFactory.fromPositions(piece, move.getFrom(), move.getTo()));
        }
        if (!move.getSpecialMoveList().isEmpty()) {
            handleSpecialMoves(move);
        }
    }

    public boolean handleMove(Position from, Position to) {
        Optional<Piece> optionalPiece = this.getPieceAt(from);
        if (optionalPiece.isEmpty()) {
            return false;
        }
        Piece piece = optionalPiece.get();
        Map<Position, Move> moveMap = piece.getMoveMap(from);
        if (moveMap.containsKey(to)) {
            movePiece2(moveMap.get(to));
            System.out.println("legal move");
            return true;
        } else {
            movePiece(from, to);
            System.out.println("illegal move");
            return false;
        }
    }

    public void removePiece(Position position) {
        positionToPiece.remove(position);
    }

    public void handleSpecialMoves(Move move) {
        move.getSpecialMoveList().forEach(specialMove -> {
            if (specialMove.equals(PositionMoveEnum.ENPASSANT)) {
                handleEnpassant(move);
            }
        });
    }

    public void handleEnpassant(Move move) {
        int direction = move.getPiece().getColor().equals(PieceColor.BLACK) ? 1 : -1;
        System.out.println(new Position(move.getTo().getRow() - direction, move.getTo().getCol()));
        removePiece(new Position(move.getTo().getRow() - direction, move.getTo().getCol()));
    }

    public boolean isEmpty(Position pos) {
        return getPieceAt(pos).isEmpty();
    }

    public Move getLastMove() {
        return moveHistory.peekLast();
    }

    public List<Piece> getPiecesForTeam(PieceColor pieceColor) {
        return positionToPiece.values().stream()
                .filter(piece -> piece.getColor() == pieceColor)
                .collect(Collectors.toList());
    }

}
