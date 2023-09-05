package org.mower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mower.exceptions.OutOfPlateauException;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class MowerControllerTest {
    private MowerController controller;

    @BeforeEach
    public void setUp() {
        // Configure the plateau and controller before each test
        Plateau plateau = new Plateau(10, 10); // Por ejemplo, un plateau de 5x5
        controller = new MowerController(plateau);
    }

    @Test
    public void testDeployMower() {
        //Verify deployMowner function y more cases
        controller.deployMower(1, 2, 'N', "LMLMLMLMM");
        controller.deployMower(3, 3, 'E', "MMRMMRMRRM");
        controller.deployMower(5, 5, 'N', "MMRRMMRMRMMLM");
        controller.deployMower(2, 2, 'E', "MMMMMMRM");

        List<String> finalPositions = controller.getFinalMowerPositions();
        assertEquals(4, finalPositions.size());

        assertEquals("1 3 N", finalPositions.get(0));
        assertEquals("5 1 E", finalPositions.get(1));
        assertEquals("3 7 W", finalPositions.get(2));
        assertEquals("8 1 S", finalPositions.get(3));
    }

    @Test
    public void testDeployMowerOutOfPlateau() {
        // Attempting to deploy a mower outside the plateau should throw the OutOfPlateauException
        assertThrows(OutOfPlateauException.class, () -> {
            controller.deployMower(11, 11, 'N', "MMMMMM"); // Intentamos desplegar fuera del plateau
        });
    }
}