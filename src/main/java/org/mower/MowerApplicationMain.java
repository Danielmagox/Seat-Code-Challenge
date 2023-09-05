package org.mower;

import java.io.*;
import java.util.List;

public class MowerApplicationMain {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java MowerApplication inputFilePath outputFilePath");
            return;
        }
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try {
            List<String> finalPositions = processInput(inputFilePath);
            saveOutput(outputFilePath, finalPositions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> processInput(String inputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line = reader.readLine();

            // Read plateau dimensions
            String[] plateauDimensions = line.split(" ");
            int upperRightX = Integer.parseInt(plateauDimensions[0]);
            int upperRightY = Integer.parseInt(plateauDimensions[1]);
            Plateau plateau = new Plateau(upperRightX, upperRightY);

            // Create MowerController
            MowerController controller = new MowerController(plateau);

            // Process mowers
            String mowerLine;
            while ((mowerLine = reader.readLine()) != null) {
                int x = Integer.parseInt(mowerLine.split(" ")[0]);
                int y = Integer.parseInt(mowerLine.split(" ")[1]);
                char orientation = mowerLine.split(" ")[2].charAt(0);
                String instructions = reader.readLine();

                // Deploy mower and execute instructions
                controller.deployMower(x, y, orientation, instructions);
            }

            return controller.getFinalMowerPositions();
        }
    }

    private static void saveOutput(String outputFilePath, List<String> finalPositions) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String position : finalPositions) {
                System.out.println(position);
                bufferedWriter.write(position);
                bufferedWriter.newLine();
            }
        }
    }
}