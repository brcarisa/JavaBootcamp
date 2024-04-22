package ex01;

public class EggThread implements Runnable{

    private Printer printer;
    private int count;

    public EggThread(int count, Printer printer){
        this.count = count;
        this.printer = printer;
    }

    @Override
    public void run(){
        try {
            for(int i = 0; i < count; i++){
                printer.printEgg();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
