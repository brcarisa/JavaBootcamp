package ex00;

import java.util.Scanner;

public class Program {
    public static int num = 479598;
    public static void main(String[] args) {
        num = Math.abs(num);
        int res = 0;
        while(num > 0){
            res = res + (num % 10);
            num = num / 10;
        }
        System.out.println(res);
    }
}
