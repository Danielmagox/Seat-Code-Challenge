package org.mower;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Mower {
    private final Position position;

    public Mower(Position position) {
        this.position = position;
    }

    // Executes a given instruction ('L', 'R', or 'M') to change the mower's position.
    public void executeInstruction(char instruction) {
        // Define a mapper for instructions
        Map<Character, Consumer<Position>> instructionMap = new HashMap<>();
        instructionMap.put('L', Position::turnLeft);
        instructionMap.put('R', Position::turnRight);
        instructionMap.put('M', Position::moveForward);

        // Get the corresponding action for the instruction and execute it
        instructionMap.getOrDefault(instruction, (pos) -> {}).accept(position);
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
