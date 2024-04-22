package ex03;

public class TransactionNotFoundException extends RuntimeException{
    TransactionNotFoundException(String s){
        super(s);
    }
}
