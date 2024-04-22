package ex04;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static void drawHistogram(char[] resListChars, int[] resListCounts){
        System.out.println();
        for(int i = 0; i < 10; i++){
            if(resListCounts[i] * 10 / resListCounts[0] == 10)
                System.out.print(resListCounts[i] + " ");
        }
        System.out.println();
        for(int i = 10; i > 0; i--){
            for(int j = 0; j < 10; j++){
                if(resListCounts[j] * 10 / resListCounts[0] >= i) System.out.print("# ");
                if(resListCounts[j] * 10 / resListCounts[0] == i - 1){
                    if(resListCounts[j] != 0)
                        System.out.print(resListCounts[j] + " ");
                }
            }
            System.out.println();
        }
        for(int i = 0; i < 10; i++){
            System.out.print(resListChars[i]+ " ");
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()){
            String inputLine = scanner.nextLine();
            char[] inputLineChars = inputLine.toCharArray();
            int[] charCount = new int[65536];
            char[] resListChars = new char[10];
            int[] resListCounts = new int[10];
            char maxChar = ' ';
            int maxCount = 0;
            int index = 0;
            for(int i = 0; i < inputLine.length(); i++){
                charCount[inputLineChars[i]]++;
            }
            for(int j = 0; j < 10; j++){
                for(int i = 0; i < 65536; i++){
                    if(charCount[i] > maxCount){
                        maxCount = charCount[i];
                        maxChar = (char) i;
                        index = i;
                    }
                }
                resListChars[j] = maxChar;
                resListCounts[j] = charCount[index];
                charCount[index] = 0;
                maxChar = ' ';
                maxCount = 0;
            }
            if(resListCounts[0] > 999){
                System.err.println("Illegal argument");
                scanner.close();
                System.exit(-1);
            }
            drawHistogram(resListChars, resListCounts);
            scanner.close();
            System.out.println();
        } else {
            System.err.println("Illegal argument");
            scanner.close();
            System.exit(-1);
        }
    }
}
