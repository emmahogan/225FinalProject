package gameutils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

import sun.tools.jstat.Scale;

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

    public void rotate(double angle) {
        BufferedImage temp = this.img;
        int w = this.img.getWidth();
        int h = this.img.getHeight();

        this.img = new BufferedImage(w, h, temp.getType());
        Graphics2D graphic = this.img.createGraphics();
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawImage(temp, null, 0, 0);
        graphic.dispose();
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
