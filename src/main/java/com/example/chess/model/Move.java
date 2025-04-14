package com.example.chess.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class Move {

    private Piece piece;
    private Position from;
    private Position to;
    private List<PositionMoveEnum> specialMoveList;

    //TODO: implement toString for algebraic notation

    public void addSpecialMove(PositionMoveEnum positionMoveEnum) {
        if (specialMoveList == null) {
            specialMoveList = Collections.singletonList(positionMoveEnum);
        } else {
            this.specialMoveList.add(positionMoveEnum);
        }
    }

}
