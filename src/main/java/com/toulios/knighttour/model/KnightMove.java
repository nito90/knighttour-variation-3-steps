package com.toulios.knighttour.model;

/**
 * The allowed moves for a chess knight
 */
public class KnightMove extends ChessMove {

    public KnightMove() {
        super();
        setX(new int[]{2, 1, -1, -2, -2, -1, 1, 2});
        setY(new int[]{1, 2, 2, 1, -1, -2, -2, -1});
    }

}
