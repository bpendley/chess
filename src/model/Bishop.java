package model;

import java.awt.Point;
import java.util.List;

public class Bishop extends ChessPiece {

	public Bishop(boolean isWhite, Point position, ChessBoard board) {
		super(isWhite, position, board);
		this.pieceImagePath = this.isWhite ? WHITE_BISHOP_IMAGE_PATH : BLACK_BISHOP_IMAGE_PATH;
	}

	@Override
	public List<Point> possibleMoves() {		
		return checkDiagonals(8);
	}
	


}
