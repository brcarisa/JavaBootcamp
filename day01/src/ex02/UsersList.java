package ex02;

public interface UsersList {
    void addUser(User user);
    User searchUserById(int identifier) throws UserNotFoundException;
    User searchUserByIndex(int index);
    int getAmountUsers();
}
