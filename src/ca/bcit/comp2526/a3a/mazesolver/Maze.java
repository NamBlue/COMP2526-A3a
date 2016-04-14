package ca.bcit.comp2526.a3a.mazesolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Maze.
 *
 * @author BCIT, Jia Qi Lee
 * @version 2016
 */
public class Maze {
    private final MazeSection[][] mazeSections;
    private final Random random;
    private final int rows;
    private final int columns;
    private Scanner scan;
    private MazeSolver mazeSolver; //for generating random mazes

    /**
     * Constructor for objects of type Maze.
     * 
     * @param rows the number of rows of this Maze
     * @param columns the number of columns of this Maze
     */
    public Maze(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException(
                    "Parameters cannot be negative or zero");
        }
        this.rows = rows;
        this.columns = columns;
        random = new Random();
        mazeSections = new MazeSection[rows][columns];
    }

    /**
     * Initializes a new empty maze.
     */
    public void init(MazeSolver mazesolver) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mazeSections[i][j] = new MazeSection(i, j, false);
                mazeSections[i][j].init();
            }
        }
        mazeSolver = mazesolver;
    }

    /**
     * Eliminates all walls from the maze and 'unvisits' each maze section,
     * effectively wiping it clean.
     */
    public void clear() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mazeSections[i][j].setSolid(false);
                mazeSections[i][j].unvisit();
            }
        }
    }
    
    /**
     * Fills the maze with walls for random generation
     */
    public void genFill() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows && i % 2 == 0; j++) {
                mazeSections[j][i].setSolid(true);
            }
        }
    }
    
    /**
     * Fills the maze with walls.
     */
    public void fill() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mazeSections[i][j].setSolid(true);
            }
        }
    }

    /**
     * Makes the specified MazeSection solid.
     * 
     * @param section
     *            a maze
     */
    @SuppressWarnings("unused")
    private void makeSolid(MazeSection section) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mazeSections[i][j] == section) {
                    mazeSections[i][j].setSolid(true);
                }
            }
        }
    }

    /**
     * Makes the specified MazeSection navigable.
     * 
     * @param section
     *            a maze
     */
    private void makeNavigable(MazeSection section) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mazeSections[i][j] == section) {
                    mazeSections[i][j].setSolid(false);
                }
            }
        }
    }

    /**
     * Gets the number of rows.
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns.
     * @return the number of columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Returns a reference to the MazeSection at the specified coordinates.
     * 
     * @param row the row target MazeSection is at
     * @param column the column target MazeSection is at
     * @return location in maze as a MazeSection
     */
    public MazeSection getMazeSectionAt(int row, int column) {
        return mazeSections[row][column];
    }

    /**
     * Generates a random maze.
     */
    public void generateRandomMaze() {
        clear();
        genFill();
        //First create a map with a large amount of solutions
        final int two = 2;
        for (int i = 0; i < columns; i += two) {  
            //number of openings for each column
            int openings = random.nextInt(two) + 1;
            for (int h = 0; h < openings; h++) {
                int seed = random.nextInt(rows);
                for (int j = 0; j < rows; j++) {
                    if (j == seed) {
                        mazeSections[j][i].setSolid(false);
                    }
                }
            }
        }
        //Create a list of solutions from that map
        try {
            ArrayList<ArrayList<MazeSection>> list = mazeSolver.solveMaze();
            fill();
            //Create the map based on the solutions
            for (int i = 0; i < two; i++) {
                int seed = random.nextInt(list.size());
                for (int j = 0; j < list.get(seed).size(); j++) {
                    makeNavigable(list.get(seed).get(j));
                }
            }
        } catch (Exception e) {
            
        }
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
        int row = 0;
        int col = 0;
        String buffer;
        scan = new Scanner(file);
        scan.useDelimiter("");
        while (scan.hasNext()) {
            buffer = scan.next();
            System.out.print(buffer);
            if (buffer.equals("*")) {
                mazeSections[row][col].setSolid(true);
                col++;
            } else if (buffer.equals("\n")) {
                col = 0;
                row ++;
            } else {
                mazeSections[row][col].setSolid(false);
                col++;
            }
        }        
    }

    /**
     * Resets the maze by 'unvisiting' all visited maze sections
     * and resetting the path color.
     */
    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!mazeSections[i][j].isSolid()) {
                    mazeSections[i][j].unvisit(); 
                    mazeSections[i][j].setImage("path1.png");
                }
            }
        }
    }
}
