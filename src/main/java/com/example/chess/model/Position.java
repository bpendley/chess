package com.example.chess.model;

import lombok.Value;

@Value
public class Position {
    int row;
    int col;

    public boolean isValid() {
        return 0 <= row && row < 8
                && 0 <= col && col <8;
    }
}