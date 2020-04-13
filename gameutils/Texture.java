package gameutils;

import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 * Write a description of class Texture here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Texture {
    private Image img;
    
    /**
     * Constructor for objects of class Texture
     */
    public Texture(String filePath) {
        try {
            img = ImageIO.read(new File(filePath));
        }
        catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    /**
     * Scales the image
     * @param width the percentage of the width to change
     * @param height the percentage of the height to change
     */
    public void scale(float width, float height) {
        img = img.getScaledInstance((int) (img.getWidth(null) * width),
            (int) (img.getHeight(null) * height), Image.SCALE_DEFAULT);
    }
    
    public int getWidth() {
        return img.getWidth(null);
    }
    
    public int getHeight() {
        return img.getHeight(null);
    }
    
    /**
     * Returns the Image of the texture
     * @return the Image of the texture
     */
    public Image getImage() {
        return img;
    }
}
