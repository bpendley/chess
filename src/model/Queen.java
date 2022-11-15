package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

	public Queen(boolean isWhite, Point position, ChessBoard board) {
		super(isWhite, position, board);
		this.pieceImagePath = this.isWhite ? WHITE_QUEEN_IMAGE_PATH : BLACK_QUEEN_IMAGE_PATH;
	}

	@Override
	public List<Point> possibleMoves() {
		System.out.println(position.toString());
		ArrayList<Point> possible = new ArrayList<Point>();
		possible.addAll(checkDiagonals(8));
		possible.addAll(checkRows(8));
		return possible;
	}

}
