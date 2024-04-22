package ex01;

public class Program {
    private static final String ARG = "--count=";

    public static void main(String[] args) {
        int counts = 0;
        if(args.length != 1 || !args[0].startsWith(ARG)){
            System.err.println("Please use this constructor " + ARG + "<count>");
            System.exit(-1);
        }
        try{
            String numCounts = args[0].substring(ARG.length());
            counts = Integer.parseInt(numCounts);
            if(counts < 1){
                throw new NumberFormatException("Count can't be negative");
            }

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        Printer printer = new Printer();
        Thread eggThread = new Thread(new EggThread(counts, printer));
        Thread henThread = new Thread(new HenThread(counts, printer));
        eggThread.start();
        henThread.start();
    }
}
