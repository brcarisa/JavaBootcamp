package ex00;

public class NewThread implements Runnable{
    private  String text;
    private  int count;

    public NewThread(String text, int count){
        this.text = text;
        this.count = count;
    }

    @Override
    public void run(){
        for(int i = 0; i < count; i++){
            System.out.println(this.text);
        }
    }

}
