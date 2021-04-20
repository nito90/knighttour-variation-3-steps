package com.toulios.knighttour.util;

import com.toulios.knighttour.model.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Reader {
    /**
     * Get an integer from the console
     */
    public static int getInt() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error type of input. Please insert again the numeric value: ");
            return getInt();
        }
    }

    /**
     * Get a new point from the console
     */
    public static Point getPoint(int size) {
        int x = getCoordinate(size, "x");
        int y = getCoordinate(size, "y");
        return new Point(x, y);
    }

    /**
     * Get a new value for each coordinate you will give
     */
    private static int getCoordinate(int size, String coordinate) {
        System.out.print("Enter a valid number [0..." + size + ") for " + coordinate + " pos: ");
        var coordinateValue = getInt();
        while (size < 0) {
            System.out.print("Please enter a valid number [0..." + size + ") for " + coordinate + " pos: ");
            coordinateValue = getInt();
        }
        return coordinateValue;
    }
}
