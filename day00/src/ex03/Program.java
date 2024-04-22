package ex03;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        String string;
        long grades = 0;
        long pow;
        Scanner scanner = new Scanner(System.in);
        for(int weeks = 1; weeks <= 18; weeks++){
            string = scanner.next();
            if(string.equals("42")) break;
            else if(!string.equals("Week")){
                System.err.println("Illegal argument");
                scanner.close();
                System.exit(-1);
            }
            if(weeks != scanner.nextInt()){
                System.err.println("Illegal argument");
                scanner.close();
                System.exit(-1);
            }
            pow = 1;
            for(int i = 1; i < weeks; i++){
                pow = pow * 10;
            }
            grades = grades + (pow * getMinGradeOfWeek(scanner));
        }
        scanner.close();
        printData(grades);
    }

    public static int getMinGradeOfWeek(Scanner scanner){
        int min = 10;
        int i = 0;
        int buff;
        while (i < 5){
            if(scanner.hasNextInt()){
                buff = scanner.nextInt();
                if(buff < 1 || buff > 9){
                    System.err.println("Illegal argument");
                    scanner.close();
                    System.exit(-1);
                }
                if (min > buff){
                    min = buff;
                }
            } else {
                System.err.println("Illegal argument");
                scanner.close();
                System.exit(-1);
            }
            i++;
        }
        return min;
    }
    public static void printData(long grades){
        long grade;
        int weeks = 1;
        while (grades != 0) {
            grade = grades % 10;
            System.out.print("Week " + weeks);
            while(grade != 0){
                System.out.print("=");
                grade--;
            }
            System.out.println(">");
            grades = grades / 10;
            weeks++;
        }
    }
}

