package ex05;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(){
        super();
    }
    public InvalidDataException(String s){
        super(s);
    }
}
