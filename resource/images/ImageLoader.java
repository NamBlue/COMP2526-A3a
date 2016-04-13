package images;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

/**
 * Loader for image resources.
 * 
 * @author Jia Qi Lee
 * @version 1.0
 */
public class ImageLoader {
    private static ImageLoader imageLoader = new ImageLoader();
    //All URI for images are added here
    private static final String[] walls = {"wall1.png", "wall2.png", "wall3.png" , "wall4.png", "wall5.png"};
    private static final String[] paths = {"path1.png"};
    
    /**
     * Loads the image.
     * @param name the URI of the image file
     * @return the image
     */
    public static Image getImage(String name) {        
        return Toolkit.getDefaultToolkit().getImage(
                imageLoader.getClass().getResource(name));       
    }
    
    /**
     * Loads a random Wall Image chosen from the array of URI's.
     * @return the image randomly selected
     */
    public static Image getWall() {
        Random gen = new Random();
        int seed = 0;
        if (gen.nextInt(100) < 50) {
            seed = gen.nextInt(walls.length);
        }
        
        return Toolkit.getDefaultToolkit().getImage(
                imageLoader.getClass().getResource(walls[seed]));
    }
    
    /**
     * Loads a random Path Image chosen from the array of URI's.
     * @return the image randomly selected
     */
    public static Image getPath() {
        Random gen = new Random();
        int seed = gen.nextInt(paths.length);
        
        return Toolkit.getDefaultToolkit().getImage(
                imageLoader.getClass().getResource(paths[seed]));
    }
}
