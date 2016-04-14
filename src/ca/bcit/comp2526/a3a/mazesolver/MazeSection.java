package ca.bcit.comp2526.a3a.mazesolver;

import images.ImageLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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
    private Image image;

    /**
     * Constructor for objects of type MazeSection.
     * 
     * @param row the row location of the MazeSection
     * @param column the column location of the MazeSection
     * @param isSolid MazeSection is solid or not
     */
    public MazeSection(int row, int column, boolean isSolid) {
        location = new Point(column, row);
        setSolid(isSolid);
        color = Color.RED;
        image = ImageLoader.getWall();
        setLayout(new GridLayout(1, 1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new MouseListener());
    }

    /**
     * Initializes this maze section.
     */
    public void init() {
        setSolid(false);
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
            image = ImageLoader.getWall();
        } else {
            setColour(Color.WHITE);
            image = ImageLoader.getImage("path1.png");
        }
        repaint();
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
     * Sets the image of this maze section.
     * 
     * @param string the URI of the image.
     */
    public void setImage(String string) {
        image = ImageLoader.getImage(string);
        repaint();
    }
    
    /**
     * Draws the cell.
     * @param draw device context for the Panel to draw on
     */
    public void paintComponent(Graphics draw) {
        super.paintComponent(draw);
        draw.setColor(color);
        draw.fillRect(0, 0, getWidth(), getHeight());
        draw.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        
    }
    
    private class MouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            if (isSolid) {
                setSolid(false);
            } else {
                setSolid(true);
            }
        }
    }
}
