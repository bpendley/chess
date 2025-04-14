package com.example.chess.model;

import java.util.ArrayList;

public class MoveFactory {

    public static Move fromPositions(Piece piece, Position from, Position to) {
        return Move.builder()
                .piece(piece)
                .from(from)
                .to(to)
                .specialMoveList(new ArrayList<>())
                .build();
    }

}
