package ex05;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static final String[] week = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] listStudents = new String[10];
        String[] classesTime = new String[50];
        int countClasses = listStudents.length * classesTime.length;
        String[] scheduleList = new String[countClasses];

        fillStudents(scanner, listStudents);
        int sumClasses =  fillClassesAndTime(scanner, classesTime);
        int sumVisits = fillSchedule(scanner, scheduleList, countClasses);

        printHeaderLine(classesTime, sumClasses);
        printBottom(classesTime, sumClasses,scheduleList, sumVisits);
    }

    public static int fillStudents(Scanner scanner, String[] listStudents){
        int StudentsAmount = 0;
        String buff = "";
        while(true){
            buff = scanner.nextLine();
            if (buff.equals(".") || StudentsAmount == 10) break;
            listStudents[StudentsAmount] = buff;
            StudentsAmount++;
        }
        return StudentsAmount;
    }

    public static int fillClassesAndTime(Scanner scanner, String[] classesTime){
        int sumClasses = 0;
        String buff = "";
        while(true){
            buff = scanner.nextLine();
            if (buff.equals(".") || sumClasses == 50) break;
            classesTime[sumClasses] = buff;
            sumClasses++;
        }
        return sumClasses;
    }

    public static int fillSchedule(Scanner scanner, String[] scheduleList, int countClasses){
        int sumVisits = 0;
        String buff = "";
        while(true){
            buff = scanner.nextLine();
            if (buff.equals(".") || sumVisits == countClasses) break;
            scheduleList[sumVisits] = buff;
            sumVisits++;
        }
        return sumVisits;
    }

    public static String returnString(String str, int start, int end){
        char[] src = str.toCharArray();
        char[] buff = new char[end - start];
        for (int i = start, j = 0; i < end; i++, j++){
            buff[j] = src[i];
        }
        return new String(buff);
    }

    public static int findEndIndex(String str, int start){
        int res = 0;
        char[] chars = str.toCharArray();
        for(int i = start; i < str.length(); i++){
            if(chars[i] == ' '){
                res = i;
                break;
            }
        }
        return res;
    }

    public static int findDate(String dayOfWeek, int numOfWeek){
        int numDayInWeek = 0;
        for(int i = 0; i < 7; i++){
            if(week[i].equals(dayOfWeek)){
                numDayInWeek = i;
                break;
            }
        }
        return numDayInWeek + (7 * (numOfWeek));
    }

    public static int positionOfTheDayOfTheWeek(String day){
        int i = 0;
        while (i < 7){
            if(week[i].equals(day)) break;
            i++;
        }
        return i;
    }


    public static String[] dateOfClass(String classTime){
        String[] res = new String[2];
        res[0] = returnString(classTime, 0 ,1);
        res[1] = returnString(classTime, 2, 4);
        return res;
    }

    public static void printHeaderLine(String[] classesTime, int sumClasses){
        System.out.print("        ");
        for(int numOfWeeks = 0; numOfWeeks < 5; numOfWeeks++){
            for(int numOfDaysOfWeek = 0; numOfDaysOfWeek < 7; numOfDaysOfWeek++){
                for(int numOfClasses = 0; numOfClasses < sumClasses; numOfClasses++) {
                    String[] timeAndDay = dateOfClass(classesTime[numOfClasses]);
                    int date = findDate(timeAndDay[1], numOfWeeks);
                    if (date == 0 || date > 30) continue;
                    if (positionOfTheDayOfTheWeek(timeAndDay[1]) == numOfDaysOfWeek) System.out.print(timeAndDay[0] + ":00 " + timeAndDay[1] + "  " + date + "|");
                }
            }
        }
        System.out.println();
    }

    public static void printBottom(String[] classesTime, int sumClasses, String[] scheduleList, int sumVisits){
        for(int i = 0; i < sumVisits; i++ ){
            int indName = findEndIndex(scheduleList[i], 0);
            String name = returnString(scheduleList[i], 0, indName);
            String time = returnString(scheduleList[i], indName + 1, indName + 2);
            String day = returnString(scheduleList[i], indName + 3, findEndIndex(scheduleList[i], indName + 3));
            int indVisit = findEndIndex(scheduleList[i], indName + 3) + 1;
            String visitOrNotVisit = returnString(scheduleList[i], indVisit, scheduleList[i].length());
            int count = 10 - name.length();
            System.out.print("     ");
            System.out.print(name);
            for(int numOfWeeks = 0; numOfWeeks < 5; numOfWeeks++){
                for(int numOfDaysOfWeek = 0; numOfDaysOfWeek < 7; numOfDaysOfWeek++){
                    for(int numOfClasses = 0; numOfClasses < sumClasses; numOfClasses++) {
                        String[] timeAndDay = dateOfClass(classesTime[numOfClasses]);
                        int date = findDate(timeAndDay[1], numOfWeeks);
                        if (date == 0 || date > 30) continue;
                        if (positionOfTheDayOfTheWeek(timeAndDay[1]) == numOfDaysOfWeek){
                            if(time.equals(timeAndDay[0]) && compareIntString(date, day)){
                                if(visitOrNotVisit.equals("HERE")) System.out.print("          1|");
                                else System.out.print("                   -1|");
                            } else System.out.print("         |");
                        }
                    }
                }
            }
            System.out.println();
        }
    }


    public static boolean compareIntString(int value, String string){
        int stringAsInt = Integer.parseInt(string);
        boolean a = value == stringAsInt;
        return a;
    }
}

