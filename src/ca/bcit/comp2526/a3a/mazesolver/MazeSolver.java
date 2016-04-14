package ca.bcit.comp2526.a3a.mazesolver;

import java.util.ArrayList;

/**
 * MazeSolver.
 *
 * @author BCIT, Jia Qi Lee
 * @version 2016
 */
public class MazeSolver {

    private Maze maze;
    private ArrayList<ArrayList<MazeSection>> mazeSolutions;

    /**
     * Constructor for objects of type MazeSolver.
     * 
     * @param maze the Maze to solve
     */
    public MazeSolver(Maze maze) {
        this.maze = maze;
        mazeSolutions = new ArrayList<>();
    }

    /**
     * Returns the maze as a Maze.
     * 
     * @return maze as a Maze
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * Sets the maze.
     * 
     * @param maze
     *            the maze to set
     */
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Clears the list of solutions.
     */
    public void clear() {
        mazeSolutions.clear();
    }

    /**
     * Solves the maze and returns a List of paths composed of MazeSections.
     * 
     * @return mazeSolutions as an ArrayList of ArrayList of type MazeSection
     * @throws MazeEntryPointException if the maze does not have an entry point at [0][1]
     */
    public ArrayList<ArrayList<MazeSection>> solveMaze() throws MazeEntryPointException {
        clear();
        maze.reset();
        if (maze.getMazeSectionAt(0, 1).isSolid()) {
            throw new MazeEntryPointException("No entry point at 0,1 - Unsolvable");
        }
        generatePaths(maze, 0, 1, new ArrayList<MazeSection>());
        return mazeSolutions;
    }

    /**
     * Generates paths through the maze recursively and adds all solutions to
     * the collection of solutions.
     * 
     * @param maze the Maze to generate paths on
     * @param row the row location
     * @param column the column location
     * @param path the path
     */
    public void generatePaths(Maze maze, int row, int column, ArrayList<MazeSection> path) {      
        if (row < 0 || column < 0 || row > maze.getRows() - 1 || column > maze.getColumns() - 1
                || maze.getMazeSectionAt(row, column).isSolid() 
                || maze.getMazeSectionAt(row, column).hasBeenVisited()) {
            return;             
        } else if (column == maze.getColumns() - 1) {
            path.add(maze.getMazeSectionAt(row, column));
            mazeSolutions.add(path);
        } else {
            maze.getMazeSectionAt(row, column).visit();
            path.add(maze.getMazeSectionAt(row, column));
            generatePaths(maze, row, column - 1, new ArrayList<MazeSection>(path));
            generatePaths(maze, row, column + 1, new ArrayList<MazeSection>(path));
            generatePaths(maze, row - 1, column, new ArrayList<MazeSection>(path));
            generatePaths(maze, row + 1, column, new ArrayList<MazeSection>(path));           
            maze.getMazeSectionAt(row, column).unvisit();
        }        
    }
    
    /**`
     * Returns the index of the shortest path in the collection of paths, or
     * -1 if there is no shortest path, i.e., the maze has no solutions.
     * 
     * @return index as an int
     */
    public int findShortestPath() {
        int shortest = mazeSolutions.get(0).size();
        int index = 0;
        for (int i = 0; i < mazeSolutions.size(); i++) {
            if (mazeSolutions.get(i).size() < shortest) {
                shortest = mazeSolutions.get(i).size();
                index = i;
            }
        }
        return index;
    }
}
