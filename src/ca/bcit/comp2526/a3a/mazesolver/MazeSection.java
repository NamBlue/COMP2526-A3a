package ca.bcit.comp2526.a3a.mazesolver;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * MazeSection.
 *
 * @author BCIT, Jia Qi Lee
 * @version 2016
 */
@SuppressWarnings("serial")
public class MazeSection extends JPanel {
    private final Point location;
    private boolean isSolid;
    private boolean visited;
    private Color color;

    /**
     * Constructor for objects of type MazeSection.
     * 
     * @param row the row location of the MazeSection
     * @param column the column location of the MazeSection
     * @param isSolid MazeSection is solid or not
     */
    public MazeSection(int row, int column, boolean isSolid) {
        location = new Point(column, row);
        this.isSolid = isSolid;
        color = Color.RED;
        setLayout(new GridLayout(1, 1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new MouseListener());
    }

    /**
     * Initializes this maze section.
     */
    public void init() {
        isSolid = false;
    }

    /**
     * Checks if this maze section is solid or not.
     * @return true if this maze section is solid, else false
     */
    public boolean isSolid() {
        return isSolid;
    }

    /**
     * Sets whether this maze section is solid.
     * 
     * @param isSolid
     *            a boolean
     */
    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
        if (isSolid) {
            setColour(Color.RED);
        } else {
            setColour(Color.WHITE);
        }
    }

    /**
     * Visits this maze section.
     */
    public void visit() {
        visited = true;
    }

    /**
     * "Unvisits" this maze section.
     */
    public void unvisit() {
        visited = false;
        setColour(Color.white);
    }

    /**
     * Checks if this MazeSection has been visited or not.
     * @return true if this maze has been visited, else false
     */
    public boolean hasBeenVisited() {
        return visited;
    }

    /**
     * Gets the location of this MazeSection as a Point.
     * @return the location of this MazeSection as a Point
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Sets the colour of this maze section.
     * 
     * @param color the color of this maze section.
     */
    public void setColour(Color color) {
        this.color = color;
    }
    
    /**
     * Draws the cell.
     * @param draw device context for the Panel to draw on
     */
    public void paintComponent(Graphics draw) {
        super.paintComponent(draw);
        draw.setColor(color);
        draw.fillRect(0, 0, getWidth(), getHeight());
    }
    
    private class MouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (isSolid) {
                setSolid(false);
            } else {
                setSolid(true);
            }
            repaint();
        }
    }
}
