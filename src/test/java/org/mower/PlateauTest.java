package org.mower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    private Plateau plateau;

    @BeforeEach
    public void setUp() {
        plateau = new Plateau(5,5);
    }


    @Test
    public void testIsWithinBoundsWithinBounds() {
        assertTrue(plateau.isWithinBounds(3, 4));
        assertTrue(plateau.isWithinBounds(0, 0));
        assertTrue(plateau.isWithinBounds(5, 5));
    }

    @Test
    public void testIsWithinBoundsOutOfBounds() {
        assertFalse(plateau.isWithinBounds(-1, 3)); // x < 0
        assertFalse(plateau.isWithinBounds(6, 4)); // x > upperRightX
        assertFalse(plateau.isWithinBounds(2, -2)); // y < 0
        assertFalse(plateau.isWithinBounds(3, 6)); // y > upperRightY
    }

}