package ex01;

public class Printer {
    private boolean isEggTurn = true;

    public synchronized void printEgg() throws InterruptedException{
        if(!isEggTurn) wait();
        System.out.println("Egg");
        isEggTurn = false;
        notify();
    }

    public synchronized void printHen() throws InterruptedException{
        if(isEggTurn) wait();
        System.out.println("Hen");
        isEggTurn = true;
        notify();
    }
}
