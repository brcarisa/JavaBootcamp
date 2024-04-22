package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Logic {

    private String white;
    private String black;
    private BufferedImage bufferedImage;

    public Logic(String imageFile, CommandLine args) throws IOException{
        this.bufferedImage = ImageIO.read(Logic.class.getResource(imageFile));
        this.white = args.getWhite();
        this.black = args.getBlack();
    }

    public void OutputImage() throws IOException, IllegalArgumentException {
        ColoredPrinter coloredPrinter = new ColoredPrinter();

        try {
            for(int yPixel = 0; yPixel < bufferedImage.getHeight(); yPixel++){
                for(int xPixel = 0; xPixel < bufferedImage.getWidth(); xPixel++){
                    int color = bufferedImage.getRGB(xPixel, yPixel);
                    if(color == Color.BLACK.getRGB()){
                        coloredPrinter.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(black));
                    } else if (color == Color.WHITE.getRGB()) {
                        coloredPrinter.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(white));;
                    }
                }
                System.out.println();
            }
        } catch (IllegalArgumentException e){
            System.err.println("File not found");
            System.exit(-1);
        }

    }
}
