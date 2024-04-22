package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Ira", 100);
        User user2 = new User("DAnil", 100);
        User user3 = new User("Ivan", 150);

        user1.printUserInfo();
        user2.printUserInfo();
        user3.printUserInfo();
    }

}
