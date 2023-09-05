# Seat Mower Kata

## Overview

This project implements a solution for controlling robotic mowers on a rectangular plateau. The mowers are tasked with cutting grass and sending terrain information to the SEAT Maintenance Office.

## Usage

1. **Compile the Project**: Ensure you have Java and Maven installed. Compile the project using the following command:

   ```shell
   mvn clean package
   ```

2. **Run the Application**: Execute the JAR file with two command-line arguments: the name of the input file (input.txt) and the name of the output file (output.txt).

```shell
java -jar target/Seat-mower-kata-1.0.jar input.txt output.txt
```
Make sure to replace input.txt and output.txt with your desired file names.

3. **Input File**: Create an input file (e.g., input.txt) with the following format:
```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```
The first line specifies the upper-right coordinates of the plateau. Each subsequent pair of lines represents a mower's initial position and instructions.

4. **Output File**: After running the application, the final mower positions will be written to the specified output file (e.g., output.txt).

## Explanation

This project follows the SEAT:CODE Backend Developer Technical Challenge, where robotic mowers navigate a rectangular plateau and execute instructions provided in an input file. Key components include:

- **Plateau**: Represents the rectangular terrain where mowers operate. It checks if a mower is within its bounds.

- **Position**: Represents a mower's position and orientation. It handles movements and turns while ensuring the mower stays within the plateau's bounds.

- **Mower**: Represents a robotic mower and its associated position. It executes instructions to move and turn.

- **MowerController**: Manages multiple mowers on the plateau. It deploys mowers, executes their instructions, and retrieves their final positions.

- **Input and Output**: Instructions are read from an input file, and the final mower positions are written to an output file.