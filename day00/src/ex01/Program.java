package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        if(num <= 1){
            System.err.println("Illegal Argument");
            System.exit(-1);
        } else if (num == 2) {
            System.out.println(true + " " + 1);
        } else {
            int counts = 1;
            boolean isNumPrime = true;
            for(int i = 2; i * i <= num; i++){
                if(num % i == 0){
                    isNumPrime = false;
                    break;
                }
                counts++;
            }
            System.out.println(isNumPrime + " " + counts);
        }
        scanner.close();
    }
}
