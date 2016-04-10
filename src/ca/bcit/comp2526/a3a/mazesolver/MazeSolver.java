package ca.bcit.comp2526.a3a.mazesolver;

import java.util.ArrayList;

/**
 * MazeSolver.
 *
 * @author BCIT
 * @version 2016
 */
public class MazeSolver {

    private Maze maze;
    private ArrayList<ArrayList<MazeSection>> mazeSolutions;

    /**
     * Constructor for objects of type MazeSolver.
     * 
     * @param maze
     * @param frame
     */
    public MazeSolver(Maze maze) {
        // TODO your code goes here
    }

    /**
     * Returns the maze as a Maze.
     * 
     * @return maze as a Maze
     */
    public Maze getMaze() {
        // TODO your code goes here
    }

    /**
     * Sets the maze.
     * 
     * @param maze
     *            the maze to set
     */
    public void setMaze(Maze maze) {
        // TODO your code goes here
    }

    /**
     * Clears the list of solutions.
     */
    public void clear() {
        // TODO your code goes here
    }

    /**
     * Solves the maze and returns a List of paths composed of MazeSections.
     * 
     * @param solutions
     *            as an int
     * @return mazeSolutions as an ArrayList<ArrayList<MazeSection>>
     * @throws MazeEntryPointException if the maze does not have an entry point at [0][1]
     */
    public ArrayList<ArrayList<MazeSection>> solveMaze() throws MazeEntryPointException {
        // TODO your code goes here
    }

    /**
     * Generates paths through the maze recursively and adds all solutions to
     * the collection of solutions.
     * 
     * @param maze
     * @param row
     * @param column
     * @param path
     */
    public void generatePaths(Maze maze, int row, int column, ArrayList<MazeSection> path) {
        // TODO your code goes here
    }
    
    /**
     * Returns the index of the shortest path in the collection of paths, or
     * -1 if there is no shortest path, i.e., the maze has no solutions.
     * 
     * @param paths
     * @return index as an int
     */
    public int findShortestPath() {
        // TODO your code goes here
    }
}
