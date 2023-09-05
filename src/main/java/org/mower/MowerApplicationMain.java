package org.mower;

import java.io.IOException;
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
            ProcessIO processIO = new ProcessIO(inputFilePath, outputFilePath);
            List<String> finalPositions = processIO.processInputAndSaveOutput();
            processIO.closeResources();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}