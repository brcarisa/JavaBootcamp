package ex03;

import java.io.IOException;

public class Program {
    private static final String PARAM = "--threadsCount=";
    private static int countThreads;
    private static Thread[] threads;
    private static UrlsList urlsList;

    public static void main(String[] args) {


        try {
           validParams(args);
           parseData();
           runThreads();
        } catch (NumberFormatException e){
            System.err.println("Invalid argument");
        } catch (RuntimeException exception){
            System.err.println(exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void validParams(String[] args){
        if(args.length == 1){
            if(args[0].startsWith(PARAM)){
                countThreads = Integer.parseInt(args[0].substring(PARAM.length()));
            } else {
                throw new NumberFormatException();
            }
        } else {
            throw new NumberFormatException();
        }
    }

    private static void parseData() throws IOException {
        urlsList = new UrlsList();
        threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; i++) {
            threads[i] = new Thread(new MyThread(i + 1, urlsList));
        }
    }

    private static void runThreads(){
        for (int i = 0; i < countThreads; i++) {
            threads[i].start();
        }
    }

}
