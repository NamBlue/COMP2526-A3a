package ca.bcit.comp2526.a3a.mazesolver;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Maze.
 *
 * @author BCIT
 * @version 2016
 */
public class Maze {
    private final MazeSection[][] mazeSections;
    private final Random random;
    private Scanner scan;

    /**
     * Constructor for objects of type Maze.
     * 
     * @param rows
     * @param columns
     */
    public Maze(int rows, int columns) {
        // TODO your code goes here
    }

    /**
     * Initializes a new empty maze.
     */
    public void init() {
        // TODO your code goes here
    }

    /**
     * Eliminates all walls from the maze and 'unvisits' each maze section,
     * effectively wiping it clean.
     */
    public void clear() {
        // TODO your code goes here
    }

    /**
     * Makes the specified MazeSection solid.
     * 
     * @param section
     *            a maze
     */
    private void makeSolid(MazeSection section) {
        // TODO your code goes here
    }

    /**
     * Makes the specified MazeSection navigable.
     * 
     * @param section
     *            a maze
     */
    private void makeNavigable(MazeSection section) {
        // TODO your code goes here
    }

    /**
     * @return the number of rows
     */
    public int getRows() {
        // TODO your code goes here
    }

    /**
     * @return the number of columns
     */
    public int getColumns() {
        // TODO your code goes here
    }

    /**
     * Returns a reference to the MazeSection at the specified coordinates.
     * 
     * @param row
     * @param column
     * @return location in maze as a MazeSection
     */
    public MazeSection getMazeSectionAt(int row, int column) {
        // TODO your code goes here
    }

    /**
     * Generates a (terrible) random maze.
     */
    public void generateRandomMaze() {
        // TODO your code goes here
    }

    /**
     * Generates a maze from the specified file. The file must be a .txt file
     * which contains MAZE_DIMENSION lines of MAZE_DIMENSION characters each.
     * There are two characters: * delineates a solid section, and anything else
     * delineates a navigable section. The entry to the maze must be at location
     * [0][1] and the (possibly multiple) exit(s) to the maze must be on the
     * right edge (rightmost column) of the maze [0...24][24]
     * 
     * @param file
     *            that contains the maze
     */
    public void generateMazeFromFile(File file) throws IOException {
        // TODO your code goes here
    }

    /**
     * Resets the maze by 'unvisiting' all visited maze sections
     * and resetting their colour to white.
     */
    public void reset() {
        // TODO your code goes here
    }
}
