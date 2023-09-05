package org.mower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MowerTest {

    private Plateau plateau;

    @BeforeEach
    public void setUp() {
        plateau = new Plateau(3,3);
    }

    @Test
    public void testExecuteInstructionTurnLeft() {
        Position position = new Position(0, 0, 'N', plateau);
        Mower mower = new Mower(position);

        mower.executeInstruction('L');
        assertEquals("0 0 W", mower.toString());
    }

    @Test
    public void testExecuteInstructionTurnRight() {
        Position position = new Position(0, 0, 'N', plateau);
        Mower mower = new Mower(position);

        mower.executeInstruction('R');
        assertEquals("0 0 E", mower.toString());
    }

    @Test
    public void testExecuteInstructionMoveForward() {
        Position position = new Position(0, 0, 'N', plateau);
        Mower mower = new Mower(position);

        mower.executeInstruction('M');
        assertEquals("0 1 N", mower.toString());
    }

}