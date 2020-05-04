package gameutils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
    private BufferedImage img;
    private String filePath;
    
    /**
     * Constructor for objects of class Texture
     */
    public Texture(String filePath) {
        try {
            this.filePath = filePath;
            img = ImageIO.read(new File(filePath));
        }
        catch (IOException e) {
            System.out.println("File not found!");
        }
    }
    
    public int getWidth() {
        return img.getWidth();
    }
    
    public int getHeight() {
        return img.getHeight();
    }
    
    /**
     * Returns the Image of the texture
     * @return the Image of the texture
     */
    public Image getImage() {
        return img;
    }

    /**
     * Scales the image
     * @param width the percentage of the width to change
     * @param height the percentage of the height to change
     */
    public void scale(double width, double height) {
        img = convertToBufferedImage(img.getScaledInstance(
                (int) (img.getWidth(null) * width),
                (int) (img.getHeight(null) * height),
                Image.SCALE_DEFAULT));
    }

    /**
     * Converts an Image to a BufferedImage
     * @param image the image to be converted
     * @return the new BufferedImage
     */
    public BufferedImage convertToBufferedImage(Image image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(null),
                image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }
}
