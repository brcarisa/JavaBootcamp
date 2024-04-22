package ex04;

public class TransactionNotFoundException extends RuntimeException{
    TransactionNotFoundException(String s){
        super(s);
    }
}
