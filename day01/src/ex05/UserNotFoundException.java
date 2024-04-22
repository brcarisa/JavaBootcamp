package ex05;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(String s){
        super(s);
    };
}
