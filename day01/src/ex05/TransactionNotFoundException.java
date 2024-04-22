package ex05;

public class TransactionNotFoundException extends RuntimeException{
    TransactionNotFoundException(String s){
        super(s);
    }
}
