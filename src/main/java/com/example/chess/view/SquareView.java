package com.example.chess.view;

import com.example.chess.model.Piece;
import com.example.chess.model.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;

import java.util.Objects;

@Getter
public class SquareView extends StackPane {
    private final Position pos;
    private final boolean isLight;
    private Piece piece;
    private final Rectangle backgroundRect;
    private final Rectangle highlightOverlay;
    private ImageView pieceImage;
    private boolean highlighted = false;

    public SquareView(Position pos, boolean isLight) {
        this.pos = pos;
        this.isLight = isLight;

        setPrefSize(80, 80);

        backgroundRect = new Rectangle(80, 80);
        backgroundRect.setFill(isLight ? Color.BEIGE : Color.DARKSEAGREEN);

        highlightOverlay = new Rectangle(80, 80);
        highlightOverlay.setFill(Color.YELLOW);
        highlightOverlay.setOpacity(0.4);
        highlightOverlay.setVisible(false);

        getChildren().addAll(backgroundRect, highlightOverlay);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;

        if (pieceImage != null) {
            getChildren().remove(pieceImage);
        }

        if (piece != null) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(piece.getImagePath())));
            pieceImage = new ImageView(image);
            pieceImage.setFitWidth(60);
            pieceImage.setFitHeight(60);
            getChildren().add(pieceImage);
        }
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
        highlightOverlay.setVisible(highlighted);
    }
}
