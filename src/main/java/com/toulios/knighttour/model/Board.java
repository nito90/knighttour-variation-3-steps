package com.toulios.knighttour.model;

/**
 * The chess board
 */
public class Board {
    Point[][] points;
    int size;

    public Board(Point[][] points, int size) {
        this.points = points;
        this.size = size;
    }

    public Point[][] getPoints() {
        return points;
    }

    public void setPoints(Point[][] points) {
        this.points = points;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Point getPoint(int x, int y) {
        if (isValidPosition(x, y)) {
            return points[x][y];
        }
        return null;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}
