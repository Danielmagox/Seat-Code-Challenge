package org.mower;

import org.mower.constants.PositionConstants;
import org.mower.exceptions.OutOfPlateauException;

import java.util.HashMap;
import java.util.Map;

public class Position {
    private int x;
    private int y;
    private int orientation;
    private final Plateau plateau;

    // Mapping of orientation characters to integer constants
    private static final Map<Character, Integer> orientationMap = new HashMap<>();
    static {
        orientationMap.put('N', PositionConstants.NORTH);
        orientationMap.put('E', PositionConstants.EAST);
        orientationMap.put('S', PositionConstants.SOUTH);
        orientationMap.put('W', PositionConstants.WEST);
    }

    // Constructor for Position class
    public Position(int x, int y, char orientation, Plateau plateau) {
        this.x = x;
        this.y = y;
        this.plateau = plateau;

        // Map the orientation character to its integer constant representation
        Integer orientationValue = orientationMap.get(orientation);
        if (orientationValue != null) {
            this.orientation = orientationValue;
        } else {
            throw new IllegalArgumentException("Invalid orientation");
        }
    }

    // Move the position forward in the current orientation
    public void moveForward() {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        x += dx[orientation];
        y += dy[orientation];

        // Check if the new position is within the plateau bounds
        if (!plateau.isWithinBounds(x, y)) {
            throw new OutOfPlateauException("Mower is out of bounds");
        }
    }

    // Turn the position to the left (counterclockwise)
    public void turnLeft() {
        orientation = (orientation + 3) % 4;
    }

    // Turn the position to the right (clockwise)
    public void turnRight() {
        orientation = (orientation + 1) % 4;
    }

    // Override the toString() method to provide a string representation of the position
    @Override
    public String toString() {
        String orientationStr = Character.toString(PositionConstants.ORIENTATIONS.charAt(orientation));
        return x + " " + y + " " + orientationStr;
    }
}




