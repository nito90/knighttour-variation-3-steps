package com.toulios.knighttour.util;

import com.toulios.knighttour.model.Board;
import com.toulios.knighttour.model.Point;
import com.toulios.knighttour.model.SolvableBoard;

import java.util.Comparator;
import java.util.List;

public final class GameUtil {

    private static final int ASCII_CODE_A = 65;
    private static final int INITIAL_POINT_VALUE = 0;

    /**
     * Print the result of the board's solution
     *
     * @param solvableBoardList The list with all the possible solutions of the board
     */
    public static void logResult(List<SolvableBoard> solvableBoardList) {
        if (!solvableBoardList.isEmpty()) {
            var board = solvableBoardList.stream()
                    .sorted(Comparator.comparing(SolvableBoard::getMovesToSolution))
                    .findFirst()
                    .get();
            printSolution(board);
        } else {
            System.out.println("There wasn't any solution");
        }
    }

    /**
     * Print the provided solvable board
     *
     * @param solvableBoard
     */
    private static void printSolution(SolvableBoard solvableBoard) {
        System.out.println();
        var board = solvableBoard.getBoard();
        System.out.println("Number of steps to solve the game:" + solvableBoard.getMovesToSolution());
        printPath(board);
        printBoard(board);
        System.out.println();
    }

    /**
     * Print in path format the solution of the given board
     *
     * @param board
     */
    private static void printPath(Board board) {
        System.out.println("**Solution's Path**");
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                var point = board.getPoint(x, y);
                if (point.getValue() != 0) {
                    String value = String.format("%c%d( %d )", y + ASCII_CODE_A, x + 1, point.getValue());
                    System.out.print(value + "\t");
                }
            }
        }
        System.out.println();
    }

    /**
     * Print in board format the solution of the given board
     *
     * @param board
     */
    private static void printBoard(Board board) {
        System.out.println("**Solution's Board**");
        for (int x = board.getSize() - 1; x >= 0; x--) {
            for (int y = 0; y < board.getSize(); y++) {
                var point = board.getPoint(x, y);
                if (point.getValue() != 0) {
                    String value = String.format("%c%d( %d )", y + ASCII_CODE_A, x + 1, point.getValue());
                    System.out.print(value + "\t\t");
                } else {
                    String value = String.format("%c%d", y + ASCII_CODE_A, x + 1);
                    System.out.print(value + "\t\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * Print an empty board to help the player to choose the positions they want to play
     *
     * @param size
     */
    public static void printEmptyBoard(int size) {
        System.out.println("**Solution's Board**");
        for (int x = size - 1; x >= 0; x--) {
            for (int y = 0; y < size; y++) {
                String value = String.format("%c%d(%d,%d)", y + ASCII_CODE_A, x + 1, x, y);
                System.out.print(value + "\t\t");
            }
            System.out.println();
        }
    }

    /**
     * Initialization of a board of side size equals to the provided side property
     *
     * @param size Size of board's side
     * @return a board with initial points
     */
    public static Board initializationOfBoard(int size) {
        /* Initialization of solution matrix */
        var points = new Point[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                points[x][y] = new Point(x, y, INITIAL_POINT_VALUE);
            }
        }
        return new Board(points, size);
    }

    /**
     * Deep copy of the given board
     *
     * @param chessboard
     * @return
     */
    public static Board copyChessboard(Board chessboard) {
        /* Initialization of solution matrix */
        var size = chessboard.getSize();
        var points = new Point[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                var point = chessboard.getPoint(x, y);
                points[x][y] = new Point(x, y, point.getValue());
            }
        }
        return new Board(points, size);
    }
}
