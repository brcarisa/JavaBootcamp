package ex04;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(String s){
        super(s);
    };
}
