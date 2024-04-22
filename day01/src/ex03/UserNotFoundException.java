package ex03;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(String s){
        super(s);
    };
}
