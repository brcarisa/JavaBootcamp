package ex04;

public class IllegalTransactionException extends RuntimeException {
    public IllegalTransactionException(String s) {
        super(s);
    }
}
