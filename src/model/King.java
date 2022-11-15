package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {

	public King(boolean isWhite, Point position, ChessBoard board) {
		super(isWhite, position, board);
		this.pieceImagePath = this.isWhite ? WHITE_KING_IMAGE_PATH : BLACK_KING_IMAGE_PATH;
	}

	@Override
	public List<Point> possibleMoves() {
		ArrayList<Point> possible = new ArrayList<Point>();
		possible.addAll(checkDiagonals(1));
		possible.addAll(checkRows(1));
		return possible;
	}

}
