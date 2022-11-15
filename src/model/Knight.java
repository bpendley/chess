package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {

	public Knight(boolean isWhite, Point position, ChessBoard board) {
		super(isWhite, position, board);
		this.pieceImagePath = this.isWhite ? WHITE_KNIGHT_IMAGE_PATH : BLACK_KNIGHT_IMAGE_PATH;
	}

	@Override
	public List<Point> possibleMoves() {
		ArrayList<Point> possible = new ArrayList<Point>();
		
		possible.addAll(checkLengthWise(1,2,1));
		possible.addAll(checkLengthWise(1,-2,1));
		possible.addAll(checkLengthWise(2,1,1));
		possible.addAll(checkLengthWise(2,-1,1));
		possible.addAll(checkLengthWise(-1,2,1));
		possible.addAll(checkLengthWise(-1,-2,1));
		possible.addAll(checkLengthWise(-2,1,1));
		possible.addAll(checkLengthWise(-2,-1,1));
		
		return possible;
	}

}
