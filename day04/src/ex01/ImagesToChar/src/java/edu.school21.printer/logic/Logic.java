package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class Logic {
    public static int[][] parseBmpImage(String fileName, char blackPixel, char whitePixel) throws IOException, IllegalArgumentException {
        int[][] arr2D = null;

        try {
            BufferedImage image = ImageIO.read(Logic.class.getResource(fileName));
            arr2D = new int[image.getWidth()][image.getHeight()];
            for(int xPixel = 0; xPixel < image.getWidth(); xPixel++){
                for(int yPixel = 0; yPixel < image.getHeight(); yPixel++){
                    int color = image.getRGB(xPixel, yPixel);
                    if(color == Color.BLACK.getRGB()){
                        arr2D[xPixel][yPixel] = blackPixel;
                    } else if (color == Color.WHITE.getRGB()) {
                        arr2D[xPixel][yPixel] = whitePixel;
                    }
                }
            }
        } catch (IllegalArgumentException e){
            System.err.println("File not found");
            System.exit(-1);
        }

        return arr2D;
    }
}
