package ex02;

public class Program {
    public static void main(String[] args) {
        User us1 = new User("Bill1", 3010);
        User us2 = new User("Bill2", 1000);
        User us3 = new User("Bill3", 11000);
        User us4 = new User("Bill4", 1300);
        User us5 = new User("Bill5", 3020);
        User us6 = new User("Bill6", 1600);
        User us7 = new User("Bill7", 2300);
        User us8 = new User("Bill8", 1000);
        User us9 = new User("Bill9", 134000);
        User us10 = new User("Bill10", 3500);
        User us11 = new User("Bill11", 1100);
        User us12 = new User("Bill12", 1800);
        User us13 = new User("Bill13", 1800);

        UsersArrayList userList = new UsersArrayList();
        userList.addUser(us1);
        userList.addUser(us2);
        userList.addUser(us3);
        userList.addUser(us4);
        userList.addUser(us5);
        userList.addUser(us6);
        userList.addUser(us7);
        userList.addUser(us8);
        userList.addUser(us9);
        userList.addUser(us10);
        userList.addUser(us11);
        userList.addUser(us12);
        userList.addUser(us13);

        userList.searchUserById(14).printUserInfo();
        userList.searchUserById(-13).printUserInfo();
        userList.searchUserById(10).printUserInfo();

        userList.searchUserByIndex(0).printUserInfo();
        userList.searchUserByIndex(10).printUserInfo();
        userList.searchUserByIndex(15).printUserInfo();
    }

}
