package org.mower;

import java.util.ArrayList;
import java.util.List;

public class MowerController {
    private final Plateau plateau;
    private final List<Mower> mowers;

    public MowerController(Plateau plateau) {
        this.plateau = plateau;
        this.mowers = new ArrayList<>();
    }

    // Deploys a new mower to the plateau and executes its instructions.
    public void deployMower(int x, int y, char orientation, String instructions) {
        Position position = new Position(x, y, orientation, this.plateau);
        Mower mower = new Mower(position);
        mowers.add(mower);
        executeInstructions(mower, instructions);
    }

    // Executes a series of instructions for a given mower.
    public void executeInstructions(Mower mower, String instructions) {
        for (char instruction : instructions.toCharArray()) {
            mower.executeInstruction(instruction);
        }
    }

    // Retrieves the final positions of all mowers as a list of strings.
    public List<String> getFinalMowerPositions() {
        List<String> positions = new ArrayList<>();
        for (Mower mower : mowers) {
            positions.add(mower.toString());
        }
        return positions;
    }
}