package ex02;

import java.util.Scanner;

public class Program {
    public static boolean isNumPrime(int num){
        boolean isNumPrime = true;
        if (num == 2) {
            return true;
        } else {
            for(int i = 2; i * i <= num; i++){
                if(num % i == 0) {
                    isNumPrime = false;
                    break;
                }
            }
        }
        return isNumPrime;
    }
    public static int sumOfNum(int num){
        num = Math.abs(num);
        int res = 0;
        while(num > 0){
            res = res + (num % 10);
            num = num / 10;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isNumPrime = false;
        int number = scanner.nextInt();
        int sumDigits = 0;
        int amountOfCoffee = 0;
        while (number != 42){
            sumDigits = sumOfNum(number);
            isNumPrime = isNumPrime(sumDigits);
            if(isNumPrime) amountOfCoffee++;
            number = scanner.nextInt();
        }
        System.out.println("Count of coffee-request – " + amountOfCoffee);
    }
}
