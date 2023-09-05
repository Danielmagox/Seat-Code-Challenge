package org.mower;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ProcessIOTest {
    private static final String INPUT_FILE = "input_test.txt";
    private static final String OUTPUT_FILE = "output_test.txt";

    private ProcessIO processIO;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a sample input file for testing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INPUT_FILE))) {
            writer.write("5 5\n");
            writer.write("1 2 N\n");
            writer.write("LMLMLMLMM\n");
            writer.write("3 3 E\n");
            writer.write("MMRMMRMRRM\n");
        }

        processIO = new ProcessIO(INPUT_FILE, OUTPUT_FILE);
    }

    @AfterEach
    public void tearDown() {
        processIO.closeResources();
        File inputFile = new File(INPUT_FILE);
        File outputFile = new File(OUTPUT_FILE);
        inputFile.delete();
        outputFile.delete();
    }


    @Test
    public void testInvalidInputFormat() throws IOException {
        // Add an invalid mower position format to the input
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INPUT_FILE, true))) {
            writer.write("InvalidMowerPosition\n");
        }

        assertThrows(IllegalArgumentException.class, () -> processIO.processInputAndSaveOutput());
    }

    @Test
    public void testInvalidCoordinates() throws IOException {
        // Add an invalid mower coordinates to the input
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INPUT_FILE, true))) {
            writer.write("6 6 N\n"); // Coordinates outside the plateau
            writer.write("1 1 N\n");
            writer.write("MM\n");
        }

        assertThrows(IllegalArgumentException.class, () -> processIO.processInputAndSaveOutput());
    }

    @Test
    public void testInvalidFinalPositionFormat() throws IOException {
        // Add an invalid final position format to the input
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INPUT_FILE, true))) {
            writer.write("1 1 N\n");
            writer.write("MM\n");
            writer.write("InvalidFinalPosition\n");
        }

        assertThrows(IllegalArgumentException.class, () -> processIO.processInputAndSaveOutput());
    }
}