package com.toulios.knighttour.service;

import com.toulios.knighttour.model.Board;
import com.toulios.knighttour.model.Point;
import com.toulios.knighttour.model.SolvableBoard;
import com.toulios.knighttour.model.ChessMove;
import com.toulios.knighttour.model.KnightMove;

import java.util.ArrayList;
import java.util.List;

import static com.toulios.knighttour.util.GameUtil.*;
import static com.toulios.knighttour.util.Reader.getInt;
import static com.toulios.knighttour.util.Reader.getPoint;

public class GameService {

    private static final int INITIAL_INCREMENT_VALUE = 1;
    private static final int MAXIMUM_ALLOWED_GAME_MOVES = 5;

    public static boolean play() {
        System.out.println("** Welcome to Knight's tour game **");
        System.out.print("Enter a valid number [1...] for the size of the board: ");
        var size = getInt();
        while (size <= 0) {
            System.out.print("Please enter a valid number [1...N] for the size of the board: ");
            size = getInt();
        }

        printEmptyBoard(size);

        System.out.println("Enter details for start point:");
        var startPoint = getPoint(size);
        while (!isValid(size, startPoint.getX(), startPoint.getY())) {
            System.out.println("Enter valid details for start point:");
            startPoint = getPoint(size);
        }

        System.out.println("Enter details for end point:");
        var endPoint = getPoint(size);
        while (!isValid(size, endPoint.getX(), endPoint.getY())) {
            System.out.println("Enter valid details for end point:");
            endPoint = getPoint(size);
        }

        return solve(size, startPoint, endPoint, new KnightMove());
    }

    /**
     * Solve a variation of Knight's Tour problem using Backtracking. It will print the solution with the shortest path
     * in 3 steps if exists otherwise it will log an error to inform the user that there wasn't any solution
     */
    public static boolean solve(int size, Point startPoint, Point endPoint, ChessMove chessMove) {
        Board chessBoard = initializeGame(size, startPoint);

        //All possible solutions
        List<SolvableBoard> solvableBoardList = new ArrayList<>();
        solveHelper(startPoint, INITIAL_INCREMENT_VALUE, chessBoard, chessMove, endPoint,
                solvableBoardList);
        logResult(solvableBoardList);
        return !solvableBoardList.isEmpty();
    }

    /**
     * Validate x,y coordinates
     */
    public static boolean isValid(int size, int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    /**
     * Initialize a new board and validate the initial given points
     */
    private static Board initializeGame(int size, Point startPoint) {
        var chessBoard = initializationOfBoard(size);

        // initialization of first position
        chessBoard.getPoint(startPoint.getX(), startPoint.getY()).setValue(INITIAL_INCREMENT_VALUE);
        return chessBoard;
    }

    /* A recursive utility function to solve this custom Knight Tour problem variation */
    private static boolean solveHelper(Point currentPoint, int moveOrder, Board chessboard, ChessMove chessMove,
                                Point endPoint, List<SolvableBoard> solvableBoardList) {
        int xyCounter;
        if (moveOrder == MAXIMUM_ALLOWED_GAME_MOVES) {
            return false;
        }

        if (isSolved(currentPoint, endPoint)) {
            return handleSolution(moveOrder, chessboard, solvableBoardList);
        }

        ++moveOrder;

        var x = currentPoint.getX();
        var y = currentPoint.getY();

        var xMoves = chessMove.getX();
        var yMoves = chessMove.getY();

        /* Try all next moves from the current coordinate x, y */
        for (xyCounter = 0; xyCounter < xMoves.length; xyCounter++) {

            var nextPossibleX = x + xMoves[xyCounter];
            var nextPossibleY = y + yMoves[xyCounter];

            if (isSafe(nextPossibleX, nextPossibleY, chessboard)) {
                makeNextMove(moveOrder, chessboard, chessMove, endPoint, solvableBoardList, nextPossibleX,
                        nextPossibleY);
            }
        }
        return false;
    }

    /**
     * Execute the next available move
     */
    private static void makeNextMove(int moveOrder, Board chessboard, ChessMove chessMove, Point endPoint,
                              List<SolvableBoard> solvableBoardList, int nextPossibleX, int nextPossibleY) {
        chessboard.getPoint(nextPossibleX, nextPossibleY).setValue(moveOrder);
        var nextPoint = chessboard.getPoint(nextPossibleX, nextPossibleY);
        solveHelper(nextPoint, moveOrder, chessboard, chessMove, endPoint, solvableBoardList);
        nextPoint.setValue(0);
    }

    /**
     * Handle the solution of the board
     */
    private static boolean handleSolution(int moveOrder, Board chessboard, List<SolvableBoard> solvableBoardList) {
        var copyChessBoard = copyChessboard(chessboard);
        var solvedBoard = new SolvableBoard(moveOrder - 1, copyChessBoard);
        solvableBoardList.add(solvedBoard);
        return true;
    }

    /**
     * Check if the board is solved while checking the equality of two points current ones and the end point.
     */
    private static boolean isSolved(Point currentPoint, Point endPoint) {
        return currentPoint.equals(endPoint);
    }

    /**
     * Check if the next move is valid
     */
    private static boolean isSafe(int x, int y, Board board) {
        var nextPossiblePoint = board.getPoint(x, y);
        return (nextPossiblePoint != null && nextPossiblePoint.getValue() == 0);
    }
}
