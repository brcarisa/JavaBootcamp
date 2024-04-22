package ex02;

public class Printer {
    private int[] arr;
    private int sumAll;

    public Printer(int[] arr){
        this.arr = arr;
    }

    public synchronized void print(int first, int last, int numThread){
        int sumThread = 0;
        for(int i = first; i <= last; i++){
            sumThread += arr[i];
        }
        System.out.println("Thread " + numThread + ": from " + first + " to " + last + " sum is " + sumThread);
        sumAll += sumThread;
    }

    public int getSumAll(){
        return sumAll;
    }
}
