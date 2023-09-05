package org.mower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mower.exceptions.OutOfPlateauException;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    private Plateau plateau;

    @BeforeEach
    public void setUp() {
       plateau = new Plateau(3,3);
    }

    @Test
    public void testMoveForward() {
        Position position = new Position(0, 0, 'N', plateau);
        position.moveForward();
        assertEquals("0 1 N", position.toString());

        position = new Position(0, 0, 'E', plateau);
        position.moveForward();
        assertEquals("1 0 E", position.toString());
    }

    @Test
    public void testTurnLeft() {
        Position position = new Position(0, 0, 'N', plateau);
        position.turnLeft();
        assertEquals("0 0 W", position.toString());

        position = new Position(0, 0, 'W', plateau);
        position.turnLeft();
        assertEquals("0 0 S", position.toString());
    }

    @Test
    public void testTurnRight() {
        Position position = new Position(0, 0, 'N', plateau);
        position.turnRight();
        assertEquals("0 0 E", position.toString());

        position = new Position(0, 0, 'E', plateau);
        position.turnRight();
        assertEquals("0 0 S", position.toString());
    }

    @Test
    public void testMoveForwardOutOfPlateau() {
        Position position = new Position(0, 0, 'S', plateau);
        assertThrows(OutOfPlateauException.class, () -> {
            position.moveForward();
        });
    }

}