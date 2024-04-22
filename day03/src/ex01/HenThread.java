package ex01;

public class HenThread implements Runnable{
    private Printer printer;
    private int count;

    public HenThread(int count, Printer printer){
        this.count = count;
        this.printer = printer;
    }

    @Override
    public void run(){
        try {
            for(int i = 0; i < count; i++){
                printer.printHen();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
