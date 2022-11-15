package model;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ChessPiece {
	
	protected static final String BLACK_KING_IMAGE_PATH = "resouces/black_king.png";
	protected static final String BLACK_QUEEN_IMAGE_PATH = "resouces/black_queen.png";
	protected static final String BLACK_ROOK_IMAGE_PATH = "resouces/black_rook.png";
	protected static final String BLACK_KNIGHT_IMAGE_PATH = "resouces/black_knight.png";
	protected static final String BLACK_BISHOP_IMAGE_PATH = "resouces/black_bishop.png";
	protected static final String BLACK_PAWN_IMAGE_PATH = "resouces/black_pawn.png";
	
	protected static final String WHITE_KING_IMAGE_PATH = "resouces/white_king.png";
	protected static final String WHITE_QUEEN_IMAGE_PATH = "resouces/white_queen.png";
	protected static final String WHITE_ROOK_IMAGE_PATH = "resouces/white_rook.png";
	protected static final String WHITE_KNIGHT_IMAGE_PATH = "resouces/white_knight.png";
	protected static final String WHITE_BISHOP_IMAGE_PATH = "resouces/white_bishop.png";
	protected static final String WHITE_PAWN_IMAGE_PATH = "resouces/white_pawn.png";
	
	protected String pieceImagePath;
	protected boolean isWhite;
	protected Point position;
	protected Point previousPosition;
	protected ChessBoard board;

	public ChessPiece(boolean isWhite, Point position, ChessBoard board) {
		this.board = board;
		this.isWhite = isWhite;
		this.position = position;
	}
	
	public ImageView getImageView(double xSize, double ySize)  {
		ImageView pic = new ImageView();
		try {
			Image image = new Image(new FileInputStream(this.pieceImagePath));
			pic.setFitWidth(xSize);
			pic.setFitHeight(ySize);
			pic.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return pic;
	}
	
	public void changePosition(Point newPosition) {
		this.previousPosition = this.position;
		this.position = newPosition;
	}
	
	public String getImagePath() {
		return pieceImagePath;
	}
	
	public boolean getIsWhite() {
		return isWhite;
	}
	
	public abstract List<Point> possibleMoves();
	
	public List<Point> checkDiagonals(int maxInDirection) {
		ArrayList<Point> possible = new ArrayList<Point>();
		
		possible.addAll(checkLengthWise(1,1,maxInDirection));
		possible.addAll(checkLengthWise(-1,1,maxInDirection));
		possible.addAll(checkLengthWise(1,-1,maxInDirection));
		possible.addAll(checkLengthWise(-1,-1,maxInDirection));
		
		return possible;
	}
	
	public List<Point> checkRows(int maxInDirection) {
		ArrayList<Point> possible = new ArrayList<Point>();
		
		possible.addAll(checkLengthWise(1,0,maxInDirection));
		possible.addAll(checkLengthWise(-1,0,maxInDirection));
		possible.addAll(checkLengthWise(0,-1,maxInDirection));
		possible.addAll(checkLengthWise(0,-1,maxInDirection));
		
		return possible;
	}
	
	public List<Point> checkLengthWise(int leftOrRight, int upOrDown, int maxInDirection) {
		ArrayList<Point> possible = new ArrayList<Point>();
		int x = position.x + leftOrRight;
		int y = position.y + upOrDown;
		int counter = 0;
		while (x >= 0 && x <= 7 && y >= 0 && y <= 7 && counter < maxInDirection) {
			if (board.getPiece(new Point(x, y)) == null)
				possible.add(new Point(x, y));
			else {
				if(board.getPiece(new Point(x, y)).getIsWhite() ^ isWhite) {
					possible.add(new Point(x, y));
				}
				break;
			}
			x += leftOrRight;
			y += upOrDown;
			counter ++;
		}
		return possible;
	}
	

}
