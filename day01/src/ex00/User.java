package ex00;

public class User {
    private int identifier;
    private String name;
    private int balance;
    private static int count = 0;

    public User(String name, int balance){
        if(name == null || balance < 0){
            System.err.println("Please add correct arguments");
        } else {
            this.name = name;
            this.balance = balance;
            this.identifier = ++count;
        }
    }

    public void printUserInfo(){
        System.out.println("ID: " + identifier + " Name: " + name + " Balance: " + balance);
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public int getIdentifier() {
        return identifier;
    }
}
