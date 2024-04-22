package ex00;

public class Program {
    private static final String ARG = "--count=";

    public static void main(String[] args) {
        if(args.length != 1){
            System.err.println("Please use this constructor " + ARG + "<count>");
            System.exit(-1);
        }
        if(!args[0].startsWith(ARG)){
            System.err.println("Please use this constructor " + ARG + "<count>");
            System.exit(-1);
        }
        try{
            String numCounts = args[0].substring(ARG.length());
            int counts = Integer.parseInt(numCounts);
            if(counts < 1){
                throw new NumberFormatException("Count can't be negative");
            }
            Thread eggThread = new Thread(new NewThread("Egg", counts));
            Thread henThread = new Thread(new NewThread("Hen", counts));
            eggThread.start();
            henThread.start();
            eggThread.join();
            henThread.join();
            for(int i = 0; i < counts; i++){
                System.out.println("Human");
            }
        } catch (NumberFormatException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        catch (InterruptedException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
