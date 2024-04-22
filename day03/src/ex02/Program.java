package ex02;

import java.util.Arrays;
import java.util.Random;

public class Program {
    private static final String FIRST_PARAM = "--arraySize=";
    private static final String SECOND_PARAM = "--threadsCount=";

    private static int arraySize;
    private static int threadsCount;
    private static int[] arr;
    private static Printer printer;
    private static Thread[] threads;


    public static void main(String[] args) {
        try{
            checkAndParseParams(args);
            fillArr();
            execution();
            System.out.println("Sum by threads: " + printer.getSumAll());
        } catch (NumberFormatException e){
            System.err.println("Invalid arguments");
            System.exit(-1);
        }

    }

    private static void checkAndParseParams(String[] args){
        if(args.length == 2){
            if(args[0].startsWith(FIRST_PARAM) && args[1].startsWith(SECOND_PARAM)){
                arraySize = Integer.parseInt(args[0].substring(FIRST_PARAM.length()));
                threadsCount = Integer.parseInt(args[1].substring(SECOND_PARAM.length()));
                if (arraySize > 2_000_000 || threadsCount < 1 || threadsCount > arraySize) {
                    System.err.println("Error: Illegal argument for arraySize or threadsCount");
                    System.exit(-1);
                }
            } else {
                throw new IllegalArgumentException("Use this constructor: java Program --arraySize=<arraySize> --threadsCount=<threadsCount>");
            }
        } else {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
    }

    private static void fillArr(){
        int section, first;
        int last = 0, n = 0;

        arr = new int[arraySize];
        Random random = new Random();
        threads = new Thread[threadsCount];

        for(int i = 0; i < arraySize; i++){
            arr[i] = random.nextInt(2001) - 1000;
        }
        System.out.println("Sum: " + Arrays.stream(arr).sum());
        printer = new Printer(arr);
        section = arraySize / threadsCount;


        for(int i = 0; i < threadsCount - 1; i++, n++){
            first = section * n;
            last = first + section - 1;
            threads[i] = new Thread(new NewThread(printer, first, last, (i + 1)));
        }

        if(arraySize % threadsCount != 0){
            section = arraySize - (section * (threadsCount - 1));
        }

        if(threadsCount > 1){
            threads[threads.length - 1] = new Thread(new NewThread(printer, last + 1, last + section, threads.length));
        } else {
            threads[0] = new Thread(new NewThread(printer, 0, arraySize - 1, 1));
        }
    }

    private static void execution(){
        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
                Thread.sleep(3);
            }
            for(int j = 0; j < threads.length; j++){
                threads[j].join();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
