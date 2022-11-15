package model;

import java.awt.Point;

public class ChessBoard {

	public ChessPiece[][] board;
	
	public ChessBoard() {
		board = new ChessPiece[8][8];
	}
	
	public void newBoard() {
		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(false, new Point(i,1), this);
			board[6][i] = new Pawn(true, new Point(i,6), this);
		}
		board[0][0] = new Rook(false, new Point(0,0), this);
		board[0][7] = new Rook(false, new Point(7,0), this);
		board[7][0] = new Rook(true, new Point(0,7), this);
		board[7][7] = new Rook(true, new Point(7,7), this);
		
		board[0][1] = new Knight(false, new Point(1,0), this);
		board[0][6] = new Knight(false, new Point(6,0), this);
		board[7][1] = new Knight(true, new Point(1,7), this);
		board[7][6] = new Knight(true, new Point(6,7), this);
		
		board[0][2] = new Bishop(false, new Point(2,0), this);
		board[0][5] = new Bishop(false, new Point(5,0), this);
		board[7][2] = new Bishop(true, new Point(2,7), this);
		board[7][5] = new Bishop(true, new Point(5,7), this);
		
		board[0][3] = new Queen(false, new Point(3,0), this);
		board[7][3] = new Queen(true, new Point(3,7), this);
		
		board[0][4] = new King(false, new Point(4,0), this);
		board[7][4] = new King(true, new Point(4,7), this);
		
	}
	
	public ChessPiece getPiece(Point p) {
		return board[p.y][p.x];
	}
	
	public void movePiece(Point initial, Point target) {
		ChessPiece selectedPiece = this.getPiece(initial);
		if (selectedPiece != null) {
			board[initial.y][initial.x] = null;
			board[target.y][target.x] = selectedPiece;
			selectedPiece.changePosition(target);
		}
	}

}
