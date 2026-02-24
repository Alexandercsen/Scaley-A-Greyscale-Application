import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *@author: Alexander
 * @version: 2.0
 * <p>
 *     Opens file choosing dialog that allows user to select an image file
 *     The selected image read and converts into a regular and weighted grayscale using two seperate methods.
 *     processed images are written to root folder of the application. Works on Win, MacOS and Linux.
 * </p>
 */

public class Main {

    public static BufferedImage toGrayScale2(BufferedImage img) {
        System.out.println(" Converting to GrayScale2");

        BufferedImage grayImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        int rgb=0,r=0,g=0,b=0;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                rgb = (int)(img.getRGB(x,y));
                r = ((rgb >> 16) & 0xFF);
                g = ((rgb >> 8) & 0xFF);
                b = (rgb & 0xFF);
                rgb = (int)((r+g+b)/3);
                rgb = (255<<24) | (rgb<<16) | (rgb<<8) | rgb;
                grayImage.setRGB(x,y,rgb);
            }
        }
        return grayImage;
    }

    public static BufferedImage toGrayScale3(BufferedImage img) {
        System.out.println(" Converting to GrayScale3");

        BufferedImage grayImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        int rgb=0,r=0,g=0,b=0;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                rgb = (int)(img.getRGB(x,y));
                r = ((rgb >> 16) & 0xFF);
                g = ((rgb >> 8) & 0xFF);
                b = (rgb & 0xFF);
                rgb = (int)(0.299*r + 0.587*g + 0.114*b);
                rgb = (255<<24) | (rgb<<16) | (rgb<<8) | rgb;
                grayImage.setRGB(x,y,rgb);
            }
        }
        return grayImage;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            try{
                BufferedImage img = ImageIO.read(file);
                BufferedImage gray2 = toGrayScale2(img);
                BufferedImage gray3 = toGrayScale3(img);
                String name = file.getName();
                String extension = name.substring(name.lastIndexOf(".")+1);
                ImageIO.write(gray2, extension, new File("output_gray2." + extension));
                ImageIO.write(gray3, extension, new File("output_gray3." + extension));
                System.out.println("Finished");
            }catch(IOException e){
                e.printStackTrace();
            }


        }


    }
}