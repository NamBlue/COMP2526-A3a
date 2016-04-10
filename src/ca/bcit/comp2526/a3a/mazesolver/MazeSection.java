package ca.bcit.comp2526.a3a.mazesolver;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * MazeSection.
 *
 * @author BCIT
 * @version 2016
 */
@SuppressWarnings("serial")
public class MazeSection extends JPanel {
    private final Point location;
    private boolean isSolid, visited;

    /**
     * Constructor for objects of type MazeSection.
     * 
     * @param maze
     * @param row
     * @param column
     * @param isSolid
     */
    public MazeSection(int row, int column, boolean isSolid) {
        // TODO your code goes here
    }

    /**
     * Initializes this maze section.
     */
    public void init() {
        // TODO your code goes here
    }

    /**
     * @return true if this maze section is solid, else false
     */
    public boolean isSolid() {
        // TODO your code goes here
    }

    /**
     * Sets whether this maze section is solid.
     * 
     * @param isSolid
     *            a boolean
     */
    public void setSolid(boolean isSolid) {
        // TODO your code goes here
    }

    /**
     * Visits this maze section.
     */
    public void visit() {
        // TODO your code goes here
    }

    /**
     * "Unvisits" this maze section.
     */
    public void unvisit() {
        // TODO your code goes here
    }

    /**
     * @return true if this maze has been visited, else false
     */
    public boolean hasBeenVisited() {
        // TODO your code goes here
    }

    /**
     * @return the location of this MazeSection as a Point
     */
    public Point getLocation() {
        // TODO your code goes here
    }

    /**
     * Sets the colour of this maze section.
     * 
     * @param color
     */
    public void setColour(Color color) {
        // TODO your code goes here
    }
}
