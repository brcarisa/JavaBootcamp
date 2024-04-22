package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        if(args.length != 3){
            System.err.println("Invalid count of arguments");
            System.exit(-1);
        }
        char whiteSymbol = args[0].charAt(0);
        char blackSymbol = args[1].charAt(0);
        String fileName = args[2];

        int[][] image2D = Logic.parseBmpImage(fileName, blackSymbol, whiteSymbol);
        for(int xPixels = 0; xPixels < image2D.length; xPixels++){
            for (int yPixels = 0; yPixels < image2D[xPixels].length; yPixels++) {
                System.out.print((char) image2D[yPixels][xPixels]);
            }
            System.out.println();
        }
    }
}
