package com.example.chess.model;

public class BoardFactory {

    public Board createInitialBoard() {
        Board board = new Board();
        board.placePiece(new Position(0, 0), new Rook(PieceColor.BLACK, board));
        board.placePiece(new Position(0, 1), new Knight(PieceColor.BLACK, board));
        board.placePiece(new Position(0, 2), new Bishop(PieceColor.BLACK, board));
        board.placePiece(new Position(0, 3), new Queen(PieceColor.BLACK, board));
        board.placePiece(new Position(0, 4), new King(PieceColor.BLACK, board));
        board.placePiece(new Position(0, 5), new Bishop(PieceColor.BLACK, board));
        board.placePiece(new Position(0, 6), new Knight(PieceColor.BLACK, board));
        board.placePiece(new Position(0, 7), new Rook(PieceColor.BLACK, board));

        board.placePiece(new Position(1, 0), new Pawn(PieceColor.BLACK, board));
        board.placePiece(new Position(1, 1), new Pawn(PieceColor.BLACK, board));
        board.placePiece(new Position(1, 2), new Pawn(PieceColor.BLACK, board));
        board.placePiece(new Position(1, 3), new Pawn(PieceColor.BLACK, board));
        board.placePiece(new Position(1, 4), new Pawn(PieceColor.BLACK, board));
        board.placePiece(new Position(1, 5), new Pawn(PieceColor.BLACK, board));
        board.placePiece(new Position(1, 6), new Pawn(PieceColor.BLACK, board));
        board.placePiece(new Position(1, 7), new Pawn(PieceColor.BLACK, board));

        board.placePiece(new Position(7, 0), new Rook(PieceColor.WHITE, board));
        board.placePiece(new Position(7, 1), new Knight(PieceColor.WHITE, board));
        board.placePiece(new Position(7, 2), new Bishop(PieceColor.WHITE, board));
        board.placePiece(new Position(7, 3), new Queen(PieceColor.WHITE, board));
        board.placePiece(new Position(7, 4), new King(PieceColor.WHITE, board));
        board.placePiece(new Position(7, 5), new Bishop(PieceColor.WHITE, board));
        board.placePiece(new Position(7, 6), new Knight(PieceColor.WHITE, board));
        board.placePiece(new Position(7, 7), new Rook(PieceColor.WHITE, board));

        board.placePiece(new Position(6, 0), new Pawn(PieceColor.WHITE, board));
        board.placePiece(new Position(6, 1), new Pawn(PieceColor.WHITE, board));
        board.placePiece(new Position(6, 2), new Pawn(PieceColor.WHITE, board));
        board.placePiece(new Position(6, 3), new Pawn(PieceColor.WHITE, board));
        board.placePiece(new Position(6, 4), new Pawn(PieceColor.WHITE, board));
        board.placePiece(new Position(6, 5), new Pawn(PieceColor.WHITE, board));
        board.placePiece(new Position(6, 6), new Pawn(PieceColor.WHITE, board));
        board.placePiece(new Position(6, 7), new Pawn(PieceColor.WHITE, board));
        return board;
    }

}
