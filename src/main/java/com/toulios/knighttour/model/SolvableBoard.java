package com.toulios.knighttour.model;

/**
 * Information of board's solution
 */
public class SolvableBoard {
    private int movesToSolution;
    private Board board;

    public SolvableBoard(int movesToSolution, Board board) {
        this.movesToSolution = movesToSolution;
        this.board = board;
    }

    public int getMovesToSolution() {
        return movesToSolution;
    }

    public void setMovesToSolution(int movesToSolution) {
        this.movesToSolution = movesToSolution;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
