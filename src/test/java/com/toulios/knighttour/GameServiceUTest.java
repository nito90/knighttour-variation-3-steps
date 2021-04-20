package com.toulios.knighttour;

import com.toulios.knighttour.model.KnightMove;
import com.toulios.knighttour.model.Point;
import org.junit.jupiter.api.Test;

import static com.toulios.knighttour.service.GameService.isValid;
import static com.toulios.knighttour.service.GameService.solve;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameServiceUTest {

    private static final int SIZE = 8;

    @Test
    void test_isValidTrue_whenValidCoordinationsProvided(){
        assertTrue(isValid(SIZE, 2, 2));
    }

    @Test
    void test_isValidFalse_whenValidXLessThanZero(){
        assertFalse(isValid(SIZE, -2, 2));
    }

    @Test
    void test_isValidFalse_whenValidYLessThanZero(){
        assertFalse(isValid(SIZE, 2, -2));
    }

    @Test
    void test_isValidFalse_whenValidXGreterThanSize(){
        assertFalse(isValid(SIZE, SIZE + 2, 2));
    }

    @Test
    void test_isValidFalse_whenValidYGreterThanSize(){
        assertFalse(isValid(SIZE, 2, SIZE + 2));
    }

    @Test
    void test_solve_whenValidDataAreProvided(){
        var startPoint = new Point(4,4);
        var endPoint = new Point(5,4);
        assertTrue(solve(SIZE, startPoint, endPoint, new KnightMove()));
    }

    @Test
    void test_solveFalse_whenNoProvidedSolution(){
        var startPoint = new Point(0,0);
        var endPoint = new Point(7,6);
        assertFalse(solve(SIZE, startPoint, endPoint, new KnightMove()));
    }
}
