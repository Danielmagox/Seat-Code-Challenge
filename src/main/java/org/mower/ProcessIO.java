package org.mower;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessIO {
    private final BufferedReader inputReader;
    private final FileWriter outputWriter;
    private int upperRightX;
    private int upperRightY;
    private static final int MAX_PLATEAU_SIZE = 100000;

    public ProcessIO(String inputFilePath, String outputFilePath) throws IOException {
        this.inputReader = new BufferedReader(new FileReader(inputFilePath));
        this.outputWriter = new FileWriter(outputFilePath);
    }

    public List<String> processInputAndSaveOutput() throws IOException {
        String line = inputReader.readLine();

        // Validate and parse plateau dimensions
        String[] plateauDimensions = line.split(" ");
        if (plateauDimensions.length != 2) {
            throw new IllegalArgumentException("Invalid plateau dimensions format");
        }

        upperRightX = Integer.parseInt(plateauDimensions[0]);
        upperRightY = Integer.parseInt(plateauDimensions[1]);

        // Check if the plateau size exceeds the maximum limit
        if (upperRightX > MAX_PLATEAU_SIZE || upperRightY > MAX_PLATEAU_SIZE) {
            throw new IllegalArgumentException("Plateau size exceeds the maximum limit");
        }

        Plateau plateau = new Plateau(upperRightX, upperRightY);

        // Create MowerController
        MowerController controller = new MowerController(plateau);

        // Process mowers
        List<String> finalPositions = new ArrayList<>();
        String mowerLine;
        while ((mowerLine = inputReader.readLine()) != null) {
            String[] mowerInfo = mowerLine.split(" ");
            if (mowerInfo.length != 3) {
                throw new IllegalArgumentException("Invalid mower position format");
            }

            int x = Integer.parseInt(mowerInfo[0]);
            int y = Integer.parseInt(mowerInfo[1]);
            char orientation = mowerInfo[2].charAt(0);

            // Validate coordinates
            if (!isValidCoordinates(x, y)) {
                throw new IllegalArgumentException("Invalid coordinates");
            }

            String instructions = inputReader.readLine();

            // Deploy mower and execute instructions
            controller.deployMower(x, y, orientation, instructions);

            // Validate and store final position
            String finalPosition = controller.getFinalMowerPositions().get(controller.getFinalMowerPositions().size() - 1);
            if (!isValidPositionFormat(finalPosition)) {
                throw new IllegalArgumentException("Invalid final position format");
            }
            finalPositions.add(finalPosition);
        }

        for (String position : finalPositions) {
            System.out.println(position);
            outputWriter.write(position + System.lineSeparator());
        }

        return finalPositions;
    }
    //Check if there are some negative numbers or coordinates higher than the Plateau size.
    private boolean isValidCoordinates(int x, int y) {
        return x >= 0 && x <= upperRightX && y >= 0 && y <= upperRightY;
    }

    //Check if it matches the expected format like "X Y O"
    private boolean isValidPositionFormat(String position) {
        return position.matches("\\d+ \\d+ [NESW]");
    }

    public void closeResources() {
        try {
            inputReader.close();
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}