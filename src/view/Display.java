package view;

import java.awt.Point;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.ChessBoard;
import model.ChessPiece;

public class Display extends Application{

	
	private static final int BLOCK_SIZE = 40;
	private static final int BOARD_SIZE = 8;
	private static final int WINDOW_SIZE = BOARD_SIZE*BLOCK_SIZE;

	private Scene simulationScene;
	private Group simulationDrawing = new Group();;
	private Pane[][] displayGrid;
	private ChessBoard board;
	private Point selected = new Point(-1, -1);
	private Point destination = new Point(-1, -1);
		
	public void start(Stage stage) {
		board = new ChessBoard();
		board.newBoard();
		simulationScene = setupScene();
		stage.setScene(simulationScene);
		stage.setTitle("Chess");
		stage.show();
		
	}
	
	private Scene setupScene() {
		this.setupSimulation();
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(simulationDrawing);
		Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE, Color.YELLOW);
        scene.setOnMouseClicked(e -> handleClick(e));
		return scene;
	}
	
	private void handleClick(MouseEvent e) {
		//System.out.print(String.format("%d, %d", (int)(e.getSceneX()/BLOCK_SIZE), (int)(e.getSceneY()/BLOCK_SIZE)));
		if (!selected.equals(destination)) {
			destination = new Point((int)(e.getSceneX()/BLOCK_SIZE), (int)(e.getSceneY()/BLOCK_SIZE));
			board.movePiece(selected, destination);
			selected = new Point((int)(e.getSceneX()/BLOCK_SIZE), (int)(e.getSceneY()/BLOCK_SIZE));
		} else {
			selected = new Point((int)(e.getSceneX()/BLOCK_SIZE), (int)(e.getSceneY()/BLOCK_SIZE));
		}

		redrawSimulation();
	}
	
	private void highlightPossible() {
		ChessPiece p = board.getPiece(selected);
		for(Point possible: p.possibleMoves()) {
			Rectangle rect = (Rectangle) this.displayGrid[possible.y][possible.x].getChildren().get(0);
			rect.setOpacity(.7);
		}
	}
	

	
	private void setupSimulation() {
		this.displayGrid = new Pane[this.BOARD_SIZE][this.BOARD_SIZE];
		for (int currentRow = 0; currentRow < this.BOARD_SIZE; currentRow++) {
			for(int currentColumn = 0; currentColumn < Display.BOARD_SIZE; currentColumn++) {
				
				Pane p = new StackPane();
				p.relocate(currentColumn*this.BLOCK_SIZE, currentRow*this.BLOCK_SIZE);
				this.displayGrid[currentRow][currentColumn] = p;
				
				Rectangle placeHolderRectangle = new Rectangle(currentColumn*this.BLOCK_SIZE, currentRow*this.BLOCK_SIZE, this.BLOCK_SIZE, this.BLOCK_SIZE);
				
				if ((currentColumn+currentRow)%2==1) {
					placeHolderRectangle.setFill(Color.DARKSEAGREEN);
				}else {
					placeHolderRectangle.setFill(Color.GAINSBORO);
				}
				
				this.displayGrid[currentRow][currentColumn].getChildren().add(placeHolderRectangle);
				
				ChessPiece currentPiece = board.getPiece(new Point(currentColumn, currentRow));
				if (!(currentPiece == null)) {
					this.displayGrid[currentRow][currentColumn].getChildren().add(currentPiece.getImageView(this.BLOCK_SIZE, this.BLOCK_SIZE));
				}
				simulationDrawing.getChildren().add(p);
			}
		}
	}
	
	public void redrawSimulation() {
		for(int currentRow = 0; currentRow < this.displayGrid.length; currentRow++) {
			for(int currentColumn = 0; currentColumn < this.displayGrid[currentRow].length; currentColumn++) {
				if (this.displayGrid[currentRow][currentColumn].getChildren().size() > 1) {
					this.displayGrid[currentRow][currentColumn].getChildren().remove(1);
				}
				
				Rectangle rect = (Rectangle) this.displayGrid[currentRow][currentColumn].getChildren().get(0);
				rect.setOpacity(1.0);
				if ((currentColumn+currentRow)%2==1) {
					rect.setFill(Color.DARKSEAGREEN);
				}else {
					rect.setFill(Color.GAINSBORO);
				}
				
				ChessPiece currentPiece = board.getPiece(new Point(currentColumn, currentRow));
				if (!(currentPiece == null)) {
					this.displayGrid[currentRow][currentColumn].getChildren().add(currentPiece.getImageView(this.BLOCK_SIZE, this.BLOCK_SIZE));
				}
			}
		}
		if (!(selected.equals(destination))) {
			this.highlightPossible();
		}
	}
	
	public static void main (String[] args) {
		launch(args);
	}
	
}