package model;

import java.awt.Point;
import java.util.List;

public class Rook extends ChessPiece {

	public Rook(boolean isWhite, Point position, ChessBoard board) {
		super(isWhite, position, board);
		this.pieceImagePath = this.isWhite ? WHITE_ROOK_IMAGE_PATH : BLACK_ROOK_IMAGE_PATH;
	}

	@Override
	public List<Point> possibleMoves() {
		return checkRows(8);
	}

}
