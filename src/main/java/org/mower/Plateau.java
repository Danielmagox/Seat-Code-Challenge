package org.mower;

public class Plateau {
    private final int upperRightX;
    private final int upperRightY;

    public Plateau(int upperRightX, int upperRightY) {
        this.upperRightX = upperRightX;
        this.upperRightY = upperRightY;
    }
    // Checks if the given coordinates (x, y) are within the bounds of the plateau.
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x <= upperRightX && y >= 0 && y <= upperRightY;
    }
}
