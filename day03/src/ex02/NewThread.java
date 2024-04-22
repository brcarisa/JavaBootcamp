package ex02;

public class NewThread implements Runnable{
    private Printer printer;
    private int firstInd;
    private int lastInd;
    private int numThread;

    public NewThread(Printer printer, int firstInd, int lastInd, int numThread){
        this.printer = printer;
        this.firstInd = firstInd;
        this.lastInd = lastInd;
        this.numThread = numThread;
    }

    @Override
    public void run(){
        printer.print(firstInd, lastInd, numThread);
    }
}
