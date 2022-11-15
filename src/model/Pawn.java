package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {
	
	public Pawn(boolean isWhite, Point position, ChessBoard board) {
		super(isWhite, position, board);
		this.pieceImagePath = this.isWhite ? WHITE_PAWN_IMAGE_PATH : BLACK_PAWN_IMAGE_PATH;
	}

	@Override
	public List<Point> possibleMoves() {
		ArrayList<Point> possibleMoves = new ArrayList<Point>();
		if (isWhite) {
			if (position.y > 0 && board.getPiece(new Point(position.x,position.y-1)) == null) {
				possibleMoves.add(new Point(position.x,position.y-1));
				if (position.y == 6 && (board.getPiece(new Point(position.x,4)) == null))
					possibleMoves.add(new Point(position.x,4));
			}
			if (position.y > 0 && position.x > 0 && board.getPiece(new Point(position.x-1,position.y-1)) != null && !board.getPiece(new Point(position.x-1,position.y-1)).getIsWhite())
				possibleMoves.add(new Point(position.x-1,position.y-1));
			if (position.y > 0 && position.x < 7 && board.getPiece(new Point(position.x+1,position.y-1)) != null && !board.getPiece(new Point(position.x+1,position.y-1)).getIsWhite())
				possibleMoves.add(new Point(position.x+1,position.y-1));
				
		} else {
			if (position.y < 7 && board.getPiece(new Point(position.x,position.y+1)) == null) {
				possibleMoves.add(new Point(position.x,position.y+1));
				if (position.y == 1 && (board.getPiece(new Point(position.x,3)) == null))
					possibleMoves.add(new Point(position.x,3));
			}
			if (position.y < 7 && position.x > 0 && board.getPiece(new Point(position.x-1,position.y+1)) != null && board.getPiece(new Point(position.x-1,position.y+1)).getIsWhite())
				possibleMoves.add(new Point(position.x-1,position.y+1));
			if (position.y < 0 && position.x < 7 && board.getPiece(new Point(position.x+1,position.y+1)) != null && board.getPiece(new Point(position.x+1,position.y+1)).getIsWhite())
				possibleMoves.add(new Point(position.x+1,position.y+1));
		}
		return possibleMoves;
	}


}
