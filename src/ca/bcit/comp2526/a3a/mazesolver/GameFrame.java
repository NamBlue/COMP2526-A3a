package ca.bcit.comp2526.a3a.mazesolver;

import sounds.SoundLoader;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * GameFrame.
 *
 * @author BCIT, Jia Qi Lee
 * @version 2016
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame implements ActionListener {

    public static final int DELAY = 25;

    private final Maze maze;
    private final MazeSolver mazeSolver;
    private JMenuBar menuBar;
    private JMenu fileMenu, mazeMenu;
    private JMenuItem menuItemOpenFile, menuItemGenerateRandom, menuItemSolveMaze;
    private int choice; //Stores users choice for keyboard input

    /**
     * Constructor for objects of type GameFrame.
     * 
     * @param maze the Maze for this GameFrame to display
     * @param mazeSolver the MazeSolver for this GameFrame to use
     */
    public GameFrame(final Maze maze, final MazeSolver mazeSolver) {
        this.maze = maze;
        this.mazeSolver = mazeSolver;
        addKeyListener(new KeyBoardInput());
    }

    /**
     * Initializes this GameFrame. Sets the title, constructs and sets the menu
     * bar, constructs a new empty maze.
     */
    public void init() {
        final int delay = 1000;
        setTitle("Assignment 3a");
        setJMenuBar(constructMenuBar());
        setLayout(new GridLayout(this.maze.getRows(), this.maze.getColumns()));
        for (int i = 0; i < this.maze.getRows(); i++) {
            for (int j = 0; j < this.maze.getColumns(); j++) {
                add(this.maze.getMazeSectionAt(i, j));
            }
        }
        SoundLoader.bgm.play();
        //adder scheduled event to fix GameFrame components not displaying properly
        new java.util.Timer().schedule( 
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        generateRandomMaze();
                    }
                }, delay);        
    }

    /**
     * Constructs and returns the JMenu Bar.
     * 
     * @return menuBar a JMenuBar
     */
    private JMenuBar constructMenuBar() {
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        menuItemOpenFile = new JMenuItem("(1)Open file...");
        menuItemOpenFile.addActionListener(this);
        fileMenu.add(menuItemOpenFile);
        menuItemGenerateRandom = new JMenuItem("(2)Generate random maze");
        menuItemGenerateRandom.addActionListener(this);
        fileMenu.add(menuItemGenerateRandom);
        menuBar.add(fileMenu);

        mazeMenu = new JMenu("Maze");
        menuItemSolveMaze = new JMenuItem("(3)Solve Maze");
        menuItemSolveMaze.addActionListener(this);
        mazeMenu.add(menuItemSolveMaze);
        menuBar.add(mazeMenu);
        return menuBar;
    }

    /**
     * Responds to the user interface.
     * 
     * @param e
     *            an ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(menuItemGenerateRandom)) {
            generateRandomMaze();
        } else if (e.getSource().equals(menuItemOpenFile)) {
            generateMazeFromFile();
        } else if (e.getSource().equals(menuItemSolveMaze)) {
            generateMazeSolutions();
        }
        repaint();
    }

    /**
     * Generates a (terrible) random maze.
     */
    public void generateRandomMaze() {
        maze.generateRandomMaze();
    }

    /**
     * Generates a maze based on a maze file selected by the user using a
     * file chooser dialog.
     */
    public void generateMazeFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                maze.clear();
                maze.generateMazeFromFile(file);
                mazeSolver.setMaze(maze);
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(this, "That file cannot be opened.");
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(this, "That file is not a correctly-formatted maze.");
                maze.clear();
            } finally {
                repaint();
            }
        }
    }

    /**
     * Generates solutions to the maze.
     */
    public void generateMazeSolutions() {
        try {
            maze.reset();
            repaint();
            ArrayList<ArrayList<MazeSection>> solutions = mazeSolver.solveMaze();
            displayMazeSolutions(solutions);
        } catch (MazeEntryPointException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Displays the maze solutions. All solutions are displayed in grey. The
     * shortest solution is displayed in dark grey.
     * 
     * @param solutions
     */
    public void displayMazeSolutions(final ArrayList<ArrayList<MazeSection>> solutions) {
        int numberOfSolutions = solutions.size();
        JOptionPane.showMessageDialog(this,
                "This maze has " + numberOfSolutions + (numberOfSolutions != 1 ? " solutions" : " solution"));

        if (numberOfSolutions > 0) {
            int indexOfShortestSolution = mazeSolver.findShortestPath();
            Timer myTimer = new Timer(DELAY, new ActionListener() {
                int solution = 0;
                int step = 0;
                int shortestSolutionStep = 0;

                public void actionPerformed(ActionEvent e) {
                    if (solution < numberOfSolutions) {
                        ArrayList<MazeSection> aSolution = solutions.get(solution);
                        if (step < aSolution.size()) {
                            aSolution.get(step++).setImage("path2.png");
                        } else {
                            maze.reset();
                            step = 0;
                            solution++;
                        }
                    } else {
                        ArrayList<MazeSection> bestSolution = solutions.get(indexOfShortestSolution);
                        if (shortestSolutionStep < bestSolution.size()) {
                            bestSolution.get(shortestSolutionStep++).setImage("path3.png");
                        } else {
                            ((Timer) e.getSource()).stop();
                        }
                    }
                    repaint();
                }
            });
            myTimer.start();
        }
    }
    /**
     * Invokes the appropriate method on the addressBook. When the user makes
     * their selection the Keyboard listener stores the selection value in data
     * member "choice" and then calls this method.
     */
    private void evaluateChoice() {
        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;

        switch (choice) {
          case one:
              generateMazeFromFile();
              break;
          case two:
              generateRandomMaze();
              break;
          case three:
              generateMazeSolutions();
              break;
          case four:
              System.exit(0);
              break;
          default:
            // should not get here
        }
    }
    
    /**
     * KeyBoardInput.
     * A private (no one else needs access to this class) inner class (this
     * class needs access to the GUI to handle user selections) that listens for
     * keys pressed.
     *
     */
    private class KeyBoardInput extends KeyAdapter {

        /**
         * Responds when a key is pressed on the keyboard.
         * 
         * @param event
         *            KeyEvent - key pressed and other information
         */
        public void keyTyped(KeyEvent event) {
            // set the "choice" data member of the outer class GUI
            // to get the integer value, get the character value of the key
            // pressed, make it a string and ask the Integer class to parse it
            try {
                choice = Integer.parseInt("" + event.getKeyChar());
                // if it wasn't an integer key pressed then make an invalid
                // choice
            } catch (Exception except) {
                choice = -1;// this will result in nothing happening
            }
            evaluateChoice(); // GUI method to call the addressBook to perform
                              // task
        }
    }
}
