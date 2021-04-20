package com.toulios.knighttour.service;

import com.toulios.knighttour.model.ChessMove;

public interface Game {

    /**
     * Starts an automated job to solve a custom variation of Knight's tour problem
     *
     * @param chessMove The allowed moves
     */
    boolean play(ChessMove chessMove);
}
