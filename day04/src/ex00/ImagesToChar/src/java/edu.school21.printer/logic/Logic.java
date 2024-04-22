package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Logic {
    public static int[][] parseBmpImage(String fileName, char blackPixel, char whitePixel) throws IOException {
        BufferedImage image = ImageIO.read(Files.newInputStream(Paths.get(fileName)));
        int[][] arr2D = new int[image.getWidth()][image.getHeight()];
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
        return arr2D;
    }
}
